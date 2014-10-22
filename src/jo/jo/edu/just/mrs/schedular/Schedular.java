/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular;

import java.util.ArrayList;
import java.util.LinkedList;
import jo.edu.just.mrs.Task;
import jo.edu.just.mrs.performance.Job;
import org.cloudbus.cloudsim.Storage;

/**
 *
 * @author Omar
 */
public abstract class Schedular {

    
    /*
     * retern suitable VM that should execute this task
     */
    public abstract int getVM(Task task);
}
