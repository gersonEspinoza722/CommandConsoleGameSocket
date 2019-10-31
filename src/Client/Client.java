/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Armando
 */
public abstract class Client {
    
    private String hostName;
    private int portNumber;
    
    private String userName;
    private int id;
    private ClientType type;
    
    private Socket socket = null;
    protected ObjectInputStream reader;
    protected ObjectOutputStream writer;
    protected IServerMessageHandler serverMessageHandler;

    public Client(String hostName, int portNumber, ClientType clientType) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.type = clientType;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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

    public IServerMessageHandler getServerMessageHandler() {
        return serverMessageHandler;
    }

    public void setServerMessageHandler(IServerMessageHandler serverMessage) {
        this.serverMessageHandler = serverMessage;
    }
    
    
    public void run(){
        try{
            System.out.println("corre cliente");
            this.socket = new Socket(this.hostName,this.portNumber);
            
            OutputStream outputStream = socket.getOutputStream();
            this.writer = new ObjectOutputStream(outputStream);
            
            InputStream inputStream = socket.getInputStream();
            this.reader = new ObjectInputStream(inputStream);
            
            DataInputStream idReceiver = new DataInputStream(inputStream);
            int assignedId = idReceiver.readInt();
            this.id = assignedId;
            
            ClientThread clientThread = new ClientThread(this,reader);
            clientThread.setServerMessageHandler(serverMessageHandler);
            clientThread.start();
        }
        catch(IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void sendMessage(Message message){
        try{
            this.writer.writeObject(message);
        }
        catch(IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
