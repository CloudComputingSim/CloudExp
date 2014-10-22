/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudbus.cloudsim.examples;

import java.util.List;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Vm;

/**
 *
 * @author apple
 */
public class Logs {

    public static void printVMList(List<Vm> list, String title) {
        String indent3 = "               ";
        String indent1 = "    ";
        Log.printLine();
        Log.printLine("============================= " + title + " =============================");
        Log.printLine("vm Id()" + indent1 + "UserId()" + indent1 + "Host()" + indent1 + "Datacenter()" + indent1 + "vm Uid()" + indent1 + "CurrentAllocatedBw()"
                + indent1 + "vm.getCurrentAllocatedRam()" + indent1 + "vm.getCurrentAllocatedSize()" + indent1 + "CurrentRequestedBw()"
                + indent1 + "CurrentRequestedMaxMips()" + indent1 + "CurrentRequestedRam()" + indent1 + "CurrentRequestedTotalMips()"
                + indent1 + "TotalUtilizationOfCpu(i)" + indent1 + "TotalUtilizationOfCpuMips(i)");

        Vm vm;

        for (int i = 0; i < list.size(); i++) {
            vm = list.get(i);
            Log.printLine(vm.getId() + indent3 + vm.getUserId() + indent3 + vm.getHost() + indent3 + vm.getHost()/*.getDatacenter()*/ + indent3 + vm.getUid() + indent3 + vm.getCurrentAllocatedBw()
                    + indent3 + vm.getCurrentAllocatedRam() + indent3 + vm.getCurrentAllocatedSize() + indent3 + vm.getCurrentRequestedBw()
                    + indent3 + vm.getCurrentRequestedMaxMips() + indent3 + vm.getCurrentRequestedRam() + indent3 + vm.getCurrentRequestedTotalMips()
                    + indent3 + vm.getTotalUtilizationOfCpu(i) + indent3 + vm.getTotalUtilizationOfCpuMips(i));
        }
        Log.printLine("===============================================================================================");
    }

    public static void printDatacenter(Datacenter datacenter, String title) {
        String indent3 = "               ";
        String indent1 = "    ";
        Log.printLine();
        Log.printLine("============================= " + title + " =============================");
        Log.printLine("Datacenter Id" + indent1 + "Datacenter name");
        Log.printLine(datacenter.getId() + indent1 + datacenter.getName());
        Log.printLine();
        printHostList(datacenter.getHostList(), "Hosts");
    }
    
    public static void printHostList(List<Host> list, String title) {
        String indent3 = "               ";
        String indent1 = "    ";
        Log.printLine();
        Log.printLine("============================= " + title + " =============================");
        Log.printLine("Host Id()" + indent1 + "getAvailableMips()" + indent1 + "getMaxAvailableMips()" + indent1 + "getMaxAvailableMips()"
                + indent1 + "getNumberOfFreePes()" + indent1 + "CurrentRequestedBw()"
                + indent1 + "getTotalMips()" + indent1 + "getVmsMigratingIn.Size()");

        Host host;

        for (int i = 0; i < list.size(); i++) {
            host = list.get(i);
            Log.printLine(host.getId() + indent3 + host.getAvailableMips() + indent3 + host.getMaxAvailableMips() + indent3 + host.getTotalMips()
                    + indent3 + host.getNumberOfFreePes()
                     + indent3 + host.getMaxAvailableMips() + indent3 + host.getTotalMips()
                    + indent3 + host.getVmsMigratingIn().size());
        }
        Log.printLine("===============================================================================================");
    }
}
