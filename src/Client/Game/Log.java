package Client.Game;

import BoardElement.Character.ICharacter;
import Client.Command.ICommand;
import Client.Command.PlayerAttackCommand;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log implements IGame{
    private Date fecha;
    private ICommand command;
    String resultado;

    public Log(ICommand command) {
        this.command = command;
        fecha = new Date(System.currentTimeMillis());
    }

    @Override
    public void surrender(ICommand command) {

    }

    @Override
    public void attack(ICommand command) {
        PlayerAttackCommand playerAttackCommand = (PlayerAttackCommand) command;
        //obtener de comand las vidas antes y despues de atacar
        for (int i = 0; i<playerAttackCommand.getChars().size(); i++){
            ICharacter character = playerAttackCommand.getChars().get(i);
            int damage = playerAttackCommand.getVidasAntes().get(i) - playerAttackCommand.getVidasDespues().get(i);
            resultado = resultado.concat("Enemigo "+ character.getName() + " ha recibido " + Integer.toString(damage) + "% de daño. \n");
        }

    }

    private void formatResultado(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        resultado = resultado.concat(simpleDateFormat.format(fecha) + "\n" + command.getCommandText() + "\n");
    }
}