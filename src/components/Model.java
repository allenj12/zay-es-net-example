/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.jme3.network.serializing.Serializable;
import com.simsilica.es.EntityComponent;

@Serializable
public class Model implements EntityComponent {
    private String name;
    public final static String SpaceShip = "SpaceShip";
    public final static String BasicInvader = "BasicInvader";
    public final static String Bullet = "Bullet";
    
    public Model(){
    }

    public Model(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Model[" + name + "]";
    }
}
