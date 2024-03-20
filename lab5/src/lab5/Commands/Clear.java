package lab5.Commands;

import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда clear - очистить коллекцию
 *
 * @author Timur
 */
public class Clear extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды Clear
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Очистить коллекцию
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length != 1) throw new WrongArgumentsException();
            collectionManager.clearCollection();
    }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }
    }
    @Override
    public void describe(){
        Console.println(this+": очищает коллекцию");
    }
    @Override
    public String toString(){
        return "clear";
    }
}
