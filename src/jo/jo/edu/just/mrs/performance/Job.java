/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.performance;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
public class Job {
    
    int id = 0;
    
    List<Task> tasks = new ArrayList<Task>();
    private double startTime = Double.MAX_VALUE;
    private double finishTime = Double.MIN_VALUE;
    
    private int state = STATE_FRESH;
    public static final int STATE_FRESH = 0;
    public static final int STATE_IDEL = 1;
    
    private static final int SMALL_JOP_TASK_NO = 0;//if the job have thes number of task or less then it is small job

    public Job(int id) {
        this.id = id;
    }
    
    public Job(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }
   public boolean removeTask(Task t)
   {
       return tasks.remove(t);   
   }
    
    public void addTask(Task task){
        tasks.add(task);
    }
    
    public Task getTaskAt(int idx){
        return tasks.get(idx);
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }
    
    public double getStartTime(){
        return startTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    public double getFinishTime() {
        return finishTime;
    }
    
    public double getExecTime(){
        return finishTime - startTime;
    }
    
    public int getTasksSize(){
        return tasks.size();
    }
    
    public boolean isSmallJob(){
        if(tasks.size() <= SMALL_JOP_TASK_NO){
            return true;
        }
        return false;
    }
    
}