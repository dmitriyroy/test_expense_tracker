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
@Table(name = "user_role", schema = "public")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", updatable = false, nullable = false)
    private Integer roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_description")
    private String roleDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(userRole, userRole.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

}