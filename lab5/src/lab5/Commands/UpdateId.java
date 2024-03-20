package lab5.Commands;
import lab5.City.City;
import lab5.City.Generators.GeneratorCity;
import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.BuildObjectException;
import lab5.exceptions.IncorrectValueEntryException;
import lab5.exceptions.MustBeNotEmptyException;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда updateId - обновить значение элемента коллекции, id которого равен заданному
 */

public class UpdateId extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса UpdateId
     * @param console консоль
     * @param collectionManager менеджер коллекци
     */
    public UpdateId(Console console, CollectionManager collectionManager){
        super("UpdateId","");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Обновляет элемент, у которого id равен заданному
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length==1 ||args[1].isEmpty() || args.length>2 ) throw new WrongArgumentsException();
            int id = Integer.parseInt(args[1]);
            if (id<0){
                System.err.print("Значение id должно быть больше 0! ");
                throw new IncorrectValueEntryException();
            }if (!collectionManager.getIdCities().contains(id)){
                System.err.println("Элемента с таким id нет в коллекции!");
                throw new IncorrectValueEntryException();
            }
            Console.println("Для создания города заполните его поля");
            City city = GeneratorCity.generate();
            city.setId(id);
            collectionManager.updateByIdFromCollection(city,id);
    }catch(IncorrectValueEntryException | WrongArgumentsException | MustBeNotEmptyException | NumberFormatException | BuildObjectException e){
            Console.printError(e.getMessage());
        }}

    @Override
    public void describe(){
        Console.println(this +": обновить значение элемента коллекции по id.");
    }
    @Override
    public String toString(){
        return "updateId";
    }
}
