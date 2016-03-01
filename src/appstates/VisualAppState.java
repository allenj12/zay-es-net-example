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
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import com.simsilica.es.client.EntityDataClientService;
import components.Name;
import components.Position;
import java.util.HashMap;
import java.util.Set;
import util.ModelFactory;

/**
 *
 * @author joe
 */
public class VisualAppState extends AbstractAppState{
    
    private SimpleApplication app;
    private EntityData ed;
    private EntitySet entities;
    private EntityDataClientService edcs;
    private HashMap<EntityId, Spatial> models;
    private ModelFactory modelFactory;
    
    public VisualAppState(){
        models = new HashMap<>();
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        this.app = (SimpleApplication)app;        
        this.edcs = stateManager.getState(ClientState.class).getClientService(); //might have to do this per update
        this.ed = this.edcs.getEntityData();
        this.entities = ed.getEntities(Position.class, Name.class);
        
        System.out.println("");
        System.out.println(stateManager.getState(ClientState.class).getClient().isConnected());
        System.out.println("");
        
        
        this.app.getViewPort().setBackgroundColor(ColorRGBA.DarkGray);

        app.getCamera().lookAt(Vector3f.UNIT_Z, Vector3f.UNIT_Y);
        app.getCamera().setLocation(new Vector3f(0, 0, 60));

        DirectionalLight light = new DirectionalLight();
        light.setDirection(new Vector3f(1, 1, -1));
        this.app.getRootNode().addLight(light);

        modelFactory = new ModelFactory(this.app.getAssetManager());
    }
    
    @Override
    public void cleanup() {
        entities.release();
        entities = null;
    }

    @Override
    public void update(float tpf) {
        if (entities.applyChanges()) {
            removeModels(entities.getRemovedEntities());
            addModels(entities.getAddedEntities());
            updateModels(entities.getChangedEntities());
        }
    }

    private void removeModels(Set<Entity> entities) {
        for (Entity e : entities) {
            Spatial s = models.remove(e.getId());
            s.removeFromParent();
        }
    }

    private void addModels(Set<Entity> entities) {
        for (Entity e : entities) {
            System.out.println("added");
            Spatial s = createVisual(e);
            models.put(e.getId(), s);
            updateModelSpatial(e, s);
            this.app.getRootNode().attachChild(s);
        }
    }

    private void updateModels(Set<Entity> entities) {
        for (Entity e : entities) {
            System.out.println("updated");
            Spatial s = models.get(e.getId());
            updateModelSpatial(e, s);
        }
    }

    private void updateModelSpatial(Entity e, Spatial s) {
        Position p = e.get(Position.class);
        System.out.println(p.getLocation());
        s.setLocalTranslation(p.getLocation());
        float angles[] = new float[3];
        angles[0] = p.getRotation().x;
        angles[1] = p.getRotation().y;
        angles[2] = p.getRotation().z;
        s.setLocalRotation(new Quaternion(angles));
    }

    private Spatial createVisual(Entity e) {
        Name model = e.get(Name.class);
        Vector3f test = e.get(Position.class).getLocation();
        System.out.println(test);
        System.out.println(model.getName());
        return modelFactory.create();
    }
}
