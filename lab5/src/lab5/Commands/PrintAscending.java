package lab5.Commands;

import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда printAscending - выводит элементы коллекции в порядке возрастания
 * @author Timur
 */
public class PrintAscending extends Command{
    /**
     * менеджер коллекций
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса PrintAscending
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public PrintAscending(Console console, CollectionManager collectionManager){
        super("printAscending","");
        this.collectionManager = collectionManager;
    }

    /**
     * выводит элементы коллекции в порядке возрастания
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length !=1) throw new WrongArgumentsException();
            collectionManager.printAscending();
    }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }}
    @Override
    public void describe(){
        Console.println(this+": вывести элементы в порядке возрастания");
    }
    @Override
    public String toString(){
        return "printAscending";
    }
}
