package Client.Game;

import BoardElement.Character.ICharacter;
import BoardElement.Tools.ITool;
import Client.Command.ICommand;
import Client.Command.PlayerAttackCommand;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log implements IGame, Serializable {
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
        for (int i = 0; i<playerAttackCommand.getCharacters().getSize(); i++){
            ICharacter character = playerAttackCommand.getCharacters().getCharacter(i);
            int damage = playerAttackCommand.getVidasAntes().get(i) - playerAttackCommand.getVidasDespues().get(i);
            resultado = resultado.concat("Enemigo "+ character.getName() + " ha recibido " + Integer.toString(damage) + "% de daño. \n");
        }
        resultado = resultado.concat("\n");
        //vidas antes y despues implementar dividido
        formatResultado();

    }

    @Override
    public void addLog(Log log) {
        resultado = log.resultado;
    }

    @Override
    public ITool getWeapon(String weaponName, String warriorName) {
        return null;
    }

    private void formatResultado(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String fechaText = simpleDateFormat.format(fecha) + "\n";
        String commandText = command.getCommandText() + "\n";
        resultado = fechaText.concat(commandText).concat(resultado);
        System.out.println(resultado);
        command.getRealGame().addLog(this);
    }
}
