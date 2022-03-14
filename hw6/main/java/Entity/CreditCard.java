package Entity;

import lombok.*;

import javax.persistence.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Account account;
    private Long cardNumber;
    private String password;

}
