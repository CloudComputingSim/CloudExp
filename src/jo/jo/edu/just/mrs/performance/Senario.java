/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.performance;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jo.edu.just.mrs.schedular.performance.*;
import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.power.PowerDatacenterBroker;
import org.cloudbus.cloudsim.power.PowerVm;
import org.cloudbus.cloudsim.power.PowerVmAllocationPolicySimple;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 *
 * @author Omar
 */
public class Senario {

    

    StorageDatacenter datacenter;
    PowerDatacenterBroker broker;
    private ArrayList<Block> blockList = new ArrayList<Block>();
    private List<File> fileList = new ArrayList<File>();
    LinkedList<Storage> storageList = new LinkedList<Storage>();
    private static ArrayList<Job> jobList = new ArrayList<Job>();
    private static List<Task> taskList = new ArrayList<Task>();
    private static List<Vm> vmlist = new ArrayList<Vm>();
    private String name;
    private int blockNo =4;
    private int hostNo = 24;
    private int fileNo =50;//must be more than or equal hostNo*2
   
    private int jobNo= 100;
    private int taskNo = 10000;
    private static final int TASK_LENGTH = 500;
    private int taskFileNo = 1;
    
    private int fileSize = 64;//file size is in MBytes
    private double searchTime = 10000;//in s
     private int replicaRasio = 2;
    private int schedularType = MTL;
    private static final int MTL = 0;
    private static final int RANDOM = 1;
    private static final int FIFO=2;
    private static final int DELAY=3;
    private static final int MM=4; // must be blockNo is 1
    private int storageBandwidth = 10;
    private double storageNetworkLatency =5.0;
    //internal usage
    private int fileCounterArray[];
    private int replicaFileCounterArray[];

    public Senario(String name, int schedularType, int blocksNo, int hostNo, int fileNo, int replicaRasio, int taskNo, int taskFileNo) {
        this.name = name;
        this.schedularType = schedularType;
        this.blockNo = blocksNo;
        this.hostNo = hostNo;
        this.fileNo = fileNo;
        this.replicaRasio = replicaRasio;
        this.taskNo = taskNo;
        this.taskFileNo = taskFileNo;
        fileCounterArray = new int[fileNo];
        replicaFileCounterArray = new int[fileNo];
    }

    public Senario() {
        fileCounterArray = new int[fileNo];
        replicaFileCounterArray = new int[fileNo];
    }

    public void build() {
        Log.printLine("Building Senario [" + name + "] ...");

        try {
            // First step: Initialize the CloudSim package. It should be called
            // before creating any entities.
            int num_user = 1; // number of cloud users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false; // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);

            // Second step: Create Datacenters
            // Datacenters are the resource providers in CloudSim. We need at
            // list one of them to run a CloudSim simulation
            datacenter = createDatacenter("Datacenter_0");

            // Third step: Create Broker
            broker = createBroker();


            createVmlist();

            createTasks();



        } catch (Exception e) {
            e.printStackTrace();
            Log.printLine("Unwanted errors happen");
        }

    }

