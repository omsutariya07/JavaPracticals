
abstract class Employee {

    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double monthlySalary();
}

class FullTime extends Employee {

    private double salary;

    public FullTime(String name, int id, double salary) {
        super(name, id);
        this.salary = salary;
    }

    public double monthlySalary() {
        return salary;
    }
}

class PartTime extends Employee {

    private int hours;
    private double rate;

    public PartTime(String name, int id, int hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    public double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {

    private double stipend;

    public Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    public double monthlySalary() {
        return stipend;
    }
}

public class PayrollTest {

    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Om", 72, 500000),
            new PartTime("Jugal", 65, 80, 200),
            new Intern("Yug R", 62, 5000),
            new FullTime("Yug T", 75, 60000),};

        double total = 0;

        for (Employee e : employees) {
            double salary = e.monthlySalary();
            System.out.print(e.getName() + " (ID " + e.getId() + ") salary is " + salary);
            if (e instanceof Intern) {
                System.out.print(" [Internship]");
            }
            System.out.println();
            total += salary;
        }

        System.out.println("Total payroll = " + total);
    }
}
