import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> menu = List.of("1 - Добавить игрушку",
                "2 - Наименование товара",
                "3 - Изменение шанса выпадения",
                "4 - Розыгрыш",
                "5 - Получить приз",
                "6 - Выход");

        boolean start = true;
        System.out.println("Программа \"Магазин игрушек\"");
        Shop<Toy> shop = new Shop<>("Магазин игрушек");
        Map<Integer, String> wh = shop.createWarehouse();
        Map<Toy, Integer> wh1 = shop.getToyCount();
        List<Toy> toys = new ArrayList<>();
        List<Map.Entry<Toy, Integer>> prizes = new ArrayList<>();
        PrizeData pd = new PrizeData("data");
        List<String> listPrize = new ArrayList<>();

        while (start) {
            menu.forEach(System.out::println);
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите номер: ");
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
                        shop.setToyCount(wh1, toy, count);
                        toys.add(toy);
                        System.out.println("Игрушка добавлена!");
                    }
                }
                case "2" -> {
                    if (!wh1.isEmpty()) {
                        System.out.println("Наименование товара: ");
                        for (Toy item : toys) {
                            System.out.println("Игрушка: " + item.getName() + "\n"
                                    + "Шанс выпадения: " + item.getDropRate() + "\n"
                                    + "Количество: " + wh1.get(item) + "\n"
                                    + "----------------");
                        }
                    } else {
                        System.out.println("Нет игрушек на складе!");
                    }
                }
                case "3" -> {
                    System.out.println("Изменение шанса выпадения: ");
                    if (!wh1.isEmpty()) {
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
                    } else {
                        System.out.println("Нет игрушек на складе!");
                    }
                }
                case "4" -> {
                    if (!wh1.isEmpty()) {
                        System.out.println("4 - Розыгрыш");
                        System.out.println("Выбрать приз: ");
                        wh.forEach((key, value) -> System.out.println(
                                "Игрушка: " + value + " номер: " + key));
                        int prize = sc.nextInt();
                        if (wh.containsKey(prize)) {
                            for (Map.Entry<Toy, Integer> entry : wh1.entrySet()) {
                                if (Objects.equals(entry.getKey().getName(), wh.get(prize)) && entry.getValue() == 0) {
                                    System.out.println("Все игрушки разыграны!");
                                } else {
                                    Random random = new Random();
                                    int rand = random.nextInt(0, 1);
                                    System.out.println("Выпало случайное число: " + rand);
                                    List<Map.Entry<Toy, Integer>> temp = wh1.entrySet()
                                            .stream()
                                            .filter(x -> x.getKey().getDropRate() == rand)
                                            .filter(x -> Objects.equals(x.getKey().getName(), wh.get(prize)))
                                            .toList();
                                    if (temp.isEmpty()) {
                                        System.out.println("В следующий раз повезёт :)");
                                    } else {
                                        System.out.println("Поздравляем! Вы выиграли!!!");
                                        if (!prizes.contains(temp.get(0))) {
                                            prizes.addAll(temp);
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("Такого номера нет в розыгрыше!");
                        }
                    } else {
                        System.out.println("Нет игрушек на складе!");
                    }
                }
                case "5" -> {
                    System.out.println("5 - Получение приза");
                    if (prizes.isEmpty()) {
                        System.out.println("Вы ничего еше не выиграли!");
                    } else {
                        String toyPrize = prizes.get(0).getKey().getName();
                        for (Map.Entry<Toy, Integer> entry : wh1.entrySet()) {
                            if (entry.getKey().getName().equals(toyPrize)) {
                                if (entry.getValue() == 0) {
                                    prizes.remove(0);
                                    System.out.println("Игрушки на сладе закончились");
                                } else {
                                    entry.setValue(entry.getValue() - 1);
                                    List<String> read = pd.readAllLines();
                                    pd.saveAllLines(read);
                                    listPrize.add(entry.getKey().getName());
                                    pd.saveAllLines(listPrize);
                                }
                            }
                        }
                    }
                }
                case "6" -> {
                    System.out.println("Завершение работы.");
                    start = false;
                }
                default -> System.out.println("Введите от 1 до 6");
            }
        }
    }
}