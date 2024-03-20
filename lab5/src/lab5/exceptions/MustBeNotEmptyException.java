package lab5.exceptions;

/**
 * Выбрасывается, если значение не должно быть пустым
 *
 * @author Timur
 */
public class MustBeNotEmptyException extends Exception{
    /**
     * Конструктор исключения MustBeNotEmptyException
     */
    public MustBeNotEmptyException(){
    }
    @Override
    public String getMessage(){
        return "Аргумент команды или объект с которым работает команда не должны быть пустыми!";
    }
}
