/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Yu.Scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import Yu.Task;
import java.util.List;
import org.cloudbus.cloudsim.power.PowerVm;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.Vm;
import sun.misc.VM;

/**
 *
 * @author Omar
 */
public class RandomScheduler extends Scheduler {

   List<Vm> vmlist;

   

    public RandomScheduler(List<Vm> vmlist) {
        this.vmlist = vmlist;
    }

    @Override
    public void submitJobs(List<Task> taskList) {
       
            for (int taskIdx = 0; taskIdx < taskList.size(); taskIdx++) {
                Task task = taskList.get(taskIdx);
                task.setVmId(getVM(task));
            
        }
    }

    @Override
    public int getVM(Task task) {
        Random random = new Random(System.nanoTime());
        return random.nextInt(vmlist.size());
    }
}
