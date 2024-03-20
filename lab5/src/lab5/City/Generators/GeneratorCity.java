package lab5.City.Generators;
import lab5.City.*;
import lab5.Managers.Validator;
import lab5.exceptions.BuildObjectException;
import lab5.exceptions.IncorrectValueEntryException;
import lab5.exceptions.MustBeNotEmptyException;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс GeneratorCity
 * @author Timur
 */
public class GeneratorCity {
    private final static Scanner sc = new Scanner(System.in);

    /**
     * Генирирует объект класса City
     * @return city
     * @throws IncorrectValueEntryException выбрасывается исключение при некорректном пользовательском вводе
     * @throws NumberFormatException выбрасывается исключение при неправильном вводе числовых данных
     * @throws BuildObjectException выбрасывается исключение при неправильном создании объекта
     * @throws MustBeNotEmptyException выбрасывается исключение при пустом значении
     */
    public static City generate() throws IncorrectValueEntryException, NumberFormatException, BuildObjectException, MustBeNotEmptyException {
        while (true) {
            try {
                GeneratorId genId = new GeneratorId();
                GeneratorCoordinates genCoordinates = new GeneratorCoordinates();
                GeneratorHuman genHuman = new GeneratorHuman();
                Integer id = genId.generate();
                String name = requestName();
                Coordinates coordinates = genCoordinates.generate();
                ZonedDateTime zonedDateTime = ZonedDateTime.now();
                long area = requestArea();
                Long population = requestPopulation();
                Long metersAboveSeaLevel = requestMetersAboveSeaLevel();
                long telephoneCode = requestTelephoneCode();
                Goverment goverment = requestGoverment();
                StandartOfLiving standartOfLiving = requestStandartOfLiving();
                Date creationDate = Date.from(zonedDateTime.toInstant());
                Human human = genHuman.generate();
                City city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, telephoneCode, goverment, standartOfLiving, human);
                if (Validator.validateCity(city)) {
                    return city;
                } else throw new BuildObjectException();
            } catch (BuildObjectException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя Name
     * @return name
     */
    public static String requestName()  {
            while (true) {
                try {
                    System.out.println("Введите имя города");
                    String name = sc.nextLine();
                    if (name.isEmpty()) throw new MustBeNotEmptyException();
                    if (name.matches("[ a-zA-ZА-Яа-яёЁ-]*") && Validator.validateName(name)) {
                        return name;
                    } else throw new IncorrectValueEntryException();
                } catch (IncorrectValueEntryException e) {
                    System.err.println(e.getMessage()+" ожидается, что город содержит в себе какие-то буквы, Повторите попытку.");
                }catch (MustBeNotEmptyException e){
                    System.out.println(e.getMessage());
                }
            }
        }

    /**
     * Запрашивает у пользователя значение поля area
     * @return area
     */
    public static Long requestArea()  {
            while (true) {
                try {
                    System.out.println("Введите значение area: (Для City, тип Integer)");
                    String t = sc.nextLine();
                    if (t.isEmpty()) throw new MustBeNotEmptyException();
                    Long area = Long.parseLong(t);
                    if (area < 0 ) throw new IncorrectValueEntryException();
                    if (Validator.validateArea(area))
                        return area;
                } catch (MustBeNotEmptyException e) {
                    System.err.println(e.getMessage());
                } catch (IncorrectValueEntryException e){
                    System.err.println(e.getMessage()+ " ожидается, что area>0. Повторите попытку");
                }
            }
        }

    /**
     * Запрашивает у пользователя значение населения
     * @return population
     */
    public static Long requestPopulation() {
            while (true) {
                try {
                    System.out.println("Введите значение population: (Для City, тип Integer)");
                    String t = sc.nextLine();
                    if (t.isEmpty()) throw new MustBeNotEmptyException(); // тут уже проверится, что t
                    Long population = Long.parseLong(t);
                    if (population<=0) throw new IncorrectValueEntryException();
                    if (Validator.validatePopulation(population)){
                        return population;
                    }
                } catch (MustBeNotEmptyException e) {
                    System.err.println(e.getMessage());
                } catch (IncorrectValueEntryException e){
                    System.err.println(e.getMessage()+ " ожидается, что population>0. Повторите попытку.");
                }
            }
        }

    /**
     * Запрашивает у пользователя значение поля metersAboveSeaLevel
     * @return metersAboveSeaLevel
     */
    public static Long requestMetersAboveSeaLevel() {
            while (true) {
                try {
                    System.out.println("Введите значение metersAboveSeaLevel: (Для City, тип Float)");
                    String t = sc.nextLine();
                    if (t.isEmpty()) throw new MustBeNotEmptyException();
                    Long metersAboveSeaLevel = Long.parseLong(t);
                    if (metersAboveSeaLevel<-10000 || metersAboveSeaLevel > 10000) throw new IncorrectValueEntryException();
                    return metersAboveSeaLevel;
                } catch (MustBeNotEmptyException e) {
                    System.err.println(e.getMessage());
                }catch (IncorrectValueEntryException e){
                    System.err.println(e.getMessage() + " Ожидается значение расположенное от -10000 до 10000 включительно.");
                }catch(NumberFormatException e){
                    System.err.println("Не верный формат введеных данных");
                }
            }
        }

    /**
     * Запрашивает у пользователя поле telephonecode
     * @return telephonecode
     */
    public static long requestTelephoneCode() {
        while (true) {
            try {
                System.out.println("Введите значение telephoneCode: (Для City, тип Long)");
                String t = sc.nextLine();
                if (t.isEmpty()) throw new MustBeNotEmptyException();
                long telephoneCode = Long.parseLong(t);
                if (Validator.validateTelephoneCode(telephoneCode)){
                    return telephoneCode;
                }else throw new IncorrectValueEntryException();
            } catch (MustBeNotEmptyException e) {
                System.err.println(e.getMessage());
            }catch (IncorrectValueEntryException e){
                System.err.println(e.getMessage() + " ожидается, что 0<=telephonecode<= 100000. Повторите попытку.");
            }

        }
    }

    /**
     * Запрашивает у пользователя константу из политического строя
     * @return null или константу
     */
    public static Goverment requestGoverment() {
        while (true) {
            try {
                System.out.println("Введите значение Goverment: (Для City, тип Goverment)");
                for (Goverment goverment : Goverment.values()) {
                    System.out.print(goverment + " ");
                }
                System.out.println("если хотите, чтобы Goverment равнялся null нажмите просто Enter или пробел и Enter");
                String t = sc.nextLine().toUpperCase();
                if (t.isEmpty() || t.equalsIgnoreCase("null") || t.trim().isEmpty()){
                    return null;
                }
                else{
                    Goverment goverment = Goverment.valueOf(t);
                return goverment;}
            } catch(EnumConstantNotPresentException e){
                System.out.println("Введена не верная коснтанта, повторите попытку");
            } catch(IllegalArgumentException e){
                System.err.println("Введена константа не принадлежащая трем основным, повторите попытку");
            }
        }
    }

    /**
     * Запрашивает у пользователя константу из перечисления
     * @return констунту или null
     */
    public static StandartOfLiving requestStandartOfLiving() {
        while (true) {
            try {
                System.out.println("Введите значение StandartOfLiving: (Для City, тип StandartOfLiving)");
                for (StandartOfLiving standartOfLiving : StandartOfLiving.values()) {
                    System.out.print(standartOfLiving + " ");
                }
                System.out.println("если хотите, чтобы StandartOfLiving равнялся null нажмите просто Enter или пробел и Enter");
                String t = sc.nextLine().toUpperCase();
                if (t.trim().isEmpty() || t.equalsIgnoreCase("null") ) {
                    return null;
                }
                else {
                    StandartOfLiving standartOfLiving = StandartOfLiving.valueOf(t);
                    return standartOfLiving;
                }
            }catch(EnumConstantNotPresentException e){
                System.out.println("Введена не верная константа");
            }catch(IllegalArgumentException e){
                System.err.println("Введена константа, которой нет в списке, повторите попытку");
            }
        }
    }
}



