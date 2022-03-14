package Entity;

import lombok.*;

import javax.persistence.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Inheritance
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String nationalCode;
    private String fullName;
    private String address;
    @ManyToOne
    private BankBranch branch;


}
