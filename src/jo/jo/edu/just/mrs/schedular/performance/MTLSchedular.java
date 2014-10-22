/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import jo.edu.just.mrs.performance.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Storage;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author Omar
 */

/*
 * Multi-Threading Locality Schedular
 */
public class MTLSchedular extends Schedular {

    private int vmArrayCounter[];
    private int startIndex=0;
    private int endIndex=-1;
    public int searchPlace=0;
    LinkedList<Storage> storageList;
    ArrayList<Block> blockList;

    public MTLSchedular(LinkedList<Storage> storageList, ArrayList<Block> blockList) {
        this.storageList = storageList;
        this.blockList = blockList;
        vmArrayCounter = new int[storageList.size()];
       
    }

    @Override
    public void submitJobs(ArrayList<Job> jobList) {


        ArrayList<Thread> threads=new ArrayList<Thread>();
        for (int i=0; i<blockList.size()-1;i++)
        {
             int blockSize=  blockList.get(0).getHosts().size();
                
                endIndex+=blockSize;
            threads.add(new myRannable(storageList,jobList, startIndex, endIndex));
            startIndex +=blockSize;
        }
         for (int i=0; i<blockList.size()-1;i++)
        {
            searchPlace=i;
            threads.get(i).start();
        }
          for (int i=0; i<blockList.size()-1;i++)
        {
            try {
                threads.get(i).join();
              //  Log.print("Thread  :"+i+"  Finished"+"\n");
              
            } catch (InterruptedException ex) {
                Logger.getLogger(MTLSchedular.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public int getVM(Task task) {
        int vmId = 0;

        return vmId;
    }

}
