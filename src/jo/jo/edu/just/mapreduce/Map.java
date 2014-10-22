/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mapreduce;

import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;

/**
 *
 * @author apple
 */
public class Map extends Cloudlet{

    public Map(int cloudletId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, UtilizationModel utilizationModelCpu, UtilizationModel utilizationModelRam, UtilizationModel utilizationModelBw) {
        super(cloudletId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelCpu, utilizationModelRam, utilizationModelBw);
    }

    public Map(int cloudletId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, UtilizationModel utilizationModelCpu, UtilizationModel utilizationModelRam, UtilizationModel utilizationModelBw, List<String> fileList) {
        super(cloudletId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelCpu, utilizationModelRam, utilizationModelBw, fileList);
    }

    public Map(int cloudletId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, UtilizationModel utilizationModelCpu, UtilizationModel utilizationModelRam, UtilizationModel utilizationModelBw, boolean record) {
        super(cloudletId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelCpu, utilizationModelRam, utilizationModelBw, record);
    }

    public Map(int cloudletId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, UtilizationModel utilizationModelCpu, UtilizationModel utilizationModelRam, UtilizationModel utilizationModelBw, boolean record, List<String> fileList) {
        super(cloudletId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelCpu, utilizationModelRam, utilizationModelBw, record, fileList);
    }

    @Override
    public int[] getAllResourceId() {
        return super.getAllResourceId();
    }

    @Override
    public double getActualCPUTime() {
        return super.getActualCPUTime();
    }

    public double getAccumulatedBwCost() {
        return accumulatedBwCost;
    }

    @Override
    public long getCloudletFileSize() {
        return super.getCloudletFileSize();
    }

    @Override
    public long getCloudletOutputSize() {
        return super.getCloudletOutputSize();
    }

    public double getCostPerBw() {
        return costPerBw;
    }

    @Override
    public double getCostPerSec() {
        return super.getCostPerSec();
    }

    @Override
    public double getCostPerSec(int resId) {
        return super.getCostPerSec(resId);
    }

    @Override
    public double getProcessingCost() {
        return super.getProcessingCost();
    }

    @Override
    public double getSubmissionTime() {
        return super.getSubmissionTime();
    }

    @Override
    public double getSubmissionTime(int resId) {
        return super.getSubmissionTime(resId);
    }
     

    @Override
    public double getExecStartTime() {
        return super.getExecStartTime();
    }

    @Override
    public double getFinishTime() {
        return super.getFinishTime();
    }
    

    
    
}
