package lab5;

import lab5.Commands.Command;
import lab5.Managers.CommandManager;
import lab5.Managers.Console;
import lab5.exceptions.EmptyFileException;
import lab5.exceptions.NotEnoughRightException;
import lab5.exceptions.ScriptRecursionException;
import lab5.exceptions.UnknownCommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс Executor
 * @author Timur
 */
public class Executor {
    private boolean statusScript = false;
    private final static Scanner scanner = new Scanner(System.in);
    private final Map<String, Command> commands;
    private int deepRecursion = 0;
    private final ArrayList<String> fileNamesList = new ArrayList<>();
    private CommandManager commandManager;

    /**
     * Конструктор класса Executor
     * @param commandManager командный менеджер
     */
    public Executor(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.commands = commandManager.getCommandCollection();
    }

    /**
     * Пользовательский ввод команд
     */
    public void userMode() {
        while (true) {
            Console.println("Введите команду,для того чтобы увидеть справку о командах введите help");
            try {
                String[] commandIn = scanner.nextLine().trim().split(" ", 2);
                Command command = commands.get(commandIn[0].toLowerCase());
                if (commandIn[0].equalsIgnoreCase("exit") && commandIn.length == 1) {
                    command.execute(commandIn);
                    break;
                }
                if (command != null) {
                    command.execute(commandIn);
                    commandManager.addToCommandHistory(command);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (UnknownCommandException e) {
                Console.printError(e.getMessage());
            }
        }

    }

    /**
     * Ввод команд с помощью скрипта
     * @param fileName название файла, где лежит скрипт
     */
    public void scriptMode(String fileName) {
        Scanner newScanner = new Scanner(System.in);
        this.statusScript = false;
        try {
            var filePath = new File(fileName);
            if (!filePath.canRead()) throw new NotEnoughRightException();
            newScanner = new Scanner(filePath);
            if (fileNamesList.isEmpty()) {
                this.statusScript = true;
            }
            fileNamesList.add(fileName);
            if (!newScanner.hasNext()) throw new EmptyFileException();
            while (checkCountFileinFilesList(fileNamesList,fileName)&&newScanner.hasNext()){
                System.out.printf(String.format("%s-> ~ ", fileName));
                String gettingString = newScanner.nextLine();
                String[] scriptCommand = (gettingString.trim()).split(" ", 2);
                Console.println(gettingString);
                if (scriptCommand[0].equals("execute_script".toLowerCase())) {
                    deepRecursion += 1;
                    if (deepRecursion == 2) {
                        Console.printError("Обнаружена рекурсия!");
                        throw new ScriptRecursionException();
                    }
                }
                Command command = commands.get(scriptCommand[0].toLowerCase());
                if (scriptCommand[0].equalsIgnoreCase("exit") && scriptCommand.length == 1) {
                    statusScript = false;
                    command.execute(scriptCommand);
                    break;
                }
                if (command != null) {
                    commandManager.addToCommandHistory(command);
                    command.execute(scriptCommand);
                }
            }
            fileNamesList.remove(fileName);
            Console.println("Весь файл " + fileName + " прочитан!");
        } catch (ScriptRecursionException | EmptyFileException | NotEnoughRightException exception) {
            Console.printError(exception.getMessage());
        } catch (FileNotFoundException exception) {
            Console.printError("Файл " + fileName + " со скриптом не найден!");
        } catch (
                IllegalStateException exception) {
            Console.println("");
            Console.printError("Непредвиденная ошибка.");
        } finally {
            if (fileNamesList.isEmpty()) statusScript = false;
        }
    }

    /**
     * Меняет глубину рекурсии
     * @param deep глубина рекурсии
     */
    public void setDeepRecursion(int deep) {
        this.deepRecursion = deep;
    }

    /**
     * Проверяет насколько много раз один и тот же файл находится в стеке
     * @param list стек вызовов
     * @param name название файла
     * @return true - если меньше 3 раз, false - если больше 2 раз
     */
    public boolean checkCountFileinFilesList(List<String> list, String name) {
        int count = 0;
        for (String element : list) {
            if (name.equals(element)) {
                count++;
                if (count > 2){
                    return false;
                }
            }
        }
        return true;
    }
}
