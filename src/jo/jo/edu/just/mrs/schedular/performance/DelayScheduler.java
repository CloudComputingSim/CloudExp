/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular.performance;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jo.edu.just.mrs.performance.Job;
import jo.edu.just.mrs.performance.Task;
import jo.edu.just.mrs.performance.LocalSanStorage;
import org.cloudbus.cloudsim.Storage;

/**
 *
 * @author Omar
 */
public class DelayScheduler extends Schedular
{
     private int vmArrayCounter[];
     private int waitJob[];
    LinkedList<Storage> storageList;

    public DelayScheduler(LinkedList<Storage> storageList) {
        this.storageList = storageList;
        vmArrayCounter = new int[storageList.size()];
          }
  @Override
    public void submitJobs(ArrayList<Job> jobList) {
      waitJob=new int[jobList.size()];
      int vmId = 0;
        int maxFound = -1;
                  for (int storageIdx = 0; storageIdx < storageList.size(); storageIdx++) 
                 {
                   
                    for(int jobIdx = 0; jobIdx < jobList.size(); jobIdx++)
         {
             Job job = jobList.get(jobIdx);
            OUTER: for(int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++)
             {
                 Task task = job.getTaskAt(taskIdx);
                  if(task.getVmId()==-1)
                {
                  int found = getFoundFileNo(task, storageList.get(storageIdx));
            if (found > 0) {
                maxFound = found;
                vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();              
                 task.setVmId(vmId);
                // break;
         }
            else
                //if (found == maxFound) 
            {
                waitJob[jobIdx]=1;
                break OUTER;               
                }
            }
             }
             vmArrayCounter[vmId]++;
             
             }
         }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DelayScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
                 runWaitJob(waitJob,jobList);
                 

  }
 private void runWaitJob(int []waitJob,ArrayList<Job> jobList)
 {
     int vmId = 0;
        int maxFound = -1;
                 for (int storageIdx = 0; storageIdx < storageList.size(); storageIdx++) 
                 {
                   
                for(int jobIdx = 0; jobIdx < waitJob.length; jobIdx++)
         {
             if(waitJob[jobIdx]==1)
             {
             Job job = jobList.get(jobIdx);
             for(int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++)
             {
                 Task task = job.getTaskAt(taskIdx);
                  if(task.getVmId()==-1)
                {
                  int found = getFoundFileNo(task, storageList.get(storageIdx));
            if(found>0)
            {
                vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID(); 
                task.setVmId(vmId);
               // break;
            }
                 
             }
             }
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
            }
        }
        return found;
    }

   

   
    
}
