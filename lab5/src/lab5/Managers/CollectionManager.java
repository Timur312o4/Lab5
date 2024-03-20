package lab5.Managers;

import lab5.City.City;
import lab5.exceptions.IncorrectValueEntryException;
import lab5.exceptions.WrongArgumentsException;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Класс для работы с коллекцией
 * Методы: добавление, сортировка, удаление, очищение, суммирование по id и т.д.
 */

public class CollectionManager {
    private Vector<City> cityCollection;
    private ZonedDateTime creationDate;

    /**
     * Конструктор класса CollectionManager
     */
    public CollectionManager(){
        cityCollection=new Vector<City>();
        creationDate= ZonedDateTime.now();
    }

    /**
     * Конструктор класса CollectionManager
     * @param collection коллекция городов
     */
    public CollectionManager(Vector<City> collection){
        this.cityCollection=collection;
    }

    /**
     * добавляет город в коллекцию
     * @param city добавляемый город
     */
    public void addToCollection(City city){
        cityCollection.add(city);
    }

    /**
     * удаляет элементы из коллекции меньшие входного города
     * @param city входной город
     */
    public void removeFromCollectionByLower(City city){
        ArrayList<City> removeCities = new ArrayList<>();
        for (City cityInCollection: cityCollection){
            if (cityInCollection.compareTo(city)<0){
                removeCities.add(cityInCollection);
            }
        }
        cityCollection.removeAll(removeCities);
    }

    /**
     * удаляет элемент из коллекции по заданному id
     * @param id заданный айди
     */
    public void removeFromCollectionById(Integer id){
        try{
            boolean flag = true;
            for (City city1: cityCollection){
                if (city1.getId().equals(id)){
                    cityCollection.remove(city1);
                    flag=false;
                    break;
                }
            }if (!flag){
                System.out.println("Данный элемент успешно удален");}
            else throw new WrongArgumentsException();
        }catch(WrongArgumentsException e){
            System.err.println("Элемента с данным id нет в коллекции, для того чтобы узнать какие элементы есть и какие у них id введите команду show");
        }
    }

    /**
     *Выводит информацию о коллекции
     */
    public void infoAboutCollection(){
        System.out.println("Тип данных: " +cityCollection.getClass().getName());
        System.out.println("Дата инициализации: " + creationDate);
        System.out.println("Количество элементов: "+ cityCollection.size());
    }

    /**
     * Обнавляет элемент коллекции по заданному id
     * @param city новый элемент
     * @param id айди по которому обновляется элемент
     */
    public void updateByIdFromCollection(City city, int id){ //спросить, если данного id нет мы должны выкинуть исключение или же просто добавить элемент с новым id
        for (int i=0; i< cityCollection.size();i++){
            City city1=cityCollection.get(i);
            if(city1.getId()==id){
                cityCollection.set(i,city);
            }
    }}

    /**
     * сохраняет коллекцию в указанный файл
     * @param fileName название файла
     * @param city объекты какого класса хранит коллекция
     */
    public void saveCityCollection(String fileName,Vector<City> city){
        try{
            FileSaveManager.writeToFile(fileName,city);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * возвращается коллекция
     * @return коллекцию городов
     */
    public Vector<City> getCityCollection(){
        return cityCollection;
    }

    /**
     * Получить id всех элементов коллекции
     * @return список id
     */
    public ArrayList<Integer> getIdCities(){
        ArrayList<Integer> idList = new ArrayList<>();
        for (City city1: cityCollection){
            idList.add(city1.getId());
        }
        return idList;
    }

    /**
     *  сложить поле metersAboveSeaLevel каждого элемента
     */
    public void sumOfMetersAboveSeaLevelCollection(){
        var sm = 0;
        for (City city1: cityCollection){
            sm+=city1.getMetersAboveSeaLevel();
        }
        System.out.println(sm);
    }

    /**
     * Показать элементы коллекции
     */
    public void showCollection(){
        if (!cityCollection.isEmpty()){
        for (City city:cityCollection){
            System.out.println(city);
        }
    }else{
            System.out.println("[]");
        }}

    /**
     * изменить коллекцию
     * @param collection коллекция городов
     */
    public void setCityCollection(Vector<City> collection){
        cityCollection = collection;
    }

    /**
     * Очистить коллекцию
     */
    public void clearCollection(){
        cityCollection.clear();
    }

    /**
     * Добавить новый элемент в указанную позицию
     * @param position позиция
     * @param city новый элемент
     */
    public void insertAtCollection(int position,City city){ // вроде вопрос отпал
        cityCollection.add(position,city);

    }

    /**
     * сгруппировать элементы коллекции по полю metersAboveSeaLevel
     */
    public void groupCountingCollectionByMetersAboveSeaLevel(){
        HashMap<Long,Integer> groupCityByMeters = new HashMap<>();
        for (City city: cityCollection){
            if (groupCityByMeters.get(city.getMetersAboveSeaLevel())==null){
                groupCityByMeters.put(city.getMetersAboveSeaLevel(),1);
            }else{
                groupCityByMeters.replace(city.getMetersAboveSeaLevel(),groupCityByMeters.get(city.getMetersAboveSeaLevel())+1);
            }
        }
        for (Long i1: groupCityByMeters.keySet()){
            System.out.println(i1+", "+groupCityByMeters.get(i1));
        }
    }

    /**
     * Вывести элементы коллекции в порядке возрастания
     */
    public void printAscending(){
        Vector<City> collectionh = new Vector<>(cityCollection);
        Collections.sort(collectionh);
        for (City city :collectionh){
            System.out.println(city);
        }
    }

    /**
     * Отсортировать коллекцию в порядке возрастания
     */
    public void sortCityCollection(){
        Collections.sort(cityCollection);
    }
}
