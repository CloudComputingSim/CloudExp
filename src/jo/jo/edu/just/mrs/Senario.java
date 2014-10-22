/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs;

import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jo.edu.just.mrs.schedular.MTLSchedular;
import jo.edu.just.mrs.schedular.RandomSchedular;
import jo.edu.just.mrs.schedular.Schedular;
import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.examples.CloudSimExampleSANStorage;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 *
 * @author Omar
 */
public class Senario {

    StorageDatacenter datacenter;
    DatacenterBroker broker;
    private List<Block> blockList = new ArrayList<Block>();
    private List<File> fileList = new ArrayList<File>();
    LinkedList<Storage> storageList = new LinkedList<Storage>();
    private static List<Task> taskList = new ArrayList<Task>();
    private static List<Vm> vmlist = new ArrayList<Vm>();
    private String name;
    private int blockNo = 5;
    private int hostNo = 25;
    private int fileNo = 50;//must be more than or equal hostNo*2
    private int replicaRasio = 3;
    private int taskNo = 100;
    private int taskFileNo = 1;
    private int fileSize = 64;//file size is in MBytes
    private int schedularType = RANDOM;
    private static final int MTL = 0;
    private static final int RANDOM = 1;
    
    private int storageBandwidth = 100;
    private double storageNetworkLatency = 200.0;
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
                if (blockIdx == (blockNo - 1)) {
                    blockList.add(new Block(blockIdx, Block.SMALL));
                } else {
                    blockList.add(new Block(blockIdx, Block.NORMAL));
                }
            }

            //create Host
            HostLDB host = new HostLDB(
                    hostId,
                    0,//blockId
                    new RamProvisionerSimple(ram),
                    new BwProvisionerSimple(bw),
                    storage,
                    peList,
                    new VmSchedulerTimeShared(peList));
            hostList.add(host); // This is our machine
            blockList.get(blockIdx).addHost(host);

            //add storage and data file
            try {
                LocalSanStorage localStorage = new LocalSanStorage(hostId, 1000000, storageBandwidth, storageNetworkLatency);
                localStorage.addFile(getHostFileList());
                storageList.add(localStorage);
            } catch (ParameterException ex) {
                Logger.getLogger(CloudSimExampleSANStorage.class.getName()).log(Level.SEVERE, null, ex);
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
        double costPerBw = 0.0; // the cost of using bw in this resource

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);

        // 6. Finally, we need to create a PowerDatacenter object.
        StorageDatacenter datacenter = null;
        try {
            datacenter = new StorageDatacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    private void createFiles() {
        try {
            for (int i = 0; i < fileNo; i++) {
                File file = new File("File" + i, fileSize);
                fileList.add(file);
            }
            // devices by now
        } catch (ParameterException ex) {
            Logger.getLogger(CloudSimExampleSANStorage.class.getName()).log(Level.SEVERE, null, ex);
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
    private static DatacenterBroker createBroker() {
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("Broker");
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
        int mips = 1000;
        long size = 10000; // image size (MB)
        int ram = 512; // vm memory (MB)
        long bw = 1000;
        int pesNumber = 1; // number of cpus
        String vmm = "Xen"; // VMM name
        int brokerId = broker.getId();

        for (; vmid < hostNo; vmid++) {
            // create VM
            Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerSpaceShared());

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
        long length = 400000;
        long fileSize = this.fileSize * 1024 * 1024;
        long outputSize = this.fileSize * 1024 * 1024;
        UtilizationModel utilizationModel = new UtilizationModelFull();
        boolean record = true;
        int brokerId = broker.getId();

        for (; id < taskNo; id++) {
            //create files needed from task
            List<String> fileNameList = new ArrayList<String>();
            Random random = new Random(System.nanoTime());
            for (int i = 0; i < taskFileNo; i++) {
                int fileIdx = random.nextInt(fileNo);
                while (true) {
                    if (!fileNameList.contains(fileList.get(fileIdx).getName())) {
                        fileNameList.add(fileList.get(fileIdx).getName());
                        break;
                    }
                    fileIdx = random.nextInt(fileNo);
                }
            }

            Task task = new Task(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel, record, fileNameList);
            task.setUserId(brokerId);

            //search for host VM to execute this task
            Schedular schedular;
            switch (schedularType) {
                case MTL:
                    schedular = new MTLSchedular(storageList);
                    task.setVmId(schedular.getVM(task));
                    break;

                case RANDOM:
                    schedular = new RandomSchedular(storageList);
                    task.setVmId(schedular.getVM(task));
                    break;
            }


            // add the cloudlet to the list
            taskList.add(task);
        }
        // submit cloudlet list to the broker
        broker.submitCloudletList(taskList);
    }

    public void start() {
        // Sixth step: Starts the simulation
        CloudSim.startSimulation();

        CloudSim.stopSimulation();

        //Final step: Print results when simulation is over
        List<Cloudlet> newList = broker.getCloudletReceivedList();
        printCloudletList(newList, datacenter);

        // Print the debt of each user to each datacenter
        datacenter.printDebts();
        Log.printLine(name + " finished!");

    }

    private static void printCloudletList(List<Cloudlet> list, StorageDatacenter datacenter) {
        int size = list.size();
        Cloudlet cloudlet;

        String indent = "    ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Cloudlet ID" + indent + "STATUS" + indent
                + "Data center ID" + indent + "VM ID" + indent + "Time" + indent
                + "Start Time" + indent + "Finish Time");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            cloudlet = list.get(i);
            Log.print(indent + cloudlet.getCloudletId() + indent + indent);

            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
                Log.print("SUCCESS");

                Log.printLine(indent + indent + cloudlet.getResourceId()
                        + indent + indent + indent + cloudlet.getVmId()
                        + indent + indent
                        + dft.format(cloudlet.getActualCPUTime()) + indent
                        + indent + dft.format(cloudlet.getExecStartTime())
                        + indent + indent
                        + dft.format(cloudlet.getFinishTime()));
            }
        }//end for
        Log.printLine("");
        Log.printLine("Local Storage = "+datacenter.getLocalStorage());
        Log.printLine("SAN Storage = "+datacenter.getSANStorage());
        Log.printLine("");
    }
}
