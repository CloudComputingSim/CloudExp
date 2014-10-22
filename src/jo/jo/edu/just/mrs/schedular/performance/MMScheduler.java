/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jo.edu.just.mrs.performance.HostLDB;
import jo.edu.just.mrs.performance.Job;
import jo.edu.just.mrs.performance.LocalSanStorage;
import jo.edu.just.mrs.performance.Task;
import org.cloudbus.cloudsim.HarddriveStorage;
import java.util.*;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.Vm;

/**
 *
 * @author Omar
 */

/*
 * Multi-Threading Locality Schedular
 */
public class MMScheduler extends Schedular {

    private int vmArrayCounter[];
    private int localityMarker[];
    private int previousMarker=0;
    private int count=0;
    LinkedList<Storage> storageList;
    List<Vm> vmlist;
    

    public MMScheduler(LinkedList<Storage> storageList,  List<Vm> vmlist ) {
        this.storageList = storageList;
        this.vmlist= vmlist;
       vmArrayCounter = new int[storageList.size()];
       localityMarker= new int[storageList.size()];
        clearMarker();
    }
    private void clearMarker()
    {
                for (int storageIdx = 0; storageIdx < storageList.size(); storageIdx++) {
                    localityMarker[storageIdx]=0;
                }
    }
    
     @Override
    public void submitJobs(ArrayList<Job> jobList) {
         int vmId = 0;
        int maxFound = -1;
        
       for (int storageIdx = 0; storageIdx < storageList.size(); storageIdx++) 
                 { Storage storage = storageList.get(storageIdx);
                 
                  Vm vm=vmlist.get(storageIdx);
               //     previousMarker=localityMarker[storageIdx];
            //    while (vm.notHasTask())
             //  {
                   
                  
          for(int jobIdx = 0; jobIdx < jobList.size(); jobIdx++)
         { 
             
             Job job = jobList.get(jobIdx);
              OUTER:for(int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++)
             {
                  //Log.print("first  " + 0+"   end   " + storageList.size()+"\n");
                 Task task = job.getTaskAt(taskIdx);
               
                 if(task.getVmId()==-1)
                {
                  int found = getFoundFileNo(task, storageList.get(storageIdx));
            if (found > 0) {
               // maxFound = found;
                             
            
               vmId = ((LocalSanStorage) storage).getOwnerID();
                 task.setVmId(vmId);
              //   vm.sethasTask(false);
                 
              // break OUTER;
                //break OUTER2;
                // break OUTER3;
               
            }
//            else
//            {
//                break OUTER;
//            }
                
            }
//                 else
//                 {
//                       System.err.println("hhhhhhhhhhhhhhhhh");
//                       break;
//                 }
              
          
            
             }
           
         }
           
                 }
         
     }
    

    @Override
    public int getVM(Task task) {
      int vmId = 0;
//        int maxFound = -1;
//        for (int storageIdx = 0; storageIdx < storageList.size(); storageIdx++) {
//            int found = getFoundFileNo(task, storageList.get(storageIdx));
//            if (found > maxFound) {
//                maxFound = found;
//                vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();
//               
//            } else if (found == maxFound) {
//                if (vmArrayCounter[found] < vmArrayCounter[maxFound]) {
//                    maxFound = found;
//                    vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();
//                }
//            }
//        }
//        vmArrayCounter[vmId]++;
        return vmId;
    }

    private int getFoundFileNo(Task task, Storage storage) {
        int found = 0;
        for (int i = 0; i < task.getRequiredFiles().size(); i++) {
            if (storage.getFile(task.getRequiredFiles().get(i)) != null) {
                found++;
                 count ++;
            }
        }
         
        
        return found;
    }

   
}
