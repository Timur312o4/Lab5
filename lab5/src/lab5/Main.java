package lab5;
import lab5.City.*;
import lab5.Commands.*;
import lab5.Managers.CollectionManager;
import lab5.Managers.CommandManager;
import lab5.Managers.Console;
import lab5.Managers.ParseCSV;
import lab5.exceptions.NotEnoughRightException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Главный класс приложения, он запускается при старте
 *
 * @author Timur
 * */
public class Main {
    /**
     * Запуск приложения
     *
     * @param args аргументы запуска
     */
    public static void main(String[] args) {
        ParseCSV parseCSV = new ParseCSV();
        Vector<City> cities = new Vector<>();
        String path = System.getenv("Lab5_data");
        if (path!=null){
            File file = new File(path);
            try {
                if (file.exists()){
                if (file.canRead()) {
                    cities = parseCSV.getVector(file);
                } else {throw new NotEnoughRightException();}}
                else throw new FileNotFoundException();
            }catch (NotEnoughRightException e){
                System.err.println(e.getMessage());
                System.exit(0);
        }catch(FileNotFoundException e){
                System.err.println("Данного файла не существует!");
                System.exit(0);
            }}
        else{
            System.out.println("У вас отсутствует переменная окружения 'Lab5-data'!");
            System.exit(0);
        }
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.setCityCollection(cities);
        CommandManager commands= new CommandManager(
                new Add(new Console(),collectionManager),
                new Clear(new Console(),collectionManager),
                new Show(new Console(),collectionManager),
                new RemoveById(new Console(),collectionManager),
                new Info(new Console(),collectionManager),
                new GroupCountingByMetersAboveSeaLevel(new Console(),collectionManager),
                new InsertAt(new Console(), collectionManager),
                new PrintAscending(new Console(), collectionManager),
                new RemoveLower(new Console(),collectionManager),
                new Save(new Console(),collectionManager),
                new SumOfMetersAboveSeaLevel(new Console(),collectionManager),
                new UpdateId(new Console(),collectionManager),
                new SortCommand(new Console(),collectionManager));
        commands.addCommand("help",new Help(new Console(),commands));
        commands.addCommand("history",new History(new Console(),commands));
        Executor executor = new Executor(commands);
        commands.addCommand("execute_script",new ExecuteScript(new Console(),executor));
        commands.addCommand("exit",new Exit(new Console()));
        executor.userMode();
    }
}