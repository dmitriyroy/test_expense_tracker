package dmroy.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import UserDetails;
import UserDetailsService;
import UsernameNotFoundException;
import org.springframework.stereotype.Service;
import dmroy.expensetracker.model.CustomUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        GlobalUser globalUser = this.globalUserService.findByUsername(username.trim().toLowerCase());
        CustomUser customUser = this.userService.findByUsername(username.trim());

//        GlobalUser globalUser = this.globalUserService.findByUsernameAndIsValidMail(username,"Y");
//        if(globalUser == null) {
//            globalUser = this.globalUserService.findByMail(username);
//            globalUser = this.globalUserService.findByMailAndIsValidMail(username,"Y");
//        }

        if (customUser == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
//            return null;
        }

        // [ROLE_USER, ROLE_ADMIN,..]
//        EmployeeRole employeeRole = this.employeeRoleService.findById(employee.getRoleId());
////        System.out.println("===========================================> employeeRole = " + employeeRole);
//        String roleName = employeeRole.getRoleName();
////        System.out.println("===========================================> roleName = " + roleName);
//        String roleName = "ROLE_GLOBAL_USER";
        String roleName = "";
        if(customUser.getIsValidMail() != null && customUser.getIsValidMail().equals("Y")){
            roleName = "ROLE_ADMIN";
        }else{
            roleName = "ROLE_WAIT_VERIF";
        }
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleName != null) {
            // USER, ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
            grantList.add(authority);
        }
        UserDetails userDetails = new User(customUser.getUsername(), customUser.getEncryptedPassword(), grantList);
        return userDetails;
    }

}
