package entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
@NoArgsConstructor
@Entity
public class Customer extends Users {

    private String address;


    public Customer(int id, String username, String password, String address) {
        super(id, username, password);
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                '}';
    }
}
