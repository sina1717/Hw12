package Entity;

import lombok.*;

import javax.persistence.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long amount;
    @ManyToOne
    private Customer customer;
}
