package dmroy.expensetracker.service;

import dmroy.expensetracker.model.Expense;
import dmroy.expensetracker.model.UserExpenses;
import dmroy.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    UserExpensesService userExpensesService;

    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }
    public Expense findById(Integer expenseId) {
        return expenseRepository.findById(expenseId).get();
    }
    public boolean existsById(Integer expenseId) {
        return expenseRepository.existsById(expenseId);
    }
    public Iterable<Expense> findAll() {
        return expenseRepository.findAll();
    }
    public Iterable findAllById(Iterable<Integer> iterable) {
        return expenseRepository.findAllById(iterable);
    }
    public long count() {
        return expenseRepository.count();
    }
    public void deleteById(Integer expenseId) { expenseRepository.deleteById(expenseId); }
    public void delete(Expense expense) {
        expenseRepository.delete(expense);
    }
    public void deleteAll() { expenseRepository.deleteAll(); }

    public Page<Expense> findAllByUserIdPageable(Integer userId, Pageable pageable){
        List<UserExpenses> userExpensesList = userExpensesService.findAllByUserId(userId);
        List<Integer> expenseIds = new ArrayList<>();
        for(UserExpenses userExpenses:userExpensesList){
            expenseIds.add(userExpenses.getExpenseId());
        }
        if(expenseIds.isEmpty()){
            return null;
        }
        return expenseRepository.findAllByExpenseIdIn(expenseIds,pageable);
    }

    public List<Expense> findAllByUserId(Integer userId){
        List<UserExpenses> userExpensesList = userExpensesService.findAllByUserId(userId);
        List<Integer> expenseIds = new ArrayList<>();
        for(UserExpenses userExpenses:userExpensesList){
            expenseIds.add(userExpenses.getExpenseId());
        }
        if(expenseIds.isEmpty()){
            return null;
        }
        return expenseRepository.findAllByExpenseIdIn(expenseIds);
    }

    public Expense addExpense(Integer userId,String description,String comment,Double amount,Date dateCreateDate){
        Expense expense = new Expense();
        expense.setExpenseDescription(description);
        expense.setExpenseComment(comment);
        expense.setExpenseAmount(amount);
        expense.setExpenseDttm(dateCreateDate);
        expense = this.save(expense);

        UserExpenses userExpenses = new UserExpenses();
        userExpenses.setExpenseId(expense.getExpenseId());
        userExpenses.setUserId(userId);
        userExpensesService.save(userExpenses);

        return expense;
    }

    public Expense updateExpense(Integer expenseId,String description,String comment,Double amount,Date dateCreateDate){
        Expense expense = this.findById(expenseId);
        expense.setExpenseDescription(description);
        expense.setExpenseComment(comment);
        expense.setExpenseAmount(amount);
        expense.setExpenseDttm(dateCreateDate);
        expense = this.save(expense);

        return expense;
    }

}
