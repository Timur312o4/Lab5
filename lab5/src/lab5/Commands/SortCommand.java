package lab5.Commands;

import lab5.Managers.CollectionManager;

import lab5.Managers.Console;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда sortCommand - сортирует коллекцию по возрастанию
 */
public class SortCommand extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса SortCommand
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public SortCommand(Console console, CollectionManager collectionManager){
        super("sort","");
        this.console=console;
        this.collectionManager=collectionManager;
    }

    /**
     * Сортирует коллекцию по возрастанию
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length != 1) throw new WrongArgumentsException();
        collectionManager.sortCityCollection();
    }catch(WrongArgumentsException e ){
            Console.printError(e.getMessage());
        }}
    @Override
    public void describe(){
        Console.println(this + ": отсортировать коллекцию");
    }
    @Override
    public String toString(){
        return "sortCommand";
    }
}
