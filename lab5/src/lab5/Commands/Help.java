package lab5.Commands;

import lab5.Managers.CommandManager;
import lab5.Managers.Console;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда help - выводит информацию о всех командах
 */
public class Help extends Command{
    /**
     * Менеджер команд
     */
    private final CommandManager commandManger;

    /**
     * Конструктор класса Help
     * @param console консоль
     * @param commandManager менеджер команд
     */
    public Help(Console console, CommandManager commandManager){
        super("help","");
        this.console = console;
        this.commandManger=commandManager;
    }

    /**
     * Выводит информацию о всех командах
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length != 1) throw new WrongArgumentsException();
            commandManger.help();
        }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }
    }
    @Override
    public void describe(){
        Console.println(this+ ": Вывести информацию по доступным командам");
    }
    @Override
    public String toString(){
        return "help";
    }


}
