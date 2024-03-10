package model;

public class Customer extends Person{
    private String isDeposit;
    private int id;

    public Customer(){

    }
    public Customer(String name, String phoneNumber, int id) {
        super(name, phoneNumber);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
