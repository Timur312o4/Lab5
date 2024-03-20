package lab5.Commands;


import lab5.City.Generators.GeneratorCity;
import lab5.Managers.CollectionManager;
import lab5.Managers.Console;
import lab5.exceptions.BuildObjectException;
import lab5.exceptions.IncorrectValueEntryException;
import lab5.exceptions.MustBeNotEmptyException;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда add - добавить новый элемент в коллекцию
 *
 * @author Timur
 */
public class Add extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Add
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public Add(Console console, CollectionManager collectionManager){
        super("add","добавить элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Добавить новый элемент в коллекцию
     * @param args аргумент команды*
     */
    @Override
    public void execute(String[] args) {
        try{
            if (args.length != 1) throw new WrongArgumentsException();
            Console.println("Введите параметры города");
            collectionManager.addToCollection(GeneratorCity.generate());
            Console.println("Город был успешно создан и добавлен в коллекцию");
    }catch (IncorrectValueEntryException|WrongArgumentsException | MustBeNotEmptyException | NumberFormatException | BuildObjectException e){
            Console.printError(e.getMessage());
        }}
    @Override
    public void describe(){
        Console.println(this+ ": добавляет элемент в коллекцию");
    }
    @Override
    public String toString(){
        return "add";
    }
}
