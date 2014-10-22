/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mapreduce;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.ResCloudlet;

/**
 *
 * @author apple
 */
public class MapReduceResCloudlet extends ResCloudlet {

    public MapReduceResCloudlet(Cloudlet cloudlet) {
        super(cloudlet);
    }

    public MapReduceResCloudlet(Cloudlet cloudlet, long startTime, int duration, int reservID) {
        super(cloudlet, startTime, duration, reservID);
    }

    @Override
    public boolean setCloudletStatus(int status) {
//        if (getCloudlet().getStatus() == Cloudlet.PAUSED && Master.getInstance().isAllMapFinished() || getCloudlet().getStatus() != Cloudlet.PAUSED) {//zax
//            System.err.println("MapReduceResCloudlet :: status = "+status);
            return super.setCloudletStatus(status);
//        } else {
//            return false;
//        }
    }
}
