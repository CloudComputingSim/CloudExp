/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim.power.models;

/**
 * The power model of an HP ProLiant ML110 G5 (1 x [Xeon 3075 2660 MHz, 2 cores], 4GB).
 * http://www.spec.org/power_ssj2008/results/res2011q1/power_ssj2008-20110124-00339.html
 * 
 * If you are using any algorithms, policies or workload included in the power package, please cite
 * the following paper:
 * 
 * Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and Adaptive
 * Heuristics for Energy and Performance Efficient Dynamic Consolidation of Virtual Machines in
 * Cloud Data Centers", Concurrency and Computation: Practice and Experience, ISSN: 1532-0626, Wiley
 * Press, New York, USA, 2011, DOI: 10.1002/cpe.1867
 * 
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 3.0
 */
public class PowerModelSpecPowerHpProLiantMl110G5Xeon3075 extends PowerModelSpecPower {

	/** The power. */
	private final double[] power = { 93.7, 97, 101, 105, 110, 116, 121, 125, 129, 133, 135 };

	/*
	 * (non-Javadoc)
	 * @see org.cloudbus.cloudsim.power.models.PowerModelSpecPower#getPowerData(int)
	 */
	@Override
	protected double getPowerData(int index) {
		return power[index];
	}

}
