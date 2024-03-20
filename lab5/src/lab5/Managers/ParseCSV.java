package lab5.Managers;

import lab5.City.*;
import lab5.exceptions.EmptyFileException;
import lab5.exceptions.SameIdInFileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс ParseCSV - который парсит данные из файла и на основе этих данных создает коллекцию
 * @author Timur
 */
public class ParseCSV {
    /**
     * Парсер, который принимает на вход данные из файла и каждую строку преобразует в элемент коллекции
     * @param file файл
     * @return коллекцию
     */
    public Vector<City> getVector(File file) {
        Vector<City> VectorList = new Vector<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (file.length()==0) throw new EmptyFileException();
            String line;
            HashSet<Integer> idSet = new HashSet<Integer>();
            while ((line = reader.readLine()) != null) {
                    if (Validator.validatepars(line)) {
                        try {
                            String[] parts = line.split(",");
                            int id = Integer.parseInt(parts[0]);
                            if (!idSet.contains(id)) {
                                idSet.add(id);
                            } else throw new SameIdInFileException();
                            String name = parts[1].trim();
                            int x = Integer.parseInt(parts[2].trim());
                            double y = Double.parseDouble(parts[3].trim());
                            Coordinates coordinates = new Coordinates(x,y);
                            Date creationDate= parseDatefromFile(parts[4]);
                            long area = Long.parseLong(parts[5].trim());
                            Long population = Long.parseLong(parts[6].trim());
                            Long metersAboveSeaLevel = Long.parseLong(parts[7].trim());
                            long telephoneCode = Long.parseLong(parts[8].trim());
                            Goverment goverment;
                            StandartOfLiving standartOfLiving;
                            try {
                                goverment = Goverment.valueOf(parts[9].trim());
                            } catch (IllegalArgumentException e) {
                                goverment = null;
                            }
                            try {
                                standartOfLiving = StandartOfLiving.valueOf(parts[10].trim());
                            } catch (IllegalArgumentException e) {
                                    standartOfLiving = null;
                            }
                            Human governor;
                            if (parts[11].trim().equalsIgnoreCase("null")){
                                governor = null;
                            }else {
                                String namegovernor = parts[12].trim();
                                long age = Long.parseLong(parts[13].trim());
                                int height = Integer.parseInt(parts[14].trim());
                                governor = new Human(namegovernor, age, height);
                            }
                            City city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, telephoneCode, goverment, standartOfLiving, governor);
                            if (Validator.validateCity(city)) {
                                VectorList.add(city);
                            } else {
                                System.out.println(city);
                                Console.printError("Ошибка в создании объекта город, в файле введены значения полей не удовлетворяющие ограничениям");
                            }
                        }catch (ParseException e){
                            System.err.println(e.getMessage());
                        }catch (SameIdInFileException e){
                            Console.printError(e.getMessage());
                            Console.printError("Ошибка в создании объекта город, в файле встречаются строки с одинаковыми значениями id.");
                        }
            }}
        } catch(EmptyFileException e){
            Console.printError(e.getMessage());
        }catch (FileNotFoundException e) {
                    Console.printError("Файл не найден" + e.getMessage());
        } catch (java.io.IOException e) {
                    Console.printError(e.getMessage());
        }
        return VectorList;
    }

    /**
     * Парсер даты, для того чтобы можно принять различные форматы дат
     * @param s строка
     * @return дату
     * @throws ParseException вылетает, если дата написанна не типичным образом или же не правильно записана
     */
    public static Date parseDatefromFile(String s) throws ParseException {
        String[] mspat = {"EEE MMM dd HH:mm:ss zzz yyyy", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "MM/dd/yyyy", "MM-dd-yyyy", "dd/MM/yyyy", "dd-MM-yyyy",
                "yyyy/MM/dd", "yyyy-MM-dd", "yyyy/dd/MM", "yyyy-dd-MM"};
        for (String pat : mspat) {
            SimpleDateFormat dateFormat1 = new SimpleDateFormat(pat, Locale.ENGLISH);
            try {
                Date creationDate1 = dateFormat1.parse(s);
                return creationDate1;
            } catch (ParseException e) {
                System.out.print("");
            }
        }
        throw new ParseException("Не удалось распознать дату",-1);
    }
}
