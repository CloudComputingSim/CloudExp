/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Yu;

//import jo.edu.just.mrs.performance.*;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelNull;
import org.cloudbus.cloudsim.UtilizationModelStochastic;

/**
 *
 * @author Omar
 */
public class Task extends Cloudlet {

    
    private static UtilizationModel utilizationModelNull = new UtilizationModelNull();
    private static UtilizationModelStochastic utilizationModelStochastic = new UtilizationModelStochastic();

    public Task(int taskId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize) {
        super(taskId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelStochastic, utilizationModelNull, utilizationModelNull);
    
    }

    public Task(int taskId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, List<String> fileList) {
        super(taskId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelStochastic, utilizationModelNull, utilizationModelNull, fileList);
       
    }

    public Task(int taskId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, boolean record) {
        super(taskId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelStochastic, utilizationModelNull, utilizationModelNull, record);
       
    }

    public Task(int taskId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, boolean record, List<String> fileList) {
        super(taskId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelStochastic, utilizationModelNull, utilizationModelNull, record, fileList);

    }

   
}
