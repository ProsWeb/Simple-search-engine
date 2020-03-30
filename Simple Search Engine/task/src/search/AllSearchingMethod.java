package search;

import java.util.*;

class AllSearchingMethod implements SearchingMethod {

    @Override
    public void search(final String[] query, final Map<Integer, String> data,
                       final Map<String, List<Integer>> index) {

        Set<String> find = new LinkedHashSet<>();
        List<Integer> tempIndex = new ArrayList<>();

        for (String queryPart : query) {
            if (index.containsKey(queryPart)) {
                if (tempIndex.isEmpty()) {
                    tempIndex = index.get(queryPart);
                }
                List<Integer> temp = index.get(queryPart);

                tempIndex.retainAll(temp);

                for (Integer t : tempIndex) {
                    find.add(data.get(t));
                }
            } else {
                System.out.println("\nNo such person.");
                return;
            }
        }

        System.out.println();
        find.forEach(System.out::println);
    }
}
