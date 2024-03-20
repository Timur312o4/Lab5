package lab5.Commands;

import lab5.City.Generators.GeneratorCity;
import lab5.Managers.Console;
import lab5.Managers.CollectionManager;
import lab5.exceptions.BuildObjectException;
import lab5.exceptions.IncorrectValueEntryException;
import lab5.exceptions.MustBeNotEmptyException;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда insertAt - добавляет новый элемент в коллекции по указанному индексу
 */
public class InsertAt extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса InsertAt
     * @param console консоль
     * @param collectionManager менеджер коллекции
     */
    public InsertAt(Console console, CollectionManager collectionManager){
        super("insertId","");
        this.collectionManager = collectionManager;
    }

    /**
     * Добавляет в коллекцию в указанное место новый элемент
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        try{
            if (args.length !=2 || args[1].isEmpty() ) throw new WrongArgumentsException();
            int position = Integer.parseInt(args[1]);
            if (collectionManager.getCityCollection().size()<=position){
                System.err.println("Заданная позиция больше размера самой коллекции!");
                throw new IncorrectValueEntryException();
            }
            collectionManager.insertAtCollection(position, GeneratorCity.generate());// спросить, а индекс может быть вообще любой?
        }catch(IncorrectValueEntryException | WrongArgumentsException | MustBeNotEmptyException |
           BuildObjectException e){
            Console.printError(e.getMessage());
        } catch(NumberFormatException e){
            Console.printError("Не верно введен index");
        }
    }
    @Override
    public void describe(){
        Console.println(this+": Добавить новый элемент в коллекцию в заданную позицию");
    }
    @Override
    public String toString(){
        return "insertAt index";
    }
}
