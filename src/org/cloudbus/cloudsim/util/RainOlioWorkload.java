/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudbus.cloudsim.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import radlab.rain.Benchmark;
import radlab.rain.workload.olio.OlioGenerator;


/**
 *
 * @author apple
 */
public class RainOlioWorkload implements WorkloadModel {
    
    private ArrayList<Cloudlet> jobs = null; // a list for getting all the
    private final int rating; // a PE rating
    private static final int IRRELEVANT = -1; // irrelevant number
    
    

    public RainOlioWorkload(final String workloadProfileName, final int rating) {
        if (workloadProfileName == null || workloadProfileName.length() == 0) {
			throw new IllegalArgumentException("Invalid trace file name.");
	} else if (rating <= 0) {
			throw new IllegalArgumentException("Resource PE rating must be > 0.");
		}

		this.rating = rating;
                String[] args = new String[]{workloadProfileName};
        try {
            Benchmark.main(args);
        } catch (Exception ex) {
            Logger.getLogger(RainOlioWorkload.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    

    @Override
    public List<Cloudlet> generateWorkload() {
        if (jobs == null) {
		jobs = new ArrayList<Cloudlet>();
        }
//        System.err.println("OlioGenerator.operations.size() = "+OlioGenerator.operations.size());
        for(int i = 0; i < OlioGenerator.operations.size(); i++)
        {
            createJob(i, IRRELEVANT, OlioGenerator.operations.get(i).RUN_TIME, OlioGenerator.operations.get(i).NUM_PROC, IRRELEVANT, IRRELEVANT, IRRELEVANT);
        }
        
        return jobs;
        
    }
    
    
    /**
	 * Creates a Gridlet with the given information and adds to the list
	 * 
	 * @param id a Gridlet ID
	 * @param submitTime Gridlet's submit time
	 * @param runTime Gridlet's run time
	 * @param numProc number of processors
	 * @param reqRunTime user estimated run time
	 * @param userID user id
	 * @param groupID user's group id
	 * @pre id >= 0
	 * @pre submitTime >= 0
	 * @pre runTime >= 0
	 * @pre numProc > 0
	 * @post $none
	 */
	private void createJob(
			final int id,
			final long submitTime,
			final int runTime,
			final int numProc,
			final int reqRunTime,
			final int userID,
			final int groupID) {
		// create the cloudlet
		final int len = runTime * rating;
		UtilizationModel utilizationModel = new UtilizationModelFull();
		final Cloudlet wgl = new Cloudlet(
				id,
				len,
				numProc,
				0,
				0,
				utilizationModel,
				utilizationModel,
				utilizationModel);
		jobs.add(wgl);
	}

    
}
