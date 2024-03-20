package lab5.Commands;

import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.WrongArgumentsException;
import java.util.Scanner;

/**
 * Команда save - сохраняет коллекцию в выбранный файл
 */
public class Save extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Save
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Save(Console console, CollectionManager collectionManager){
        super("save","сохранить");
        this.console=console;
        this.collectionManager = collectionManager;
    }

    /**
     * сохраняет коллекцию в указанный файл
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length !=2 || args[1].isEmpty() ) throw new WrongArgumentsException();
            String fileName = args[1].trim();
            collectionManager.saveCityCollection(fileName,collectionManager.getCityCollection());
    }catch(WrongArgumentsException e){
        Console.printError(e.getMessage());
        }}
    @Override
    public void describe(){
        Console.println(this +": сохранить коллекцию в файл");
    }
    @Override
    public String toString(){
        return "save";
    }
}
