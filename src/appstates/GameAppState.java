package appstates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import com.simsilica.es.Filters;
import com.simsilica.es.ObservableEntityData;
import components.Model;
import components.Position;
import java.util.Random;

public class GameAppState extends AbstractAppState {

    private ObservableEntityData ed;
    private EntitySet shipSet;
    private SimpleApplication app;
    private final int min = -10;
    private final int max = 10;
    private final Random rand = new Random();

    @Override
    public void initialize(AppStateManager stateManager, Application app) {

        this.app = (SimpleApplication) app;
        this.ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();

        EntityId ship = ed.createEntity();
        this.ed.setComponents(ship,
                new Position(new Vector3f(0f, -20f, 0f)),
                new Model(Model.Cube));
        
        shipSet = ed.getEntities(
                Filters.fieldEquals(Model.class, "name", Model.Cube),
                Model.class,
                Position.class
        );
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void update(float tpf) {
        Vector3f position = new Vector3f(randomRangedFloat(),randomRangedFloat(), 0f);
        
        System.out.println(position);
        
        shipSet.applyChanges();
        shipSet.stream().findFirst().ifPresent(e -> {
            System.out.println("Found");
            e.set(new Position(position));
        });  
    }
    
    private float randomRangedFloat(){
        return rand.nextFloat() * (max - min) + min; 
    }

}