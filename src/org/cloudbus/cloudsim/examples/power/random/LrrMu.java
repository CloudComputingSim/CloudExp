package org.cloudbus.cloudsim.examples.power.random;

import java.io.IOException;

/**
 * A simulation of a heterogeneous power aware data center that applies the Local Regression Robust
 * (LRR) VM allocation policy and Minimum Utilization (MU) VM selection policy.
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
public class LrrMu {

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
		String vmAllocationPolicy = "lrr"; // Local Regression Robust (LRR) VM allocation policy
		String vmSelectionPolicy = "mu"; // Minimum Utilization (MU) VM selection policy
		String parameter = "1.2"; // the safety parameter of the LRR policy

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
