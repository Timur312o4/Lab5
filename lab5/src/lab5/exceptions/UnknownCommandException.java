package lab5.exceptions;

/**
 * Выбрасывается, если ввести неизвестную команду
 */
public class UnknownCommandException extends Exception {
    @Override
    public String getMessage(){
        return "Ошибка. Введена неизвестная команда!";
    }
}
