package lab5.Commands;

import lab5.Executor;
import lab5.Managers.CollectionManager;
import lab5.Managers.Console;
import lab5.exceptions.WrongArgumentsException;

/**
 * Команда execute_script - запуск скрипта
 *
 * @author Timur
 */
public class ExecuteScript extends Command{
    private final Console console;
    /**
     * исполнитель - который работает с пользоавтелем
     */
    private final Executor executor;

    /**
     * Конструктор класса ExecuteScript
     * @param console консоль
     * @param executor исполнитель
     */
    public ExecuteScript(Console console, Executor executor){
        super("execute_script","запустить скрипт");
        this.console = console;
        this.executor=executor;
    }

    /**
     * Запускает скрипт
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args) {
        try{
            if (args.length != 2) throw new WrongArgumentsException();
            executor.scriptMode(args[1].trim());
            executor.setDeepRecursion(0);
    }catch(WrongArgumentsException e){
            Console.printError(e.getMessage());
        }}
    @Override
    public void describe(){
        Console.println(this +": запускает скрипт");
    }
    @Override
    public String toString(){
        return "execute_script";
    }
}
