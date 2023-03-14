import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop<T>{
    private String shopName;
    private int id;
    private Map <Integer, String> toyWarehouse;
    private Map<T, Integer> toyCount;

    /**
     * Конструктор создания магазина
     * @param name имя магазина
     */
    public Shop(String name) {
        this.shopName = name;
    }

    public Map<Integer, T> getToyCount() {
        return (Map<Integer, T>) (toyCount = new HashMap<>());
    }

    public Map<Integer, String> createWarehouse() {
        return toyWarehouse = new HashMap<>();
    }

    public void setToyWarehouse(int id, String toyName) {
        toyWarehouse.put(id, toyName);
    }

    public void setToyCount(T toy, int count) {
        toyCount.put(toy, count);
    }

    public void getToy(int id) {
        System.out.println(toyWarehouse.get(id));
    }



    /*
Возможность организовать розыгрыш игрушек.
Например, следующим образом:
С помощью метода выбора призовой игрушки – мы получаем эту
призовую игрушку и записываем в список\массив.
Это список призовых игрушек, которые ожидают выдачи.
Еще у нас должен быть метод – получения призовой игрушки.
После его вызова – мы удаляем из списка\массива первую игрушку
и сдвигаем массив. А эту игрушку записываем в текстовый файл.
Не забываем уменьшить количество игрушек

К написанию программы можно подойти более творчески и делать
так, как Вы поняли задание. Немного менять и отходить от примера выше.

     */



}
