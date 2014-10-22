/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jo.edu.just.mrs.performance.Job;
import jo.edu.just.mrs.performance.Task;
import jo.edu.just.mrs.performance.LocalSanStorage;
import org.cloudbus.cloudsim.Storage;

/**
 *
 * @author Omar
 */
public class FIFOScheduler extends Schedular
{
     private int vmArrayCounter[];
    LinkedList<Storage> storageList;

    public FIFOScheduler(LinkedList<Storage> storageList) {
        this.storageList = storageList;
        vmArrayCounter = new int[storageList.size()];
    }
 @Override
    public void submitJobs(ArrayList<Job> jobList) {
             int vmId = 0;

        for(int jobIdx = 0; jobIdx < jobList.size(); jobIdx++)
         {
             Job job = jobList.get(jobIdx);
             for(int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++)
             {
                 Task task = job.getTaskAt(taskIdx);
                  for (int storageIdx = 0; storageIdx < storageList.size(); storageIdx++) {
            int found = getFoundFileNo(task, storageList.get(storageIdx));
            if (found > 0) {
               // maxFound = found;
                vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();
             
            }
//        }
            else
            {
             vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();
             break;
            }
            task.setVmId(vmId);
        }
             }
         }    
    }
     @Override
    public int getVM(Task task) {
        int vmId = 0;

//        for (int storageIdx = 0; storageIdx < storageList.size(); storageIdx++) {
//            int found = getFoundFileNo(task, storageList.get(storageIdx));
//            if (found > 0) {
//               // maxFound = found;
//                vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();
//             
//            }
////        }
//            else
//            {
//             vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();
//             break;
//            }
//        }
       
        return vmId;
    }
    

    private int getFoundFileNo(Task task, Storage storage) {
        int found = 0;
        for (int i = 0; i < task.getRequiredFiles().size(); i++) {
            if (storage.getFile(task.getRequiredFiles().get(i)) != null) {
                found++;
            }
        }
        return found;
    }


   
    
}
