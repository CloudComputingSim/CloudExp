/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.schedular;

import java.util.LinkedList;
import jo.edu.just.mrs.LocalSanStorage;
import jo.edu.just.mrs.Task;
import org.cloudbus.cloudsim.Storage;

/**
 *
 * @author Omar
 */

/*
 * Multi-Threading Locality Schedular
 */
public class MTLSchedular extends Schedular {

    private int vmArrayCounter[];
    LinkedList<Storage> storageList;

    public MTLSchedular(LinkedList<Storage> storageList) {
        this.storageList = storageList;
        vmArrayCounter = new int[storageList.size()];
    }

    @Override
    public int getVM(Task task) {
        int vmId = 0;
        int maxFound = -1;
        for (int storageIdx = 0; storageIdx < storageList.size(); storageIdx++) {
            int found = getFoundFileNo(task, storageList.get(storageIdx));
            if (found > maxFound) {
                maxFound = found;
                vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();
            } else if (found == maxFound) {
                if (vmArrayCounter[found] < vmArrayCounter[maxFound]) {
                    maxFound = found;
                    vmId = ((LocalSanStorage) storageList.get(storageIdx)).getOwnerID();
                }
            }
        }
        vmArrayCounter[vmId]++;
        return vmId;
    }

    private int getFoundFileNo(Task task, Storage storage) {
        int found = 0;
        for (int i = 0; i < task.getRequiredFiles().size(); i++) {
            if (storage.getFile(task.getRequiredFiles().get(i)) != null) {
                found++;
            }
        }
        return found;
    }
}
