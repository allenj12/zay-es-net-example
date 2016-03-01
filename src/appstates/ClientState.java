/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstates;

import client.ClientMain;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.Message;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;
import com.simsilica.es.client.EntityDataClientService;
import components.Model;
import components.Position;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Input;
import util.Update;
import util.Globals;

/**
 *
 * @author joe
 */
public class ClientState extends AbstractAppState implements ClientStateListener{
    
    private final Client client;
    private EntityDataClientService edcs;
    public static final Object lock = new Object();
    
    public ClientState(){
        this.client = initializeClient();
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        this.edcs = new EntityDataClientService(Globals.CHANNEL);
        
        client.addClientStateListener(this);
        
        Serializer.registerClasses(Input.class,
                Update.class,
                Position.class,
                Model.class);
        
        client.getServices().addService(edcs);
 
        client.start();
        edcs.start();
        
        synchronized(lock){
            if(!client.isConnected()){
                try {
                    System.out.println();
                    System.out.println("wating for connection to complete");
                    System.out.println();
                    lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientState.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private Client initializeClient(){
        Client cl = null;
        
        try{
            cl = Network.connectToServer("localhost", Globals.PORT);
        }catch(IOException ex){
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(cl != null){
            return cl;
            
        }
        else{
            return null;
        }
    }
    
    @Override
    public void cleanup(){
        Message message = new Update("I am disconnecting now");
        client.send(message);
        client.close();
        super.cleanup();
    }
    
    public Client getClient(){
        return this.client;
    }
    
    public EntityDataClientService getClientService(){
        return this.edcs;
    }
    
    @Override
    public void clientConnected(Client c){
        synchronized(lock){
            System.out.println("ClientConnected Called notifying object");
            lock.notify();
        }
    }
    
    @Override
    public void clientDisconnected(Client c, DisconnectInfo info){
        
    }
    
}
