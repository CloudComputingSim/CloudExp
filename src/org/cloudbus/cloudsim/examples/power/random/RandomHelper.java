/*
 *
 */
package org.cloudbus.cloudsim.examples.power.random;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelNull;
import org.cloudbus.cloudsim.UtilizationModelStochastic;
import org.cloudbus.cloudsim.examples.power.Constants;

/**
 * The Helper class for the random workload.
 * 
 * If you are using any algorithms, policies or workload included in the power package please cite
 * the following paper:
 * 
 * Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and Adaptive
 * Heuristics for Energy and Performance Efficient Dynamic Consolidation of Virtual Machines in
 * Cloud Data Centers", Concurrency and Computation: Practice and Experience, ISSN: 1532-0626, Wiley
 * Press, New York, USA, 2011, DOI: 10.1002/cpe.1867
 * 
 * @author Anton Beloglazov
 * @since Jan 5, 2012
 */
public class RandomHelper {

	/**
	 * Creates the cloudlet list.
	 * 
	 * @param brokerId the broker id
	 * @param cloudletsNumber the cloudlets number
	 * 
	 * @return the list< cloudlet>
	 */
	public static List<Cloudlet> createCloudletList(int brokerId, int cloudletsNumber) {
		List<Cloudlet> list = new ArrayList<Cloudlet>();

		long fileSize = 300;
		long outputSize = 300;
		long seed = RandomConstants.CLOUDLET_UTILIZATION_SEED;
		UtilizationModel utilizationModelNull = new UtilizationModelNull();

		for (int i = 0; i < cloudletsNumber; i++) {
			Cloudlet cloudlet = null;
			if (seed == -1) {
				cloudlet = new Cloudlet(
						i,
						Constants.CLOUDLET_LENGTH,
						Constants.CLOUDLET_PES,
						fileSize,
						outputSize,
						new UtilizationModelStochastic(),
						utilizationModelNull,
						utilizationModelNull);
			} else {
				cloudlet = new Cloudlet(
						i,
						Constants.CLOUDLET_LENGTH,
						Constants.CLOUDLET_PES,
						fileSize,
						outputSize,
						new UtilizationModelStochastic(seed * i),
						utilizationModelNull,
						utilizationModelNull);
			}
			cloudlet.setUserId(brokerId);
			cloudlet.setVmId(i);
			list.add(cloudlet);
		}

		return list;
	}

}
