package lab5.Commands;
import lab5.City.Generators.GeneratorCity;
import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.BuildObjectException;
import lab5.exceptions.IncorrectValueEntryException;
import lab5.exceptions.MustBeNotEmptyException;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда removeLower - удаляет элементы, которые меньше заданного элемента
 */
public class RemoveLower extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveLower
     * @param console консоль
     * @param collectionManager коллекции менеджер
     */
    public RemoveLower(Console console, CollectionManager collectionManager){
        super("RemoveLower","");
        this.console=console;
        this.collectionManager = collectionManager;
    }

    /**
     * удаляет элементы, которые меньше заданного элемента
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongArgumentsException();
            collectionManager.removeFromCollectionByLower(GeneratorCity.generate());
        } catch (IncorrectValueEntryException| NumberFormatException| BuildObjectException| MustBeNotEmptyException| WrongArgumentsException e) {
            Console.printError(e.getMessage());
        }
    }

    @Override
    public void describe(){
        Console.println(this+": Удалить из коллекции все элементы, меньше, чем заданный");
    }
    @Override
    public String toString(){
        return "removeLower";
    }
}
