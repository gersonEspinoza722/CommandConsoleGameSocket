/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.Serializable;

/**
 *
 * @author Marvin Armando
 */
public interface IServerMessageHandler extends Serializable {
    
    public void handleServerMessage(Message message);
    
}
