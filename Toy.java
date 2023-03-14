public class Toy {
    private String name;

    public int getDropRate() {
        return dropRate;
    }

    private int dropRate;
    public String getName() {
        return name;
    }

    public Toy(String name) {
        this.name = name;
        this.dropRate = 0;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void setDropRate(int dropRate) {
        this.dropRate = dropRate;
        System.out.println("Шанс выпадения изменен.");
    }



}
