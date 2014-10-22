/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs.performance;

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

    private int jobId;
    private static UtilizationModel utilizationModelNull = new UtilizationModelNull();
    private static UtilizationModelStochastic utilizationModelStochastic = new UtilizationModelStochastic();

    public Task(int taskId, int jobId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize) {
        super(taskId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelStochastic, utilizationModelNull, utilizationModelNull);
        this.jobId = jobId;
    }

    public Task(int taskId, int jobId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, List<String> fileList) {
        super(taskId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelStochastic, utilizationModelNull, utilizationModelNull, fileList);
        this.jobId = jobId;
    }

    public Task(int taskId, int jobId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, boolean record) {
        super(taskId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelStochastic, utilizationModelNull, utilizationModelNull, record);
        this.jobId = jobId;
    }

    public Task(int taskId, int jobId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, boolean record, List<String> fileList) {
        super(taskId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelStochastic, utilizationModelNull, utilizationModelNull, record, fileList);
        this.jobId = jobId;
    }

    public int getJobId() {
        return jobId;
    }
}
