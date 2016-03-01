/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.simsilica.es.EntityComponent;

/**
 *
 * @author joe
 */
@Serializable
public class Position implements EntityComponent{
    
    private Vector3f location;
    private Vector3f rotation;

    public Position(Vector3f location, Vector3f rotation) {
        this.location = location;
        this.rotation = rotation;
    }

    public Position(Vector3f location) {
        this (location, new Vector3f(0, 0, 0));
    }
    
    public Position(){
    }

    public Vector3f getLocation() {
        return location;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + location + ", " + rotation + "]";
    }
}
