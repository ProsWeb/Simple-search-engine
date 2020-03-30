package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File(args[1]);

        Map<Integer, String> dataSet = fillDataSet(file);
        Map<String, List<Integer>> indexOfPersons = fillIndex(dataSet);

        String menu = "\n=== Menu ===\n"
                        + "1. Find a person\n"
                        + "2. Print all people\n"
                        + "0. Exit";

        boolean search = true;
        while (search) {
            System.out.println(menu);
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    strategySet(scanner, dataSet, indexOfPersons);
                    break;
                case 2:
                    printData(dataSet);
                    break;
                case 0:
                    search = false;
                    System.out.println("\nBye!");
                    break;
                default:
                    System.out.println("Incorrect option! Try again.\n");
            }
        }
    }

    static Map<Integer, String> fillDataSet(final File file) {
        Map<Integer, String> data = new LinkedHashMap<>();
        int i = 0;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                data.put(i, sc.nextLine().trim());
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }

        return data;
    }

    private static Map<String, List<Integer>> fillIndex(
                        final Map<Integer, String> data) {
        Map<String, List<Integer>> index = new LinkedHashMap<>();

        for (int i = 0; i < data.size(); i++) {
            String[] person = data.get(i).toLowerCase().split("\\s+");

            for (String personData : person) {
                List<Integer> personIndex;

                if (index.containsKey(personData)) {
                    personIndex = index.get(personData);
                } else {
                    personIndex = new ArrayList<>();
                }

                personIndex.add(i);
                index.put(personData, personIndex);
            }
        }

        return index;
    }

    static void strategySet(final Scanner scanner,
                            final Map<Integer, String> data,
                            final Map<String, List<Integer>> index) {

        System.out.println("Select a matching strategy: ALL, ANY, NONE");

        String strategy = scanner.nextLine();
        Finder finder = new Finder();

        switch (strategy) {
            case "ALL":
                finder.setMethod(new AllSearchingMethod());
                break;
            case "ANY":
                finder.setMethod(new AnySearchingMethod());
                break;
            case "NONE":
                finder.setMethod(new NoneSearchingMethod());
                break;
            default:
                System.out.println("Unknown strategy");
                break;
        }

        finder.find(scanner, data, index);
    }

    static void printData(final Map<Integer, String> data) {
        System.out.println("\n=== List of people ===");

        data.values().forEach(System.out::println);

    }
}
