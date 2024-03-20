package lab5.Managers;

import lab5.City.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Класс Validator, для проверки всех полей города на валидность, а также для проверки валидного парсинга
 *
 * @author Timur
 */
public class Validator {
    /**
     * Метод для проверки строки из файла на валидность при парсинге
     * @param line на вход подается строка из файла
     * @return true, если строка валидная и false в противном случае
     */
    public static boolean validatepars(String line){
        String ans="";
        String[] parts = line.split(",");
        if (parts.length!=15){              // второй способ, if((parts[11] == null && parts.length()!=12) || (parts[11] != null && parts.length()!=15)) return false
            Console.printError("в файле не заполенены все поля!");
            return false;
        }
        try{
            int id = Integer.parseInt(parts[0].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: на месте id ожидается тип int;";
            }else{ans += "на месте id ожидается тип int; ";}
        }
        String name = parts[1].trim();
        try{
            int x = Integer.parseInt(parts[2].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+= "на месте X ожидается тип int; ";
        }
        try{
            double Y = Double.parseDouble(parts[3].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте Y ожидается тип double; ";
        }
        try{
            Date creationDate = ParseCSV.parseDatefromFile(parts[4].trim());
        }catch(ParseException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте creationDate ожидается правильная расстановка даты; ";
        }
        try{
            Long area = Long.parseLong(parts[5].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте area ожидается тип long; ";
        }
        try{
            Long population = Long.parseLong(parts[6].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте population ожидается тип Long; ";
        }
        try{
            Long metersAboveSeaLevel = Long.parseLong(parts[7].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте metersAboveSeaLevel ожидается тип Long; ";
        }
        try{
            long telephonecode = Long.parseLong(parts[8].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте telephonecode ожидается тип Long; ";
        }
        if (parts[9].trim().isEmpty() || parts[9].trim().equalsIgnoreCase("null")){
            Goverment goverment = null;
        }else{
            try{
                Goverment goverment = Goverment.valueOf(parts[9].trim());
            }catch(IllegalArgumentException e){
                if (ans.isEmpty()){
                    ans = "В файле введены не правильные типы данных: ";
                }
                ans+="на месте goverment ожидаются константы типа Goverment (DESPOTISM,CORPORATOCRACY,TOTALITARIANISM) или null (чтобы получить null, оставьте данное поле пустым) ; ";
            }}
        if (parts[10].trim().isEmpty() || parts[10].trim().equalsIgnoreCase("null")){
            StandartOfLiving standartOfLiving = null;
        }
        else{
            try{
                StandartOfLiving standartOfLiving= StandartOfLiving.valueOf(parts[10].trim());
            }catch(IllegalArgumentException e){
                if (ans.isEmpty()){
                    ans = "В файле введены не правильные типы данных: ";
                }
                ans+="на месте standartOfLiving ожидаются константы типа StandartOfLiving (ULTRA_HIGH, VERY_HIGH, LOW, ULTRA_LOW, NIGHTMARE) или null (чтобы получить null, оставьте данное поле пустым); ";
            }}
        String humanName = parts[12].trim(); //Подумать как при парсинге можно сделать человека = null
        try{
            long age = Long.parseLong(parts[13].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+=" на месте age ожидается тип long; ";
        }
        try{
            int height = Integer.parseInt(parts[14].trim());
        }catch(NumberFormatException e){
            if (ans.isEmpty()){
                ans = "В файле введены не правильные типы данных: ";
            }
            ans+="на месте height ожидается тип int; ";
        }
        if (ans.isEmpty()){ return true;}
        else {
            ans = ans.substring(0,ans.length()-2)+"!";
            Console.printError(ans);
            return false;}
    }
    /**
     *  Проверка на валидность названия города
     * @param name подается на вход строка
     * @return true - если название удовлетворяет условиям, иначе false
     */
    public static boolean validateName(String name){
        if (name == null ||name.isEmpty() || name.matches("0-9")) return false;
        return true;
    }

    /**
     * Проверка на валидность площади
     * @param area подается на вход площадь
     * @return true - если удовлетворяет ограничениям, иначе false
     */
    public static boolean validateArea(long area){
        if (area<=0) return false;
        return true;
    }

    /**
     * Проверка на валидность население
     * @param population подается население
     * @return true - если значение удовлетворяет ограничениям, иначе false
     */
    public static boolean validatePopulation(Long population){
        if(population == null || population <= 0) return false;
        return true;
    }

    /**
     * Проверка на валидность телефонного кода
     * @param telephoneCode подается код
     * @return true - если значение удовлетворяет ограничениям, иначе false
     */
    public static boolean validateTelephoneCode(long telephoneCode){
        if (telephoneCode<=0 || telephoneCode > 100000) return false;
        return true;
    }

    /**
     * Проверка на валидность человека
     * @param human подается human
     * @return true - если все его поля удовлетворяют ограничениям, иначе false
     */
    public static boolean validateHuman(Human human){
        if (human !=null){
            if (human.getName() == null || human.getName().isEmpty())
                return false;
            if (!validateHumanAge(human.getAge()))
                return false;
            if (!validateHumanHeight(human.getHeight()))
                return false;
            return true;}
        else return true;
    }

    /**
     * Проверка на валидность возраст человека
     * @param age подается возраст
     * @return true - если значение удовлетворяет ограничениям, иначе false
     */
    public static boolean validateHumanAge(long age){
        if (age<= 0 | age>=100){
            return false;
        }
        return true;
    }

    /**
     * Проверка на валидность роста человека
     * @param height подается рост
     * @return true - если значение удовлетворяет ограничениям, иначе false
     */
    public static boolean validateHumanHeight(int height){
        if (height<50 | height>250){
            return false;
        }
        return true;
    }

    /**
     * Проверка на валидность координат города
     * @param coordinates подаются координаты
     * @return true - если поля удовлетворяют ограничениям, иначе false
     */
    public static boolean validateCoordinates(Coordinates coordinates){
        if (coordinates.getX()<-579 || Double.valueOf(coordinates.getY()) == null) return false;
        return true;
    }

    /**
     * проверка на валидность города
     * @param city подается город
     * @return true - если все поля удовлетворяют ограничениям, иначе false
     */
    public static boolean validateCity(City city){
        if (validateId(city.getId()) && validateArea(city.getArea()) && validateName(city.getName()) && validateCoordinates(city.getCoordinates()) && validateArea(city.getArea()) && validatePopulation(city.getPopulation())
        &&validateTelephoneCode(city.getTelephoneCode()) && validateHuman(city.getGovernor())) return true;
        return false;
    }

    /**
     * Проверка на валидность id
     * @param id подается id
     * @return true - если значение поля удовлетворяет ограничениям, иначе false
     */
    public static boolean validateId(Integer id){
        if (id<=0) return false;
        return true;
    }

    /**
     * Проверка на повторяющиеся id
     * @param listId подается список всех id, полученных при парсинге
     * @param genId сам id
     * @return true - если данный id уникальный, иначе false
     */
    public static boolean validateSameId(ArrayList<Integer> listId,Integer genId) {
        if (listId.contains(genId)) {
            return false;
        } else return true;
    }

}
