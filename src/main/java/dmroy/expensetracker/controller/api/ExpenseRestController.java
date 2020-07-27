package dmroy.expensetracker.controller.api;

import dmroy.expensetracker.model.Expense;
import dmroy.expensetracker.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class ExpenseRestController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping(value = {"/expenses"})
    public ResponseEntity expenses(@RequestParam(value="cuid") Integer customUserId) {
        List<Expense> expenses = expenseService.findAllByUserId(customUserId);
        return ResponseEntity.status(HttpStatus.OK).body(expenses == null ? new ArrayList<>() : expenses);
    }

    @GetMapping(value = {"/expenses-current-week"})
    public ResponseEntity expensesCurrentWeek(@RequestParam(value="cuid") Integer customUserId){
        Double[] expenses = expenseService.currentWeekExpenses(customUserId);
        return ResponseEntity.status(HttpStatus.OK).body(expenses == null ? new ArrayList<>() : expenses);
    }

    @GetMapping(value = {"/expenses-previous-week"})
    public ResponseEntity expensesPreviousWeek(@RequestParam(value="cuid") Integer customUserId){
        Double[] expenses = expenseService.previousWeekExpenses(customUserId);
        return ResponseEntity.status(HttpStatus.OK).body(expenses == null ? new ArrayList<>() : expenses);
    }

    @GetMapping(value = {"/expenses-period"})
    public ResponseEntity expensesByPeriod(@RequestParam(value="cuid") Integer customUserId,
                                           @RequestParam(value="dateFrom") String dateFrom,
                                          @RequestParam(value="dateTo") String dateTo) throws ParseException {
        List<Expense> expenses = expenseService.findAllByUserIdAndPeriod(customUserId,
                new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom),
                new SimpleDateFormat("yyyy-MM-dd").parse(dateTo));
        return ResponseEntity.status(HttpStatus.OK).body(expenses == null ? new ArrayList<>() : expenses);
    }

    @GetMapping(value = {"/expense"})
    public ResponseEntity customUserView(@RequestParam(value="expid") Integer expenseId) {
        Expense expense = expenseService.findById(expenseId);
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }

}