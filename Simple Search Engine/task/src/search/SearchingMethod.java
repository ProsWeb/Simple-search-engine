package search;

import java.util.List;
import java.util.Map;

public interface SearchingMethod {
    void search(String[] query, Map<Integer, String> data,
                Map<String, List<Integer>> index);
}
