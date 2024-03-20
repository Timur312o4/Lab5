package lab5.Commands;

import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда sumOfMetersAboveSeaLevel - складывает все поля metersAboveSeaLevel элементов из коллекции
 */
public class SumOfMetersAboveSeaLevel extends Command{
    /**
     * менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса SumOfMetersAboveSeaLevel
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public SumOfMetersAboveSeaLevel(Console console, CollectionManager collectionManager){
        super("SumOfMetersAboveSeaLevel","");
        this.console=console;
        this.collectionManager = collectionManager;
    }

    /**
     * складывает все поля metersAboveSeaLevel элементов из коллекции
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length != 1) throw new WrongArgumentsException();
        collectionManager.sumOfMetersAboveSeaLevelCollection();
    }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }}
    @Override
    public void describe(){
        Console.println(this + ": вывести сумму всех значений поля " +this);
    }
    @Override
    public String toString(){
        return "sumOfMetersAboveSeaLevel";
    }
}
