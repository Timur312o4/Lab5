package lab5.Commands;

import lab5.Managers.CommandManager;
import lab5.Managers.Console;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда history - выводит 9 последних использованных команд
 * @author Timur
 */
public class History extends Command{
    /**
     * Менеджер команд
     */
    private final CommandManager commandManager;

    /**
     * Конструктор команды history
     * @param console консоль
     * @param commandManager менеджер команд
     */
    public History(Console console, CommandManager commandManager){
        super("history","показать последние 9 команд");
        this.console=console;
        this.commandManager=commandManager;
    }

    /**
     * Выводит последние 9 использованных команд
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length != 1) throw new WrongArgumentsException();
            commandManager.getCommandHistory();
    }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }}
    @Override
    public void describe(){
        Console.println(this +": показать последние 9 использованных команд");
    }
    @Override
    public String toString(){
        return "history";
    }
}
