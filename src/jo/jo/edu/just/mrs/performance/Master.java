/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.performance;

import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;

/**
 *
 * @author Omar
 */
public class Master {

    public Master() {
        slaveArray = new Slave[BLOCK_NO];
        taskList = new ArrayList<Task>();
    }
    
    public void submitTask(){
        for(int i = 0; i < taskList.size(); i++)
        {
            
        }
    }
    
    public HostLDB SearchForBestSueatableHost(){
        HostLDB host = null;
        
        return host;
    }
    
    public void SubmitTask(){
        
    }
    
    public static void PrintResults(){
        System.err.println("----------------Results--------------");
    }
    
    /** The cloudlet list. */
	private List<Task> taskList;
        private static final int BLOCK_NO = 5;
        private Thread[] slaveArray;
                
                
        
}
