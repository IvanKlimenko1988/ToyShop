import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game<T> {
    public String getName() {
        return name;
    }

    private String name;
    private final List<T> game;


    public Game(String name, List<T> items) {
        this.name = name;
        this.game = items;
    }

    public List<T> getGame() {
        return game;
    }


    public static void main(String[] args) {
        // Создали игрушки
//        Toy dog = new Toy("Dog");
//        Toy cat = new Toy("Cat");
//        Toy goldDog = new Toy("GoldDog");
//        Toy goldCat = new Toy("GoldCat");
//        Toy silverDog = new Toy("silverDog");
//        Toy silverCat = new Toy("silverCat");

        //Список игрушек
//        List<Toy> toys = Stream.of(cat, silverCat, silverDog, goldCat, goldDog).toList();

        //Создали магазин


        //Создали склад и игрушками(ID, Имя игрушки)
//        Map<Integer, String> wh = shop.createWarehouse();

//        shop.setToyWarehouse(1, dog.getName());
//        shop.setToyWarehouse(2, cat.getName());
//        shop.setToyWarehouse(3, silverDog.getName());
//        shop.setToyWarehouse(4, silverCat.getName());
//        shop.setToyWarehouse(5, goldDog.getName());
//        shop.setToyWarehouse(6, goldCat.getName());


//        Map<Toy, Integer> toyCount = shop.getToyCount();
//        shop.setToyCount(dog, 5)
//        shop.setToyCount(cat, 5);
//        shop.setToyCount(silverDog, 3);
//        shop.setToyCount(silverCat, 3);
//        shop.setToyCount(goldDog, 1);
//        shop.setToyCount(goldCat, 1);


//        Game<Toy> game = new Game<>("Игра", toys);

//        System.out.println(game.getGame());


        List<String> menu = List.of("1 - Добавить игрушку",
                "2 - Наименование товара",
                "3 - Изменение шанса выпадения",
                "4 - Розыгрыш",
                "5 - Выход");

        boolean start = true;
        System.out.println("Программа \"Магазин игрушек\"");
        Shop<Toy> shop = new Shop("Магазин игрушек");
        Map<Integer, String> wh = shop.createWarehouse();
        Map<Integer, Toy> wh1 = shop.getToyCount();
        List<Toy> toys = new ArrayList<>();

        while (start) {
            menu.stream().forEach(System.out::println);
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            switch (input) {
                case "1" -> {
                    System.out.println("Введите название игрушки:");
                    String name = sc.next();
                    Toy toy = new Toy(name);
                    System.out.println("Введите ID игрушки: ");
                    int inputId = sc.nextInt();
                    if (wh.containsKey(inputId)) {
                        System.out.println("Такой ID есть в базе!");
                    } else {
                        shop.setToyWarehouse(inputId, toy.getName());
                        System.out.println("Введите количество: ");
                        int count = sc.nextInt();
                        shop.setToyCount(toy, count);
                        toys.add(toy);
                        System.out.println("Игрушка добавлена!");
                    }
                }
                case "2" -> {
                    System.out.println("Наименование товара: ");
                    for (Toy item : toys) {
                        System.out.println("Игрушка: " + item.getName() + "\n"
                                + "Шанс выпадения: " + item.getDropRate() + "\n"
                                + "Количество: " + wh1.get(item) + "\n"
                                + "----------------");
                    }
                }
                case "3" -> {
                    System.out.println("Изменение шанса выпадения: ");
                    System.out.println("Выберите игрушку: ");
                    String name = sc.next();
                    for (Toy item : toys) {
                        if (item.getName().equals(name)) {
                            System.out.println("Введите новый шанс: ");
                            int dropRate = sc.nextInt();
                            item.setDropRate(dropRate);
                        } else {
                            System.out.println("Такой игрушки нет!");
                        }
                    }
                }
                case "4" -> {
                    System.out.println("4 - Розыгрыш");
                    System.out.println("Выбрать приз: ");
                    wh.forEach((key, value) -> System.out.println(
                            "Номер: " + key + " Игрушка: " + value));
                    List<Toy> prizes = new ArrayList<>();
                    String prize = sc.next();
                    prizes.add(wh1.get(prize));
                    Game<Toy> game = new Game<>("Лохотрон", prizes);
                    System.out.println("Розыгрыш " + game.getName());






                }
                case "5" -> {
                    System.out.println("5 - Выход");
                    start = false;
                }
                default -> System.out.println("Введите от 1 до 5");
            }


        }

    }
}
