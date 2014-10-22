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
import org.cloudbus.cloudsim.Storage;

/**
 *
 * @author Omar
 */
public abstract class Schedular {
    
        
    /*
     * submit job list to schedular to organize it
     */
    public abstract void submitJobs(ArrayList<Job> jobList);

    /*
     * retern suitable VM that should execute this task
     */
    public abstract int getVM(Task task);
    
}
