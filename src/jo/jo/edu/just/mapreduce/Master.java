/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mapreduce;


import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class Master {
    
    private ArrayList<Map> mapList;
    private ArrayList<Reduce> reduceList;
    private static Master instance = null;

    public Master() {
        mapList = new ArrayList<Map>();
        reduceList = new ArrayList<Reduce>();
    }
    
    
    
    public static Master getInstance(){
        if(instance == null){
            instance = new Master();
        }
        
        return instance;
    }
    
    public void AddMaper(Map map){
        mapList.add(map);
    }
    
    public void AddReduce(Reduce reduce){
        reduceList.add(reduce);
    }
    
    
    public boolean isAllMapFinished(){
        
        if(mapList == null){return true;}
        
        for (Map map : mapList) {
			if (/*!*/map.getStatus() != Map.SUCCESS/*isFinished()*/) {
				return false;
			}
		}
        return true;
    }
    
    public boolean isAllReduceFinished(){
        
        if(reduceList == null){return true;}
        
        for (Reduce reduce : reduceList) {
			if (/*!*/reduce.getStatus() != Reduce.SUCCESS/*isFinished()*/) {
				return false;
			}
		}
        return true;
    }
    
    public boolean isJobFinished(){
        return (isAllMapFinished() & isAllReduceFinished());
    }
    
    
}
