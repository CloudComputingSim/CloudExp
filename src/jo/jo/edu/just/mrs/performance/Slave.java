/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.performance;

/**
 *
 * @author Omar
 */
public class Slave extends Thread{
    
    private Task task;

    public Slave(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        
    }
  
    //for each thread
    public HostLDB SearchForSueatableHost(){
        HostLDB host = null;
        
        return host;
    }
    
    
}
