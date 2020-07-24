package dmroy.expensetracker.service;

import dmroy.expensetracker.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import dmroy.expensetracker.model.CustomUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomUserService customUserService;
    @Autowired
    UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = this.customUserService.findByUsername(username.trim());
        if (customUser == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        // [ROLE_USER, ROLE_ADMIN,..]
        UserRole userRole = this.userRoleService.findById(customUser.getRoleId());
        String roleName = userRole.getRoleName();
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
