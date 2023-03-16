import java.util.HashMap;
import java.util.Map;

public class Shop<T> {
    private final String shopName;
    private Map<Integer, String> toyWarehouse;

    private Map<T, Integer> toyCount;

    /**
     * Конструктор создания магазина
     *
     * @param name имя магазина
     */
    public Shop(String name) {
        this.shopName = name;
    }

    public Map<T, Integer> getToyCount() {
        return new HashMap<>();
    }

    public Map<Integer, String> createWarehouse() {
        return toyWarehouse = new HashMap<>();
    }

    public void setToyWarehouse(int id, String toyName) {
        toyWarehouse.put(id, toyName);
    }

    public void setToyCount(Map<T, Integer> map, T obj, Integer toyCount) {
        this.toyCount = map;
        map.putIfAbsent(obj, toyCount);
    }
}
