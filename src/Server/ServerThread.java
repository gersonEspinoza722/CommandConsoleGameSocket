/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.Message;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Armando
 */
public class ServerThread extends Thread implements Observer {
    
    private Server server = null;
    private Socket socket = null;
    private int id;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private IClientMessageHandler clientMessageHandler;
    private INotificationHandler notificationHandler;
    
    public ServerThread(Socket socket,int clientId,Server server){
        this.socket = socket;
        this.id = clientId;
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getID() {
        return id;
    }

    public void setID(int clientId) {
        this.id = clientId;
    }

    public ObjectInputStream getReader() {
        return reader;
    }

    public void setReader(ObjectInputStream input) {
        this.reader = input;
    }

    public ObjectOutputStream getWriter() {
        return writer;
    }

    public void setWriter(ObjectOutputStream output) {
        this.writer = output;
    }

    public IClientMessageHandler getClientMessageHandler() {
        return clientMessageHandler;
    }

    public void setClientMessageHandler(IClientMessageHandler clientMessage) {
        this.clientMessageHandler = clientMessage;
    }

    public INotificationHandler getNotificationHandler() {
        return notificationHandler;
    }

    public void setNotificationHandler(INotificationHandler notifier) {
        this.notificationHandler = notifier;
    }
    
    
    @Override
    public void run(){
        boolean connected = true;
        
        try{
            OutputStream outputStream = this.socket.getOutputStream();
            this.writer = new ObjectOutputStream(outputStream);
            
            InputStream inputStream = this.socket.getInputStream();
            this.reader = new ObjectInputStream(inputStream);
            
            DataOutputStream idAssigner = new DataOutputStream(outputStream);
            idAssigner.writeInt(this.id);
            
            while(connected){
                Message clientMessage = (Message) reader.readObject();
                this.clientMessageHandler.handleClientMessage(clientMessage, server);
            }
        }
        catch (IOException |ClassNotFoundException ex){
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE,null,ex);
            }
    }

    @Override
    public void update(Observable observable, Object obj) {
        this.notificationHandler.handleObservableNotification(writer, obj);
    }
    
    
}
