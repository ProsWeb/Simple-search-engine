package search;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Finder {

    private SearchingMethod method;

    public void setMethod(final SearchingMethod m) {
        this.method = m;
    }

    public void find(final Scanner scanner, final Map<Integer, String> data,
                     final Map<String, List<Integer>> index) {

        System.out.println("\nEnter a name or email "
                + "to search all suitable people.");
        String[] query = scanner.nextLine().toLowerCase().trim().split("\\s+");

        this.method.search(query, data, index);
    }
}
