package lab5.Commands;

import lab5.Managers.CollectionManager;
import lab5.Managers.Console;
import lab5.exceptions.MustBeNotEmptyException;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда removeById - удаляет элемент по айди
 */
public class RemoveById extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveById
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public RemoveById(Console console, CollectionManager collectionManager){
        super("removeById","");
        this.console=console;
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет элемент из коллекции по id
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length==1 ||args[1].isEmpty() || args.length>2 ) throw new WrongArgumentsException();
            Integer id = Integer.parseInt(args[1].trim());
            if (collectionManager.getCityCollection().isEmpty()) throw new MustBeNotEmptyException();
            collectionManager.removeFromCollectionById(id);
        } catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }catch(MustBeNotEmptyException e){
            Console.printError(e.getMessage());
        }
    }
    @Override
    public void describe(){
        Console.println(this+": удалить элемент из коллекции по заданному id");
    }
    @Override
    public String toString(){
        return "removeById";
    }
}
