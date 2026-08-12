abstract class Media {
    private String title;
    private int id;

    public Media(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() { return title; }
    public int getId() { return id; }

    public abstract double lateFee(int daysLate);
}

class Book extends Media {
    public Book(String title, int id) {
        super(title, id);
    }

    @Override
    public double lateFee(int daysLate) {
        return daysLate * 2.0;
    }
}

class DVD extends Media {
    public DVD(String title, int id) {
        super(title, id);
    }


    public double lateFee(int daysLate) {
        return daysLate * 5.0; 
    }
}

class Magazine extends Media {
    public Magazine(String title, int id) {
        super(title, id);
    }

  
    public double lateFee(int daysLate) {
        
        return daysLate > 0 ? 10.0 : 0.0;
    }
}

public class MediaTest {
    public static void main(String[] args) {
        Media[] items = {
            new Book("Java", 1),
            new DVD("Inception", 2),
            new Magazine("Weekly Magazine", 3),
            new Book("DSA in C++", 4),
            new DVD("Interstellar", 5)
        };

        int[] daysLate = {3, 1, 0, 5, 2}; 
        double totalFee = 0;

        for (int i = 0; i < items.length; i++) {
            double fee = items[i].lateFee(daysLate[i]);
            System.out.println(items[i].getTitle() + " (ID " + items[i].getId() +
                               ") late fee = " + fee);
            totalFee += fee;
        }

        System.out.println("Total late fees = " + totalFee);
    }
}
