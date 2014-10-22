/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular;

import java.util.LinkedList;
import java.util.Random;
import jo.edu.just.mrs.LocalSanStorage;
import jo.edu.just.mrs.Task;
import org.cloudbus.cloudsim.Storage;

/**
 *
 * @author Omar
 */
public class RandomSchedular extends Schedular{
    
    LinkedList<Storage> storageList;

    public RandomSchedular(LinkedList<Storage> storageList) {
        this.storageList = storageList;
    }

    @Override
    public int getVM(Task task) {
        Random random = new Random(System.nanoTime());
        return random.nextInt(storageList.size());
    }
    
}
