/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.simsilica.es.ObservableEntityData;
import com.simsilica.es.server.EntityDataHostedService;
import components.Name;
import components.Position;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ServerListener;
import server.ServerMain;
import util.Globals;
import util.Input;
import util.Update;

/**
 *
 * @author joe
 */
public class ServerState extends AbstractAppState{
    
    private final Server server;
    private EntityDataHostedService edhs;
    private ObservableEntityData ed;
    private SimpleApplication app;
    
    public ServerState(){
        this.server = initializeServer();
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        this.app = (SimpleApplication)app;
        this.ed = app.getStateManager().getState(EntityDataState.class).getEntityData();
        this.edhs = new EntityDataHostedService(Globals.CHANNEL,this.ed);
        
        Serializer.registerClasses(Input.class,
                Update.class,
                Position.class,
                Name.class);
        
        server.getServices().addService(edhs);
        
        server.addMessageListener(new ServerListener(), Input.class, Update.class);
        
        server.start();
        
        System.out.println("Server Running");
    }
    
    @Override
    public void update(float tpf){
        System.out.println("Sending Updates");
        edhs.sendUpdates();
    }
    
    private Server initializeServer(){
        Server serv = null;
        
        try{
            serv = Network.createServer(Globals.PORT);
        } catch(IOException ex){
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(serv != null){
            return serv;
        }
        else{
            return null;
        }
    }
    
    public Server getServer(){
        return this.server;
    }
    
    public EntityDataHostedService getHostedService(){
        return this.edhs;
    }
}
