package model;

public class Customer extends Person{
    private int id;

    public Customer(){

    }
    public Customer(int id, String name, String phoneNumber) {
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
