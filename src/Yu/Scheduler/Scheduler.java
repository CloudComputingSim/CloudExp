/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Yu.Scheduler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.List.*;
import Yu.Task;
import jo.edu.just.mrs.performance.Job;
import org.cloudbus.cloudsim.Storage;
/**
 *
 * @author Omar
 */
public abstract class Scheduler {
    
    public abstract void submitJobs(List<Task> taskList);

    /*
     * retern suitable VM that should execute this task
     */
    public abstract int getVM(Task task);
    
}
