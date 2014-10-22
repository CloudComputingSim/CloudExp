/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jo.edu.just.mrs.performance.Block;
import jo.edu.just.mrs.performance.Job;
import jo.edu.just.mrs.performance.LocalSanStorage;
import jo.edu.just.mrs.performance.Task;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Storage;

/**
 *
 * @author Omar
 */
public class myRannable extends Thread {
    private int vmArrayCounter[];
    private int count=0;
    LinkedList<Storage> storageList;
    ArrayList<Job> jobList;
    private int startIndex;
    private int endIndex;
   // ArrayList<Block> blockList;
    public myRannable(LinkedList<Storage> storageList, ArrayList<Job> jobList, int startIndex, int endIndex)
     {
        this.storageList = storageList;
        this.jobList = jobList;
        this .startIndex=startIndex;
        this.endIndex=endIndex;
         vmArrayCounter = new int[storageList.size()];
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
     @Override
        public void run() {
          int vmId = 0;
        int maxFound = 0;

         for (int jobIdx = 0; jobIdx < jobList.size(); jobIdx++) {
           Job job = jobList.get(jobIdx);
           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
                    Task task = job.getTaskAt(taskIdx);
                    if(task.getVmId()==-1)
                    {
                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
            Storage storage = storageList.get(storageIdx);
             //System.err.println("first" + startIndex);
             // System.err.println("end" + endIndex);
            
            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
            int found = getFoundFileNo(task, storage);
            
          
            if (found > 0) {
                vmId = ((LocalSanStorage) storage).getOwnerID();
               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
                 task.setVmId(vmId);
                break OUTER;
             
            }
//            else 
//            {
//               Log.print(job.removeTask(task)+"\n");
//             }
                       
           }
                                      
                    }
            }
         }
         
     }
    
}








///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package jo.edu.just.mrs.schedular.performance;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import jo.edu.just.mrs.performance.Block;
//import jo.edu.just.mrs.performance.Job;
//import jo.edu.just.mrs.performance.LocalSanStorage;
//import jo.edu.just.mrs.performance.Task;
//import org.cloudbus.cloudsim.Log;
//import org.cloudbus.cloudsim.Storage;
//
///**
// *
// * @author Omar
// */
//public class myRannable extends Thread {
//    private int vmArrayCounter[];
//    private int count=0;
//    LinkedList<Storage> storageList;
//    ArrayList<Job> jobList;
//    private int startIndex;
//    private int endIndex;
//   // ArrayList<Block> blockList;
//    public myRannable(LinkedList<Storage> storageList, ArrayList<Job> jobList, int startIndex, int endIndex)
//     {
//        this.storageList = storageList;
//        this.jobList = jobList;
//        this .startIndex=startIndex;
//        this.endIndex=endIndex;
//         vmArrayCounter = new int[storageList.size()];
//     }
//
//    private int getFoundFileNo(Task task, Storage storage) {
//        int found = 0;
//        for (int i = 0; i < task.getRequiredFiles().size(); i++) {
//            if (storage.getFile(task.getRequiredFiles().get(i)) != null) {
//                found++;
//                
//            }
//        }
//  
//        return found;
//    }
//     @Override
//        public void run() {
//          int vmId = 0;
//        int maxFound = 0;
//MTLSchedular mtl= new MTLSchedular(storageList, null);
//if(mtl.searchPlace==0)
//{
//         for (int jobIdx = 0; jobIdx < jobList.size()/3; jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
//         for (int jobIdx = jobList.size()/3; jobIdx <=(jobList.size()/3)+(jobList.size()/3) ; jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
//         for (int jobIdx = (jobList.size()/3)+(jobList.size()/3); jobIdx < jobList.size(); jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
//}
//else if(mtl.searchPlace==1)
//{
//     for (int jobIdx = jobList.size()/3; jobIdx <=(jobList.size()/3)+(jobList.size()/3) ; jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
//     for (int jobIdx = 0; jobIdx < jobList.size()/3; jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
//     for (int jobIdx = (jobList.size()/3)+(jobList.size()/3); jobIdx < jobList.size(); jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
//}
//else
//{
//for (int jobIdx = (jobList.size()/3)+(jobList.size()/3); jobIdx < jobList.size(); jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
// for (int jobIdx = 0; jobIdx < jobList.size()/3; jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
//         for (int jobIdx = jobList.size()/3; jobIdx <=(jobList.size()/3)+(jobList.size()/3) ; jobIdx++) {
//           Job job = jobList.get(jobIdx);
//           for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
//                    Task task = job.getTaskAt(taskIdx);
//                    if(task.getVmId()==-1)
//                    {
//                      // Log.print("first  " + startIndex+"   end   " + endIndex+"\n"); 
//                     OUTER:  for (int storageIdx = startIndex; storageIdx <= endIndex; storageIdx++) {
//            Storage storage = storageList.get(storageIdx);
//             //System.err.println("first" + startIndex);
//             // System.err.println("end" + endIndex);
//            
//            // Log.print("first  " + startIndex+"   end   " + endIndex+"\n");
//            int found = getFoundFileNo(task, storage);
//            
//          
//            if (found > 0) {
//                vmId = ((LocalSanStorage) storage).getOwnerID();
//               // Log.print("threre is local for task  "+task.getCloudletId()+ "  in Vm ID :"+vmId+"\n");
//                 task.setVmId(vmId);
//                break OUTER;
//             
//            }
////            else 
////            {
////               Log.print(job.removeTask(task)+"\n");
////             }
//                       
//           }
//                                      
//                    }
//            }
//         }
//}
//     }
//    
//}
