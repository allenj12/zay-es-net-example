/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author joe
 */
@Serializable
public class Input extends AbstractMessage{
    
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    
}
