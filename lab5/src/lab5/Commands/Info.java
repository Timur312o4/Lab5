package lab5.Commands;

import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда info - выводит основную информацию о коллекции
 */
public class Info extends Command{
    /**
     * Менеджер коллекций
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Info
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Info(Console console, CollectionManager collectionManager){
        super("info","");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит основную информацию о коллекции
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length!=1) throw new WrongArgumentsException();
            collectionManager.infoAboutCollection();
        }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }
    }

    @Override
    public void describe(){
        Console.println(this+": показать информацию о коллекции");
    }
    @Override
    public String toString(){
        return "info";
    }
}
