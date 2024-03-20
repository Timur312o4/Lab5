package lab5.Managers;

import lab5.Commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер команд
 */
public class CommandManager {
    private final Command clear;
    private final Command add;
    private final Command groupCountingByMetersAboveSeaLevel;
    private final Command info;
    private final Command insertAt;
    private final Command printAscending;
    private final Command removeById;
    private final Command removeLower;
    private final Command save;
    private final Command show;
    private final Command sumOfMetersAboveSeaLevel;
    private final Command updateId;
    private final Command sortCommand;
    private final String[] commandHistory = new String[9];
    private final HashMap<String, Command> commandCollection = new HashMap<>();
    private final ArrayList<Command> commands;

    /**
     * Конструктор класса CommandManager
     * @param add команда add
     * @param clear команда clear
     * @param show команда show
     * @param removeById команда removeById
     * @param info команда Info
     * @param groupCountingByMetersAboveSeaLevel команда groupCountingByMetersAboveSeaLevel
     * @param insertAt команда insertAt
     * @param printAscending команда printAscending
     * @param removeLower команда removeLower
     * @param save команда save
     * @param sumOfMetersAboveSeaLevel команда sumOfMetersAboveSeaLevel
     * @param updateId команда updateId
     * @param sortCommand команда sortCommand
     */
    public CommandManager(Command add, Command clear, Command show, Command removeById, Command info,  Command groupCountingByMetersAboveSeaLevel, Command insertAt, Command printAscending, Command removeLower, Command save, Command sumOfMetersAboveSeaLevel, Command updateId,Command sortCommand) {
        this.clear = clear;
        this.add = add;
        this.groupCountingByMetersAboveSeaLevel = groupCountingByMetersAboveSeaLevel;
        this.info = info;
        this.insertAt = insertAt;
        this.printAscending = printAscending;
        this.removeById = removeById;
        this.removeLower = removeLower;
        this.save = save;
        this.show = show;
        this.sumOfMetersAboveSeaLevel = sumOfMetersAboveSeaLevel;
        this.updateId = updateId;
        this.sortCommand=sortCommand;
        commandCollection.put("add",add);
        commandCollection.put("clear",clear);
        commandCollection.put("show",show);
        commandCollection.put("removebyid",removeById);
        commandCollection.put("info",info);
        commandCollection.put("groupcountingbymetersabovesealevel",groupCountingByMetersAboveSeaLevel);
        commandCollection.put("insertat",insertAt);
        commandCollection.put("printascending",printAscending);
        commandCollection.put("removelower",removeLower);
        commandCollection.put("save", save);
        commandCollection.put("sumofmetersabovesealevel",sumOfMetersAboveSeaLevel);
        commandCollection.put("updateid",updateId);
        commandCollection.put("sort",sortCommand);
        commands = new ArrayList<Command>(Arrays.asList(clear, add, groupCountingByMetersAboveSeaLevel, info, insertAt, printAscending, removeById, removeLower, save, show, sumOfMetersAboveSeaLevel, updateId,sortCommand));
    }

    /**
     * Возвращает коллекцию команд
     * @return коллекцию commandCollection
     */
    public HashMap<String, Command> getCommandCollection() {
        return commandCollection;
    }

    /**
     * добавить команду в коллекцию команд
     * @param string1 название команды
     * @param command команда
     */
    public void addCommand(String string1,Command command){
        commandCollection.put(string1,command);
        commands.add(command);
    }

    /**
     * Добавить команду в историю
     * @param command1 команда
     */
    public void addToCommandHistory(Command command1) {
        if (commands.contains(command1)) {
            for (int i = 8; i > 0; i--) {
                commandHistory[i] = commandHistory[i - 1]; // мы должны при применении команды history вывести последние 9 команд не включая его или же включая его
            }
            commandHistory[0] = command1.toString();
        }
    }

    /**
     * Вывести подробную справку по командам
     */
    public void help(){
        for (Command command: commandCollection.values()){
            command.describe();
        }
    }

    /**
     * Получить историю команд
     */
    public void getCommandHistory() {
        for (String str: commandHistory){
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
