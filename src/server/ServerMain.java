/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import appstates.EntityDataState;
import appstates.GameAppState;
import appstates.ServerState;
import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;

/**
 *
 * @author joe
 */
public class ServerMain extends SimpleApplication{
    
    public static void main(String[] args){
        
        AppSettings settings = new AppSettings(true);
        settings.setFrameRate(1);
        ServerMain app = new ServerMain();
        app.setSettings(settings);
        app.start(JmeContext.Type.Headless);
    }
    
    public ServerMain(){
        super(new EntityDataState(),
                new GameAppState(),
                new ServerState());
    }
    
    @Override
    public void simpleInitApp(){
    }
    
    @Override
    public void simpleUpdate(float tpf){
        
    }
}
