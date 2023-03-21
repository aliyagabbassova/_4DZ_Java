package org.example;

import java.util.*;

public class Main {

    public static ArrayList<String[]> dataBase;
    public static Scanner myScanner = new Scanner(System.in);

    public static LinkedList<Integer> idList;

    public static void main(String[] args) { 
        getData();
        setIdList();

        System.out.println("\nВведенные записи:");
        printInIdListOrder();

        System.out.println("\nВведите \"1\" чтобы вывести записи, отсортированные по полу и возрасту:\n" +
                "введите любую другую строку для завершения работы:");
        String command = myScanner.nextLine();
        while (command.equals("1")) {
            System.out.println("Сортировка по полу и возрасту:");
            printSortedByGenderAndAge();
            System.out.println("\nВведите \"1\" чтобы вывести записи, отсортированные по полу и возрасту:\n" +
                    "введите любую другую строку для завершения работы:");
            command = myScanner.nextLine();
        }
        System.out.println("Завершение работы...");
        myScanner.close();
    }

    public static void getData() {
        dataBase = new ArrayList<>();
        System.out.println("Введите данные в формате \"Фамилия Имя Отчество Возраст Пол\".\n" +
                "Для завершения ввода, введите \"stop\".");
        String inputString = myScanner.nextLine();
        while (!inputString.equals("stop")) {
            dataBase.add(inputString.split(" "));
            inputString = myScanner.nextLine();
        }
    }

    public static void setIdList() {
        idList = new LinkedList<>();
        for (int i = 0; i < dataBase.size(); i++) {
            idList.add(i, i);
        }
    }

    public static void printInIdListOrder() {
        for (Integer id :
                idList) {
            String[] record = dataBase.get(id);
            System.out.println(record[0] + " " + record[1].toUpperCase().charAt(0) + "."
                    + record[2].toUpperCase().charAt(0) + ". " + record[3] + record[4]);
        }
    }

    public static void printSortedByGenderAndAge() {
        idList.sort(new Comparator<Integer>() { //  Сортировка по возрасту встроенными средствами
            @Override
            public int compare(Integer i1, Integer i2) {
                return Integer.parseInt(dataBase.get(i1)[3]) - Integer.parseInt(dataBase.get(i2)[3]);
            }
        });
        LinkedList<Integer> newIdList = new LinkedList<>(idList);
        Iterator<Integer> idItr = idList.iterator();
        Integer currentId;
        for (int i = 0; i < idList.size(); i++) {
            currentId = idItr.next();
            if (dataBase.get(currentId)[4].equals("М")) {
                newIdList.remove(currentId);
                newIdList.add(currentId);
            }
        }
        idList = newIdList;
        printInIdListOrder();
    }
}

