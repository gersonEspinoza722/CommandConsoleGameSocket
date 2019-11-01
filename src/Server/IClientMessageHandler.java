/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.Message;

import java.io.Serializable;

/**
 *
 * @author Marvin Armando
 */
public interface IClientMessageHandler extends Serializable {
    
    public void handleClientMessage(Message message, Server server);
    
}
