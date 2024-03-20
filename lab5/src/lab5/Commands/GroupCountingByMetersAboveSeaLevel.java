package lab5.Commands;

import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.WrongArgumentsException;

/**
 *  Команда groupCountingByMetersAboveSeaLevel - группирует элементы коллекции по значению поля metersAboveSeaLevel и выводит количество элементов в каждой группе
 * @author Timur
 */

public class GroupCountingByMetersAboveSeaLevel extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса GroupCountingByMetersAboveSeaLevel
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public GroupCountingByMetersAboveSeaLevel(Console console, CollectionManager collectionManager){
        super("groupCountingByMetersAboveSeaLevel","");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Группирует элементы коллекции по полю metersAboveSeaLevel
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length != 1) throw new WrongArgumentsException();
            collectionManager.groupCountingCollectionByMetersAboveSeaLevel();
            Console.println("UpdateId command");
    }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }}

    @Override
    public String toString(){
        return "groupCountingByMetersAboveSeaLevel";
    }
    @Override
    public void describe(){
        System.out.println(this+": сгруппирует элементы коллекции по значению данного поля и выведет кол-во элементов в каждой группе");
    }
}
