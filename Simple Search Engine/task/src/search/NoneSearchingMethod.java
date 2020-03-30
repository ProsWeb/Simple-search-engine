package search;

import java.util.*;

class NoneSearchingMethod implements SearchingMethod {

    @Override
    public void search(final String[] query, final Map<Integer, String> data,
                       final Map<String, List<Integer>> index) {

        Set<String> find = new LinkedHashSet<>(data.values());

        for (String queryPart : query) {
            if (index.containsKey(queryPart)) {
                List<Integer> indexes = index.get(queryPart);

                for (Integer i : indexes) {
                    find.remove(data.get(i));
                }
            }
        }

        System.out.println();
        find.forEach(System.out::println);
    }
}
