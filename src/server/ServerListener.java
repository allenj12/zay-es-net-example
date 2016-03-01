/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import util.Input;
import util.Update;

/**
 *
 * @author joe
 */
public class ServerListener implements MessageListener<HostedConnection>{
    
    @Override
    public void messageReceived(HostedConnection source, Message message){
        if(message instanceof Input){
            Input input = (Input) message;
            System.out.println("Client #"+source.getId());
            System.out.println("\t left "+input.left);
            System.out.println("\t right "+input.right);
            System.out.println("\t up "+input.up);
            System.out.println("\t down "+input.down);
        }
        if(message instanceof Update){
            Update update = (Update) message;
            String msg = update.getString();
            System.out.println("Client #"+source.getId()+" received: '"+msg+"'");
        }
    }
    
}
