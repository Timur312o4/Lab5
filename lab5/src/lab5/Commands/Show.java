package lab5.Commands;

import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда show - выводит элементы коллекции в строковом представлении
 */
public class Show extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Show
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Show(Console console, CollectionManager collectionManager){
        super("show","показать коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Показывает элементы коллекции в строковом представлении
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length!=1) throw new WrongArgumentsException();
        collectionManager.showCollection();
    }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }}

    @Override
    public void describe(){
        Console.println(this+": показать информацию об элементах в коллекции");
    }
    @Override
    public String toString(){
        return "show";
    }
}
