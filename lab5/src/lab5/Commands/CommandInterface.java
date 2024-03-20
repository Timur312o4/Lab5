package lab5.Commands;

/**
 * интерфейс команда
 * @author Timur
 */
public interface CommandInterface {
    /**
     * Вызов команды
     * @param args аргумент команды
     */
    public void execute(String[] args);

    /**
     * Получить описание команды
     */
    public void describe();
}
