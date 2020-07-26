package dmroy.expensetracker.service;

import dmroy.expensetracker.repository.CustomUserRepository;
import dmroy.expensetracker.utils.StringUtils;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dmroy.expensetracker.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CustomUserService {

    @Autowired
    CustomUserRepository customUserRepository;
    @Autowired
    StringUtils stringUtils;
    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public CustomUser save(CustomUser customUser) {
        return customUserRepository.save(customUser);
    }
    public CustomUser findById(Integer customUserId) {
        return customUserRepository.findById(customUserId).get();
    }
    public boolean existsById(Integer customUserId) {
        return customUserRepository.existsById(customUserId);
    }
    public Iterable<CustomUser> findAll() {
        return customUserRepository.findAll();
    }
    public Iterable findAllById(Iterable<Integer> iterable) {
        return customUserRepository.findAllById(iterable);
    }
    public long count() {
        return customUserRepository.count();
    }
    public void deleteById(Integer customUserId) { customUserRepository.deleteById(customUserId); }
    public void delete(CustomUser customUser) {
        customUserRepository.delete(customUser);
    }
    public void deleteAll() { customUserRepository.deleteAll(); }

    public List<CustomUser> getAll(){
        List<CustomUser> result = new ArrayList<>();
        Iterator<CustomUser> customUserIterator = this.findAll().iterator();
        while(customUserIterator.hasNext()){
            result.add(customUserIterator.next());
        }
        return result;
    }

    public CustomUser findByUsername(String username){ return customUserRepository.findByUsername(username.toLowerCase()); }

    public CustomUser addCustomUser(String fName, String sName, String username, String password, Integer roleId){
        CustomUser customUser = new CustomUser();
        customUser.setUsername(stringUtils.substrVarchar(username,20).toLowerCase());
        customUser.setFirstName(stringUtils.substrVarchar(fName,150));
        customUser.setSecondName(stringUtils.substrVarchar(sName,150));
        customUser.setEncryptedPassword(passwordEncoder().encode(password.trim()));
        customUser.setRoleId(roleId);
        customUser.setRegistrationDttm(new Date());
        customUser = this.save(customUser);
        return customUser;
    }

    public CustomUser updateCustomUser(Integer customUserId, String fName, String sName, String username, String password, Integer roleId){
        CustomUser customUser = this.findById(customUserId);
        // can`t change username
//        customUser.setUsername(stringUtils.substrVarchar(username,20).toLowerCase());
        customUser.setFirstName(stringUtils.substrVarchar(fName,150));
        customUser.setSecondName(stringUtils.substrVarchar(sName,150));
        if(password != null) {
            customUser.setEncryptedPassword(passwordEncoder().encode(password.trim()));
        }
        customUser.setRoleId(roleId);
        customUser = this.save(customUser);
        return customUser;
    }
}
