package lab5.City.Generators;

import lab5.City.Coordinates;
import lab5.Managers.Validator;
import lab5.exceptions.BuildObjectException;
import lab5.exceptions.IncorrectValueEntryException;
import lab5.exceptions.MustBeNotEmptyException;
import java.util.Scanner;

/**
 * Класс GeneratorCoordinates
 * @author Timur
 */
public class GeneratorCoordinates {
    /**
     * Генерация координат
     * @return coordinates
     * @throws BuildObjectException выбрасывается исключение при ошибке создания объекта
     */
    public Coordinates generate() throws  BuildObjectException{
        Coordinates coor = new Coordinates(this.reqX(),reqY());
        if (!Validator.validateCoordinates(coor)) throw new BuildObjectException();
        return coor;
    }

    /**
     * Запрашивает у пользователя координату X
     * @return x
     */
    public int reqX(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Введите значение X: (для Coordinates, тип int)");
            try {
                String userInput = sc.nextLine().trim();
                if (userInput.isEmpty()) throw new MustBeNotEmptyException();
                int x = Integer.parseInt(userInput);
                if (x > -579) {
                    return x;
                } else throw new IncorrectValueEntryException();
            } catch (IncorrectValueEntryException e) {
                System.err.println("не верно введено значение для переменной X, ожидается значение координаты > -579, повторите попытку,");
            } catch (NumberFormatException e){
                System.err.println("Неверный формат данных, ожидается тип int, Повторите попытку:");
            }catch (MustBeNotEmptyException e){
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя координату y
     * @return y
     */
    public double reqY(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Введите значение Y: (для Coordinates, тип double)");
            try{
                String userInput=sc.nextLine();
                if (userInput != null){
                    double y = Double.parseDouble(userInput);
                    return y;
                }else throw new MustBeNotEmptyException();
            }catch(MustBeNotEmptyException e){
                System.err.println(e.getMessage());
            }catch(NumberFormatException e){
                System.err.println("Неверный формат данных, ожидается тип double (без суффикса f и F), Повторите попытку:");
            }
        }

        }
}
