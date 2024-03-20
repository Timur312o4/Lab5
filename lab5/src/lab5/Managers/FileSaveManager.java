package lab5.Managers;

import lab5.City.City;

import java.io.*;
import java.util.Vector;

/**
 * Менеджер сохранения коллекции в файл
 * @author Timur
 */
public class FileSaveManager {
    /**
     * Метод который записывает по строчно каждый элемент коллекции
     * @param fileName название файла
     * @param cityVector коллекция
     */
    public static void writeToFile(String fileName, Vector<City> cityVector) {
        String path;
        if (!fileName.substring(Math.max(0,fileName.length()-4)).equals(".csv")){
            path = fileName+".csv";
        }else {
            path = fileName;
        }
        File file = new File(path);
        if (file.canWrite() || !file.exists()) {
            try(PrintWriter writer = new PrintWriter(file)){
                for (City city : cityVector) {
                    String csvString = toCSVstring(city);
                    writer.println(csvString);
                }
            }catch(IOException e){
                System.err.println(e.getMessage());}
        }else{
            System.err.println("Недостаточно прав для записи в файл!");
        }
    }

    /**
     * Преобразование каждого элемента коллекции в строковое представление, где каждые поля разделены запятой, для удобного преобразования в csv формат
     * @param city элемент из коллекции
     * @return строковое представление
     */
    public static String toCSVstring(City city){
        if (city.getGovernor() != null){
            return city.getId()+", "+city.getName()+","+city.getCoordinates().getX()+','+city.getCoordinates().getY()+","+
                city.getCreationDate()+","+ city.getArea()+","+city.getPopulation()+','+city.getMetersAboveSeaLevel()+","+
                city.getTelephoneCode()+","+city.getGoverment()+", "+city.getStandartOfLiving()+", "+","+city.getGovernor().getName()+","+
                city.getGovernor().getAge()+","+city.getGovernor().getHeight();
        }else{
            return city.getId()+", "+city.getName()+","+city.getCoordinates().getX()+','+city.getCoordinates().getY()+","+
                    city.getCreationDate()+","+ city.getArea()+","+city.getPopulation()+','+city.getMetersAboveSeaLevel()+","+
                    city.getTelephoneCode()+","+city.getGoverment()+", "+city.getStandartOfLiving()+",null"+", "+", "+", ";
        }
    }
}
