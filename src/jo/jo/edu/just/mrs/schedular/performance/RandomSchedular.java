/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import jo.edu.just.mrs.performance.Job;
import jo.edu.just.mrs.performance.Task;

import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.Vm;

/**
 *
 * @author Omar
 */
public class RandomSchedular extends Schedular {

    LinkedList<Storage> storageList;

    public RandomSchedular(LinkedList<Storage> storageList) {
        this.storageList = storageList;
    }



    @Override
    public void submitJobs(ArrayList<Job> jobList) {
        for (int jobIdx = 0; jobIdx < jobList.size(); jobIdx++) {
            Job job = jobList.get(jobIdx);
            for (int taskIdx = 0; taskIdx < job.getTasksSize(); taskIdx++) {
                Task task = job.getTaskAt(taskIdx);
                task.setVmId(getVM(task));
            }
        }
    }

    @Override
    public int getVM(Task task) {
        Random random = new Random(System.nanoTime());
        return random.nextInt(storageList.size());
    }
}
