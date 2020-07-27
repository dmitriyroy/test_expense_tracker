package dmroy.expensetracker.controller.api;

import dmroy.expensetracker.model.Expense;
import dmroy.expensetracker.model.UserRole;
import dmroy.expensetracker.service.ExpenseService;
import dmroy.expensetracker.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity customUsers(@RequestParam(value="cuid") Integer customUserId) {
        List<Expense> expenses = expenseService.findAllByUserId(customUserId);
        return ResponseEntity.status(HttpStatus.OK).body(expenses == null ? new ArrayList<>() : expenses);
    }

    @GetMapping(value = {"/expense"})
    public ResponseEntity customUserView(@RequestParam(value="expid") Integer expenseId) {
        Expense expense = expenseService.findById(expenseId);
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }

}