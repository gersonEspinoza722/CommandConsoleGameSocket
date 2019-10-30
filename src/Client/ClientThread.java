/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Armando
 */
public class ClientThread extends Thread{
    
    private Client client;
    private ObjectInputStream reader;
    private IServerMessageHandler serverMessageHandler;

    public ClientThread(Client client, ObjectInputStream reader) {
        this.client = client;
        this.reader = reader;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ObjectInputStream getReader() {
        return reader;
    }

    public void setReader(ObjectInputStream reader) {
        this.reader = reader;
    }

    public IServerMessageHandler getServerMessage() {
        return serverMessageHandler;
    }

    public void setServerMessageHandler(IServerMessageHandler serverMessage) {
        this.serverMessageHandler = serverMessage;
    }
    
    @Override
    public void run(){
        boolean connected = true;
        
        while (connected){
            try{
                Message serverMessage = (Message) reader.readObject();
                this.serverMessageHandler.handleServerMessage(serverMessage);
            }
            catch (IOException |ClassNotFoundException ex){
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        
    }
    
}
