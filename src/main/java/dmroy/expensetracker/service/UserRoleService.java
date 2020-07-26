package dmroy.expensetracker.service;

import dmroy.expensetracker.model.UserRole;
import dmroy.expensetracker.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
    public UserRole findById(Integer userRoleId) {
        return userRoleRepository.findById(userRoleId).get();
    }
    public boolean existsById(Integer userRoleId) {
        return userRoleRepository.existsById(userRoleId);
    }
    public Iterable<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
    public Iterable findAllById(Iterable<Integer> iterable) {
        return userRoleRepository.findAllById(iterable);
    }
    public long count() {
        return userRoleRepository.count();
    }
    public void deleteById(Integer userRoleId) { userRoleRepository.deleteById(userRoleId); }
    public void delete(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }
    public void deleteAll() { userRoleRepository.deleteAll(); }

    public List<UserRole> getAll(){
        List<UserRole> result = new ArrayList<>();
        Iterator<UserRole> userRoleIterator = this.findAll().iterator();
        while(userRoleIterator.hasNext()){
            result.add(userRoleIterator.next());
        }
        return result;
    }

}
