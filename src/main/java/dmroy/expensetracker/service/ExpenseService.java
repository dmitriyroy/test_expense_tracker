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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public Double[] currentWeekExpenses(Integer userId){
        return getWeekData(userId, new Date());
    }
    public Double[] previousWeekExpenses(Integer userId){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int todayDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, -todayDayOfWeek);

        return getWeekData(userId, gc.getTime());
    }
    private Double[] getWeekData(Integer userId, Date lastWeekDay) {
        Double[] result = new Double[8];
        GregorianCalendar gc = new GregorianCalendar();
        Calendar cal = Calendar.getInstance();
        cal.setTime(lastWeekDay);
        int todayDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        gc.add(Calendar.DATE, -todayDayOfWeek + 1);
        Date sunday = null;
        Date monday = null;
        Date tuesday = null;
        Date wednesday = null;
        Date thursday = null;
        Date friday = null;
//        Date saturday = null;
        try {
            sunday = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime()));
            gc.add(Calendar.DATE, 1);
            monday = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime()));
            gc.add(Calendar.DATE, 1);
            tuesday = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime()));
            gc.add(Calendar.DATE, 1);
            wednesday = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime()));
            gc.add(Calendar.DATE, 1);
            thursday = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime()));
            gc.add(Calendar.DATE, 1);
            friday = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime()));
//            gc.add(Calendar.DATE, 1);
//            saturday = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Expense> expenses = expenseRepository.findAllByUserIdAndPeriod(userId,sunday,lastWeekDay);
        double sumTotal = 0.0;
        double sumSun = 0.0;
        double sumMon = 0.0;
        double sumTue = 0.0;
        double sumWed = 0.0;
        double sumThu = 0.0;
        double sumFri = 0.0;
        double sumSat = 0.0;
        int cntSun = 0;
        int cntMon = 0;
        int cntTue = 0;
        int cntWed = 0;
        int cntThu = 0;
        int cntFri = 0;
        int cntSat = 0;
        for(Expense expense:expenses){
            sumTotal += expense.getExpenseAmount() == null ? 0.0 : expense.getExpenseAmount();
            Date expDate = null;
            try {
                expDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(expense.getExpenseDttm()));
                if (sunday.equals(expDate)) {
                    sumSun += expense.getExpenseAmount() == null ? 0.0 : expense.getExpenseAmount();
                    cntSun++;
                }else if(monday.equals(expDate)){
                    sumMon += expense.getExpenseAmount() == null ? 0.0 : expense.getExpenseAmount();
                    cntMon++;
                }else if(thursday.equals(expDate)){
                    sumThu += expense.getExpenseAmount() == null ? 0.0 : expense.getExpenseAmount();
                    cntThu++;
                }else if(wednesday.equals(expDate)){
                    sumWed += expense.getExpenseAmount() == null ? 0.0 : expense.getExpenseAmount();
                    cntWed++;
                }else if(tuesday.equals(expDate)){
                    sumTue += expense.getExpenseAmount() == null ? 0.0 : expense.getExpenseAmount();
                    cntTue++;
                }else if(friday.equals(expDate)){
                    sumFri += expense.getExpenseAmount() == null ? 0.0 : expense.getExpenseAmount();
                    cntFri++;
                }else{
                    sumSat += expense.getExpenseAmount() == null ? 0.0 : expense.getExpenseAmount();
                    cntSat++;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        result[0] = sumTotal;
        result[1] = sumSun / (cntSun == 0 ? 1 : cntSun);
        result[2] = sumMon / (cntMon == 0 ? 1 : cntMon);
        result[3] = sumTue / (cntTue == 0 ? 1 : cntTue);
        result[4] = sumWed / (cntWed == 0 ? 1 : cntWed);
        result[5] = sumThu / (cntThu == 0 ? 1 : cntThu);
        result[6] = sumFri / (cntFri == 0 ? 1 : cntFri);
        result[7] = sumSat / (cntSat == 0 ? 1 : cntSat);

        return result;
    }

}
