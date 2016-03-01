/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstates;

import com.jme3.app.state.AbstractAppState;
import com.jme3.network.serializing.Serializable;
import com.simsilica.es.ObservableEntityData;
import com.simsilica.es.base.DefaultEntityData;

/**
 *
 * @author joe
 */
@Serializable
public class EntityDataState extends AbstractAppState{
    
    private ObservableEntityData entityData;
    
    public EntityDataState() {
        this(new DefaultEntityData());
    }

    public EntityDataState( ObservableEntityData ed ) {
        this.entityData = ed;
    }

    public ObservableEntityData getEntityData() {
        return entityData;
    }

    @Override
    public void cleanup() {
        entityData.close();
        entityData = null; // cannot be reused
    }
}
