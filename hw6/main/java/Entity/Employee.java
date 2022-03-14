package Entity;

import lombok.*;

import javax.persistence.Entity;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(callSuper = true)
@Entity
public class Employee extends Person{

    private String username;
    private String password;

    public Employee(Integer id, String nationalCode, String fullName, String address,BankBranch branch, String username, String password) {
        super(id, nationalCode, fullName, address,branch);
        this.username = username;
        this.password = password;
    }
}
