/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author joe
 */

//only used for console messages to know we are connected etc...
@Serializable
public class Update extends AbstractMessage {
    private String str;
    
    public Update(){
        
    }
    
    public Update(String s){
        this.str = s;
    }
    
    public String getString(){
        return this.str;
    }
}
