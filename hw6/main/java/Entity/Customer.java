package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Customer extends Person{
    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    public Customer(Integer id, String nationalCode, String fullName, String address, BankBranch branch, Set<Account> accounts) {
        super(id, nationalCode, fullName, address, branch);
        this.accounts = accounts;
    }
}
