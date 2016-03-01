/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import appstates.ClientState;
import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import appstates.VisualAppState;

/**
 *
 * @author joe
 */
public class ClientMain extends SimpleApplication{
    
    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setVSync(true);
        settings.setFrameRate(60);
        settings.setFullscreen(false);
        settings.setResolution(640, 480);
        ClientMain app = new ClientMain();
        app.setShowSettings(false);
        app.setSettings(settings);
        app.setPauseOnLostFocus(false);
        app.start();
    }
    
    public ClientMain(){
        super(new ClientState(),
                new VisualAppState());
    }

    @Override
    public void simpleInitApp() {
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
}
