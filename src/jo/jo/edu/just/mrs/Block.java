/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.edu.just.mrs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
public class Block {
    
    private static int idCounter = 0;
    private int id = 0;
    private int type;
    public static final int NORMAL = 1;
    public static final int SMALL = 2;
    private List<HostLDB> hosts = new ArrayList<HostLDB>();

    public Block(int id, int type) {
        this.id = id;
        this.type = type;
    }

    public Block(int id, int type, ArrayList<HostLDB> hosts) {
        this.id = id;
        this.hosts = hosts;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setHosts(List<HostLDB> hosts) {
        this.hosts = hosts;
    }

    public List<HostLDB> getHosts() {
        return hosts;
    }
    
    public HostLDB getHostAt(int idx){
        return hosts.get(idx);
    }
    
    public void addHost(HostLDB host){
        hosts.add(host);
    }

    public int getType() {
        return type;
    }
    
    public static int getGenerateId(){
        return idCounter++;
    }
    
    
}
