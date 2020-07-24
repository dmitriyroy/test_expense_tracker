package dmroy.expensetracker.service;

import dmroy.expensetracker.model.UserExpenses;
import dmroy.expensetracker.repository.UserExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExpensesService {

    @Autowired
    UserExpensesRepository userExpensesRepository;

    public UserExpenses save(UserExpenses userExpenses) {
        return userExpensesRepository.save(userExpenses);
    }
    public UserExpenses findById(Integer userExpensesId) {
        return userExpensesRepository.findById(userExpensesId).get();
    }
    public boolean existsById(Integer userExpensesId) {
        return userExpensesRepository.existsById(userExpensesId);
    }
    public Iterable<UserExpenses> findAll() {
        return userExpensesRepository.findAll();
    }
    public Iterable findAllById(Iterable<Integer> iterable) {
        return userExpensesRepository.findAllById(iterable);
    }
    public long count() {
        return userExpensesRepository.count();
    }
    public void deleteById(Integer userExpensesId) { userExpensesRepository.deleteById(userExpensesId); }
    public void delete(UserExpenses userExpenses) {
        userExpensesRepository.delete(userExpenses);
    }
    public void deleteAll() { userExpensesRepository.deleteAll(); }

    public List<UserExpenses> findAllByUserId(Integer userId){
        return userExpensesRepository.findAllByUserId(userId);
    }

}
