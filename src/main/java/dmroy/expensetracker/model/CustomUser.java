package dmroy.expensetracker.model;

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
@Table(name = "user", schema = "public")
public class CustomUser implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Integer userId;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "encrypted_password")
    private String encryptedPassword;
    @Column(name = "registration_dttm")
    private Date registrationDttm;


    public String getUserIdString() {
        String result = userId.toString();
        return result.replaceAll(" ","");
    }

    public String getFI(){
        return ((secondName != null ? secondName.trim() + " " : "") + (firstName != null ? firstName.trim() : "")).trim();
    }

    public String getRegistrationDttmStringYYYYMMDDHHMMSS() {
        return registrationDttm == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(registrationDttm);
    }

    public String getRegistrationDttmStringYYYYMMDD() {
        return registrationDttm == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(registrationDttm);
    }


        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUser customUser = (CustomUser) o;
        return Objects.equals(username, customUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}