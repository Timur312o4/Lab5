package lab5.City.Generators;
import lab5.City.Human;
import lab5.Managers.Validator;
import lab5.exceptions.BuildObjectException;
import lab5.exceptions.IncorrectValueEntryException;
import lab5.exceptions.MustBeNotEmptyException;

import java.util.Scanner;

/**
 * Класс GeneratorHuman
 * @author Timur
 */
public class GeneratorHuman {

    private final Scanner sc = new Scanner(System.in);

    /**
     * метод генерирует объект класса Human
     * @return объект класса Human
     * @throws BuildObjectException при ошибках пользователя выбрасывается исключение
     */
    public Human generate() throws BuildObjectException {
        System.out.println("Введите null, чтобы сделать мэра = null, или другой ввод для создания мэра");
        String Input = sc.nextLine();
        if (Input.equals("null")){
            return null;
        }
        else{
            Human human = new Human(requestName(),requestAge(),requestHeight());
            if (!Validator.validateHuman(human)) throw new BuildObjectException();
            return human;}
    }

    /**
     * Запрос имени человека
     * @return name
     */
    public String requestName() {
        System.out.println("Введите значение переменной name (для Human, тип String):");
        while (true) {
            try {
                String name = sc.nextLine();
                if (name.isEmpty()) {
                    throw new MustBeNotEmptyException();
                }
                if (name.matches("[a-zA-ZА-Яа-яёЁ]*") && Validator.validateName(name)) {
                    return name;
                } else throw new IncorrectValueEntryException();

            } catch (IncorrectValueEntryException e) {
                System.err.println(e.getMessage());
            } catch (MustBeNotEmptyException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Запрос на возраст human
     * @return age
     */
    public Long requestAge()  {
            System.out.println("Введите значение переменной Age (для Human, тип long):");
            while (true){
                try {
                    String t = sc.nextLine();
                    if (t.isEmpty()){
                        throw new MustBeNotEmptyException();
                    }
                    Long age = Long.parseLong(t.trim());
                    if (Validator.validateHumanAge(age)){
                        return age;
                    }else throw new IncorrectValueEntryException();
                }catch (IncorrectValueEntryException e){
                    System.err.println(e.getMessage()+" ожидается, 0<age<100. Повторите попытку.");
                }catch(NumberFormatException e){
                    System.err.println("Не верно введен тип значения переменной age.");
                }catch (MustBeNotEmptyException e) {
                    System.err.println(e.getMessage());
                }
            }
    }

    /**
     * Запрос на рост Human
     * @return height
     */
    public int requestHeight() {
        System.out.println("Введите значение переменной height (для Human, тип int):");
        while (true){
            try{
                String t = sc.nextLine();
                if (t.isEmpty()) throw new MustBeNotEmptyException();
                int height = Integer.parseInt(t.trim());
                if (Validator.validateHumanHeight(height)){
                    return height;
                }else throw new IncorrectValueEntryException();
            }catch (MustBeNotEmptyException e){
                System.err.println(e.getMessage());
            }catch (IncorrectValueEntryException e){
                System.err.println(e.getMessage() + " ожидается, что 50<=height<=250");
            }catch (NumberFormatException e){
                System.err.println("Не верно введено значение числа");
            }
        }
    }
}



