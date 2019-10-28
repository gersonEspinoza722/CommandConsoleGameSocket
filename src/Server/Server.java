/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.Client;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Armando
 */
public class Server {
    
    protected int portNumber;
    protected boolean listening = true;
    protected HashMap<Integer , ServerThread> clients;
    protected ArrayList<Observable> observableResources;
    
    protected IClientMessageHandler clientMessageHandler;
    protected INotificationHandler notificationHandler;

    public Server(int portNumber, IClientMessageHandler clientMessageHandler, INotificationHandler notificationHandler) {
        this.portNumber = portNumber;
        this.clientMessageHandler = clientMessageHandler;
        this.notificationHandler = notificationHandler;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public boolean isListening() {
        return listening;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
    }

    public HashMap<Integer, ServerThread> getClients() {
        return clients;
    }

    public void setClients(HashMap<Integer, ServerThread> clients) {
        this.clients = clients;
    }

    public ArrayList<Observable> getObservableResources() {
        return observableResources;
    }

    public void setObservableResources(ArrayList<Observable> observableResources) {
        this.observableResources = observableResources;
    }

    public IClientMessageHandler getClientMessageHandler() {
        return clientMessageHandler;
    }

    public void setClientMessageHandler(IClientMessageHandler clientMessageHandler) {
        this.clientMessageHandler = clientMessageHandler;
    }

    public INotificationHandler getNotificationHandler() {
        return notificationHandler;
    }

    public void setNotificationHandler(INotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    
    
    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(this.portNumber)){
            System.out.println("Started server :D");
            int clientId = 0;
            while(this.listening){
                Socket clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket,clientId,this);
                serverThread.setClientMessageHandler(this.clientMessageHandler);
                serverThread.setNotificationHandler(this.notificationHandler);
                clients.put(clientId, serverThread);
                serverThread.start();
                clientId++;
            }
        }
        catch(IOException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
