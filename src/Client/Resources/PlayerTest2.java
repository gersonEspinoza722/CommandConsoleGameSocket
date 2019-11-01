package Client.Resources;

import Client.ClientType;
import Client.Command.*;
import Client.Game.*;
import Client.IServerMessageHandler;
import Client.Message;
import Client.Player.Player;
import Client.Player.PlayerMessage;
import Client.Player.ServerMessageHandlerPlayer;
import Server.ClientMessageHandler;
import Server.GameServer;
import Server.ServerThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class PlayerTest2 {


    public static void main(String[] args) {


        IServerMessageHandler serverMessageHandlerGame = new ServerMessageHandlerGame(null);


        Player player = new Player("localhost", 9090, ClientType.OBSERVER, "PLAYER");
        IServerMessageHandler serverMessageHandler = new ServerMessageHandlerPlayer(null);
        player.setServerMessageHandler(serverMessageHandler);
        player.run();


        Message registrationMessage = new PlayerMessage("PLAYER", "PLAYER_REGISTRATION", player.getName(), player.getId());
        player.sendMessage(registrationMessage);

        Message subscribeMessage = new PlayerMessage("PLAYER", "ENTER_GAME", "GAME", player.getId());
        player.sendMessage(subscribeMessage);

        //PlayerAttackCommand command = new PlayerAttackCommand("GAME", "PLAYER", "attack warriorName weaponName");
        //Message attackMessage = new PlayerMessage("PLAYER", "ATTACK_MESSAGE", command, player.getId());

        //player.sendMessage(attackMessage);

        PlayerSurrenderCommand command2 = new PlayerSurrenderCommand(0, "GAME");
        Message surrenderMessage = new PlayerMessage("PLAYER", "SURRENDER_MESSAGE", command2, player.getId());
        player.sendMessage(surrenderMessage);

        PlayerEndCommand command3 = new PlayerEndCommand(0, "GAME");
        Message endMessage = new PlayerMessage("PLAYER", "END_MESSAGE", command3, player.getId());
        player.sendMessage(endMessage);

        PlayerReloadCommand command4 = new PlayerReloadCommand(0, "GAME");
        Message reloadMessage = new PlayerMessage("PLAYER", "RELOAD_MESSAGE", command4, player.getId());
        player.sendMessage(reloadMessage);

        PlayerComodinCommand command5 = new PlayerComodinCommand(0, "GAME");
        Message comodinMessage = new PlayerMessage("PLAYER", "COMODIN_MESSAGE", command5, player.getId());
        player.sendMessage(comodinMessage);

        PlayerGetOtherInfoCommand command6 = new PlayerGetOtherInfoCommand(0, "GAME");
        Message getOtherInfoMessage = new PlayerMessage("PLAYER", "INFO_MESSAGE", command6, player.getId());
        player.sendMessage(getOtherInfoMessage);

        PlayerPassCommand command7 = new PlayerPassCommand(0, "GAME");
        Message passMessage = new PlayerMessage("PLAYER", "PASS_MESSAGE", command7, player.getId());
        player.sendMessage(passMessage);

        PlayerChatCommand command8 = new PlayerChatCommand(0, "GAME", "Mensaje para chat");
        Message chatMessage = new PlayerMessage("PLAYER", "CHAT_MESSAGE", command8, player.getId());
        player.sendMessage(chatMessage);

        PlayerFrame frame = new PlayerFrame();

    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////
class PlayerFrame2 extends JFrame{


    public PlayerFrame2(){

        setBounds(600,300,800,600);
        PlayerPanel milamina=new PlayerPanel();

        add(milamina);

        setVisible(true);


    }

}

    class PlayerPanel2 extends JPanel{
        private CommandTokenizer tokenizer = CommandTokenizer.getInstance();
        private Player player;

        //private static final int type = 0;

        public PlayerPanel2() {

            player = new Player("localhost", 9090, ClientType.OBSERVER, "PLAYER");
            IServerMessageHandler serverMessageHandler = new ServerMessageHandlerPlayer(null);
            player.setServerMessageHandler(serverMessageHandler);
            player.run();

            JLabel texto = new JLabel("PANTALLA JUGADOR");
            add(texto);

            commandField = new JTextField(50);
            add(commandField);
            respuesta = new JTextField(50);
            add(respuesta);

            sendCommandButton = new JButton("Enviar comando");
            SendCommand sendRechazo = new SendCommand();
            sendCommandButton.addActionListener(sendRechazo);
            add(sendCommandButton);
        }
        private void sendCommand(){
            Message attackMessage = new PlayerMessage("PLAYER", "ATTACK_MESSAGE",tokenizer.analyzeCommand(commandField.getText()) , player.getId());
            player.sendMessage(attackMessage);

        }

        private class SendCommand implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendCommand();
            }
        }


        private JTextField commandField;
        private JTextField respuesta;
        private JButton sendCommandButton;

}
