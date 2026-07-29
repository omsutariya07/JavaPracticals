public class MiniBank {

    public static void main(String[] args) throws CloneNotSupportedException {

        Customer.Address addr = new Customer.Address(
                "Shreedeep Residency",
                "Changa",
                "388421");

        Customer c = new Customer(
                "Om Sutariya",
                "om@gmail.com",
                "9930030060",
                addr);

        Customer c2 = c.clone();

        Account[] acc = new Account[3];

        acc[0] = new Account(c.getName(), 5000);
        acc[1] = new Account("Bhavy", 3000);
        acc[2] = new Account("Manthan");

        acc[0].deposit(1000);
        acc[0].withdraw(2000);

        acc[1].deposit(500);
        acc[1].withdraw(1000);

        acc[2].deposit(2000);
        acc[2].withdraw(500);

        System.out.println("Accounts:");

        for (Account a : acc) {
            System.out.println(a.toString());
        }

        System.out.println();

        System.out.println("Comparing Accounts:");
        System.out.println(acc[0].equals(acc[1]));
        System.out.println(acc[0].equals(acc[0]));

        Object obj = acc[0];

        if (obj instanceof Account) {
            System.out.println("obj is an Account object.");
        }

        if (c2 instanceof Customer) {
            System.out.println("c2 is a Customer object.");
        }
    }
}

class Customer implements Cloneable {

    private String name;
    private String email;
    private String phone;
    private final String id;
    private Address addr;

    private static long count = 101;

    private static String createId() {
        return "CUST" + count++;
    }

    public Customer(String name, String email, String phone, Address addr) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.addr = addr;
        this.id = createId();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public Address address() {
        return addr;
    }

    public Customer clone() throws CloneNotSupportedException {
        return (Customer) super.clone();
    }

    public static class Address {

        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() {
            return line;
        }

        public String getCity() {
            return city;
        }

        public String getPincode() {
            return pincode;
        }
    }
}

class Account {

    private final String accNo;
    private String owner;
    private long bal;
    private boolean active;

    private static int count = 1;

    private static String createAccNo() {
        return String.format("AC%04d", count++);
    }

    public Account(String owner, long bal) {
        this.accNo = createAccNo();
        this.owner = owner;
        this.bal = bal;
        this.active = true;
    }

    public Account(String owner) {
        this(owner, 0);
    }

    public void deposit(long amount) {
        if (amount < 0)
            return;

        bal += amount;
    }

    public boolean withdraw(long amount) {

        if (amount < 0)
            return false;

        if (bal >= amount) {
            bal -= amount;
            return true;
        }

        return false;
    }

    public String getAccNo() {
        return accNo;
    }

    public String getOwner() {
        return owner;
    }

    public long getBal() {
        return bal;
    }

    public boolean isActive() {
        return active;
    }

    public String toString() {
        return accNo + " " + owner + " Balance: " + bal;
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Account))
            return false;

        Account a = (Account) o;

        return accNo.equals(a.accNo);
    }

    public int hashCode() {
        return accNo.hashCode();
    }
}