    private StorageDatacenter createDatacenter(String name) {

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store
        // our machine
        List<HostLDB> hostList = new ArrayList<HostLDB>();

        // 2. A Machine contains one or more PEs or CPUs/Cores.
        // In this example, it will have only one core.
        List<Pe> peList = new ArrayList<Pe>();

        int mips = 1000;

        // 3. Create PEs and add these into a list.
        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        // 4. Create Host with its id and list of PEs and add them to the list
        // of machines
        int hostId = 0;
        int ram = 2048; // host memory (MB)
        long storage = 1000000; // host storage
        int bw = 10000;

        createFiles();



        for (; hostId < hostNo; hostId++) {
            //create Block
            int blockIdx = hostId / (hostNo / blockNo);
            if (blockList.size() - 1 != blockNo) {
               // System.err.print("Hi there : "+ blockList.size()+"///");
               // if (blockIdx == (blockNo - 1)) {
                 //   blockList.add(new Block(blockIdx, Block.SMALL));
                //} else {
                    blockList.add(new Block(blockIdx, Block.NORMAL));
                //}
            }
// System.err.print("/////// "+ blockList.size()+"//////");
            //create Host
            HostLDB host = new HostLDB(
                    hostId,
                    blockIdx,//blockId
                    new RamProvisionerSimple(ram),
                    new BwProvisionerSimple(bw),
                    storage,
                    peList,
                    new VmSchedulerTimeSharedOverSubscription(peList));
            
            hostList.add(host); // This is our machine
            blockList.get(blockIdx).addHost(host);

            //add storage and data file
            try {
                LocalSanStorage localStorage = new LocalSanStorage(hostId, 1000000, storageBandwidth, storageNetworkLatency);
                localStorage.addFile(getHostFileList());
                storageList.add(localStorage);
            } catch (ParameterException ex) {
                Logger.getLogger(Senario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        // 5. Create a DatacenterCharacteristics object that stores the
        // properties of a data center: architecture, OS, list of
        // Machines, allocation policy: time- or space-shared, time zone
        // and its price (G$/Pe time unit).
        String arch = "x86"; // system architecture
        String os = "Linux"; // operating system
        String vmm = "Xen";
        double time_zone = 10.0; // time zone this resource located
        double cost = 3.0; // the cost of using processing in this resource
        double costPerMem = 0.05; // the cost of using memory in this resource
        double costPerStorage = 0.001; // the cost of using storage in this
        // resource
        double costPerBw = 0.01; // the cost of using bw in this resource

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);

        // 6. Finally, we need to create a PowerDatacenter object.
        StorageDatacenter datacenter = null;
        try {
            datacenter = new StorageDatacenter(name, characteristics, new PowerVmAllocationPolicySimple(hostList), storageList, 10);
            datacenter.setDisableMigrations(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    private void createFiles() {
        try {
            for (int i = 0; i < fileNo; i++) {
                File file = new File("File" + i, fileSize );
                fileList.add(file);
            }
            // devices by now
        } catch (ParameterException ex) {
            Logger.getLogger(Senario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<File> getHostFileList() {
        int noOfFiles = fileNo / hostNo;
        int noOfReplica = replicaRasio * noOfFiles;
        List<File> hostFileList = new ArrayList<File>();

        Random random = new Random(System.nanoTime());

        //add orginal files
        for (int i = 0; i < noOfFiles; i++) {
            int idx = random.nextInt(fileList.size());
            while (true) {
                if (fileCounterArray[idx] == 0) {
                    fileCounterArray[idx]++;
                    hostFileList.add(fileList.get(idx));
                    break;
                }
                idx = random.nextInt(fileList.size());
            }
        }

        //add replica files
        for (int i = 0; i < noOfReplica; i++) {
            int idx = random.nextInt(fileList.size());
            while (true) {
                if (replicaFileCounterArray[idx] < noOfReplica) {
                    replicaFileCounterArray[idx]++;
                    hostFileList.add(fileList.get(idx));
                    break;
                }
                idx = random.nextInt(fileList.size());
            }
        }

        return hostFileList;
    }

    // We strongly encourage users to develop their own broker policies, to
    // submit vms and cloudlets according
    // to the specific rules of the simulated scenario
    /**
     * Creates the broker.
     *
     * @return the datacenter broker
     */
    private static PowerDatacenterBroker createBroker() {
        PowerDatacenterBroker broker = null;
        try {
            broker = new PowerDatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    private void createVmlist() {
        // Fourth step: Create one virtual machine
        // VM description
        int vmid = 0;
        int mips = 500;
        long size = 10000; // image size (MB)
        int ram = 512; // vm memory (MB)
        long bw = 1000;
        int pesNumber = 1; // number of cpus
        String vmm = "Xen"; // VMM name
        int preiority = 1;
        int SCHEDULING_INTERVAL = 300;
        int brokerId = broker.getId();

        for (; vmid < hostNo * 2; vmid++) {
            // create VM
            PowerVm vm = new PowerVm(vmid, brokerId, mips, pesNumber, ram, bw, size, preiority, vmm, new CloudletSchedulerSpaceShared(), SCHEDULING_INTERVAL);

            // add the VM to the vmList
            vmlist.add(vm);
        }
        // submit vm list to the broker
        broker.submitVmList(vmlist);
    }
    private void createTasks() {
        // Fifth step: Create one Cloudlet
        // Cloudlet properties
        int id = 0;
        int pesNumber = 1; // number of cpus
         Random random = new Random(System.nanoTime());
        
        long fileSize = this.fileSize * 1024 * 1024;
        long outputSize = this.fileSize * 1024 * 1024;
        UtilizationModel utilizationModel = new UtilizationModelFull();
        boolean record = true;
        int brokerId = broker.getId();
        
        //create Jobs
        for(int jobId = 0; jobId < jobNo; jobId++)
        {
            jobList.add(new Job(jobId));
        }

        //create Tasks
        for (; id < taskNo; id++) {
            //create files needed from task
            List<String> fileNameList = new ArrayList<String>();
 //           Random random = new Random(System.nanoTime());
            for (int i = 0; i < taskFileNo; i++) {
                int fileIdx = random.nextInt(fileNo);
                while (true) {
                    if (!fileNameList.contains(fileList.get(fileIdx).getName())) {
                        fileNameList.add(fileList.get(fileIdx).getName());
                        break;
                    }
                    fileIdx = random.nextInt(fileNo);
                }//end while loop
            }

            int jobId = random.nextInt(jobNo);  
            long length = TASK_LENGTH;
                    //random.nextInt(TASK_LENGTH);
                    
             // System.err.println("local is"+length);
            Task task = new Task(id, jobId, length, pesNumber, fileSize, outputSize, record, fileNameList);
            jobList.get(jobId).addTask(task);
            task.setUserId(brokerId);     
            taskList.add(task);   
            
        }
        
        //search for host VM to execute this task
            Schedular schedular = null;
            switch (schedularType) {
                case MTL:
                    schedular = new MTLSchedular(storageList, blockList);
                    break;

                case RANDOM:
                    schedular = new RandomSchedular(storageList);
                    break;
                    
                case FIFO:
                    schedular = new FIFOScheduler(storageList);
                     break;
                         
                case DELAY:
                    schedular = new DelayScheduler(storageList);
                     break;
                    
                case MM:
                    schedular = new MMScheduler(storageList, vmlist);
                     break;
            }
            if(schedular != null){
                schedular.submitJobs(jobList);
            }else{
                new Exception("Error :: The schedular dose not exest");
            }
            
        
        
        // submit cloudlet list to the broker
        broker.submitCloudletList(taskList);
    }

    public void start() {
        // Sixth step: Starts the simulation
//        CloudSim.terminateSimulation(24*60*60*1000);
        
        //if false >> print status else not
        Log.setDisabled(true);
        
        double lastClock = CloudSim.startSimulation();

        CloudSim.stopSimulation();
        Log.setDisabled(false);
        List<Task> newList = broker.getCloudletReceivedList();
        Result.printCloudletList(jobList, newList, datacenter);

        // Print the debt of each user to each datacenter
        datacenter.printDebts();
        switch (schedularType) {
                case MTL:
                    name="MTL";
                    break;

                case RANDOM:
                     name="RANDOM";
                    break;
                    
                case FIFO:
                     name="FIFO";
                     break;
                         
                case DELAY:
                    name="DELAY";
                     break;
                    
                case MM:
                     name="MM";
                     break;
            }
        
        Result.printResults(
                    datacenter,
                    vmlist,
                    lastClock,
                    searchTime,
                    blockNo,
                    name,
                    false,
                    "");
        
        Log.printLine(name + " finished!");
    }

}
