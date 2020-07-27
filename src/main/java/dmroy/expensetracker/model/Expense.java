package dmroy.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "expense", schema = "public")
public class Expense implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id", updatable = false, nullable = false)
    private Integer expenseId;
    @Column(name = "expense_amount")
    private Double expenseAmount;
    @Column(name = "expense_description")
    private String expenseDescription;
    @Column(name = "expense_comment")
    private String expenseComment;
    @Column(name = "expense_dttm")
    private Date expenseDttm;

    @JsonIgnore
    public String getExpenseIdString() {
        String result = expenseId.toString();
        return result.replaceAll(" ","");
    }

    @JsonIgnore
    public String getExpenseDttmStringYYYYMMDDHHMMSS() {
        return expenseDttm == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expenseDttm);
    }

    @JsonIgnore
    public String getExpenseDttmStringYYYYMMDD() {
        return expenseDttm == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(expenseDttm);
    }

    @JsonIgnore
    public String getExpenseDttmStringHH() {
        return expenseDttm == null ? "" : new SimpleDateFormat("HH").format(expenseDttm);
    }

    @JsonIgnore
    public String getExpenseDttmStringMM() {
        return expenseDttm == null ? "" : new SimpleDateFormat("mm").format(expenseDttm);
    }

    @JsonIgnore
    public String getExpenseDttmStringSS() {
        return expenseDttm == null ? "" : new SimpleDateFormat("ss").format(expenseDttm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(expenseId, expense.expenseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseId);
    }

}