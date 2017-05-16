package Helpers;

/**
 * Created by jacob on 16/05/2017.
 */
public class Customer {

    public int ID;
    public String name;
    public String address;
    public String passwordHash;

    public Customer(int ID, String name, String address, String passwordHash){
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.passwordHash = passwordHash;
    }

}
