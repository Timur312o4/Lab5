package lab5.Commands;
import lab5.Managers.Console;

/**
 * Команда exit - выход из программы
 *
 * @author Timur
 */
public class Exit extends Command{
    /**
     * Конструктор класса Exit
     * @param console консоль
     */
    public Exit(Console console){
        super("exit","выход");
        this.console = console;
    }

    /**
     * Завершение программы
     * @param args аргумент команды
     */
    @Override
    public void execute(String[] args){
        Console.println("Завершение программы");
    }
    @Override
    public void describe(){
        Console.println(this +": выходит из программы");
    }
    @Override
    public String toString(){
        return "exit";
    }
}
