package org.cloudbus.cloudsim.examples.power.random;

import java.io.IOException;

/**
 * A simulation of a heterogeneous power aware data center that applies the Static Threshold (THR)
 * VM allocation policy and Minimum Utilization (MU) VM selection policy.
 * 
 * The remaining configuration parameters are in the Constants and RandomConstants classes.
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
public class ThrMu {

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		boolean enableOutput = true;
		boolean outputToFile = false;
		String inputFolder = "";
		String outputFolder = "";
		String workload = "random"; // Random workload
		String vmAllocationPolicy = "thr"; // Static Threshold (THR) VM allocation policy
		String vmSelectionPolicy = "mu"; // Minimum Utilization (MU) VM selection policy
		String parameter = "0.8"; // the static utilization threshold

		new RandomRunner(
				enableOutput,
				outputToFile,
				inputFolder,
				outputFolder,
				workload,
				vmAllocationPolicy,
				vmSelectionPolicy,
				parameter);
	}

}
