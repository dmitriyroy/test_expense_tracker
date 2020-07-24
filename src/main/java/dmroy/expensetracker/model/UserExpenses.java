package dmroy.expensetracker.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_expenses", schema = "public")
public class UserExpenses implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_expense_id", updatable = false, nullable = false)
    private Integer userExpenseId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "expense_id")
    private Integer expenseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExpenses userExpenses = (UserExpenses) o;
        return Objects.equals(userExpenseId, userExpenses.userExpenseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userExpenseId);
    }

}