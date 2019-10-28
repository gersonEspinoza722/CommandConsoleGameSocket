/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.Message;
import java.io.ObjectOutputStream;

/**
 *
 * @author Marvin Armando
 */
public interface INotificationHandler {
    
    /**
     *
     * @param clientWriter
     * @param obj
     * @return
     */
    public Message handleObservableNotification(ObjectOutputStream clientWriter, Object obj);
    
}
