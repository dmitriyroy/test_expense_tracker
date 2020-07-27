package dmroy.expensetracker.controller;

import dmroy.expensetracker.model.CustomUser;
import dmroy.expensetracker.model.Expense;
import dmroy.expensetracker.service.CustomUserService;
import dmroy.expensetracker.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;
    @Autowired
    CustomUserService customUserService;

    @GetMapping(value = {"/expenses"})
    public String expenses(Model model, Principal principal, HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /expenses form... - user : " + user.getUsername());
        CustomUser currentUser = customUserService.findByUsername(user.getUsername());

        List<Expense> expenses = expenseService.findAllByUserId(currentUser.getUserId());
        expenses = expenses == null ? new ArrayList<>() : expenses;
        model.addAttribute("header", "My Expenses: " + (expenses == null ? 0 : expenses.size()) + " pcs.");
        model.addAttribute("expenses", expenses);

        Double[] currentWeek = expenseService.currentWeekExpenses(currentUser.getUserId());
        model.addAttribute("currentWeek", currentWeek);
        Double[] previousWeek = expenseService.previousWeekExpenses(currentUser.getUserId());
        model.addAttribute("previousWeek", previousWeek);

        model.addAttribute("activeMenuLevel_1", "collapseExpense");
        model.addAttribute("activeMenuLevel_2", "expense");

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("request", request);
        return "expenses";
    }

    @GetMapping(value = {"/expense"})
    public String expenseView(@RequestParam(value="expid") Integer expenseId,
                              Model model,
                              Principal principal,
                              HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /expense?expid=expenseId form... - user : " + user.getUsername() + "; expid=" + expenseId);
        CustomUser customUser = customUserService.findByUsername(user.getUsername());
        Expense expense = expenseService.findById(expenseId);
        model.addAttribute("expense",expense);

        model.addAttribute("header", "Expense: #ID=" + expense.getExpenseDescription());
        model.addAttribute("activeMenuLevel_1", "collapseExpense");
        model.addAttribute("currentUser", customUser);
        model.addAttribute("request", request);
        return "expense_view";
    }

    @GetMapping(value = {"/expense-edit"})
    public String expenseEdit(@RequestParam(value="expid") Integer expenseId,
                             Model model,
                             Principal principal,
                             HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /expense-edit?expid=expenseId form... - user : " + user.getUsername() + "; expid=" + expenseId);
        CustomUser customUser = customUserService.findByUsername(user.getUsername());
        Expense expense = expenseService.findById(expenseId);
        model.addAttribute("expense",expense);

        model.addAttribute("header", "Expense: #ID=" + expense.getExpenseDescription());
        model.addAttribute("activeMenuLevel_1", "collapseExpense");
        model.addAttribute("currentUser", customUser);
        model.addAttribute("request", request);
        return "expense_edit";
    }

    @GetMapping(value = {"/expense-new"})
    public String expenseNew(Model model,Principal principal,HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /expense-new form... - user : " + user.getUsername());
        CustomUser customUser = customUserService.findByUsername(user.getUsername());

        model.addAttribute("header", "Add new Expense");
        model.addAttribute("activeMenuLevel_1", "collapseExpense");
        model.addAttribute("activeMenuLevel_2", "expense-new");
        model.addAttribute("currentUser", customUser);
        model.addAttribute("request", request);
        return "expense_add_new";
    }

    @PostMapping(path = "/expense-add")
    public String expenseAddPost(@RequestParam Integer userId,
                                 @RequestParam String description,
                                 @RequestParam String comment,
                                 @RequestParam Double amount,
                                 @RequestParam (value = "datePay", required = false) String datePay,
                                 @RequestParam (value = "hours", required = false) Integer hours,
                                 @RequestParam (value = "minutes", required = false) Integer minutes,
                                 @RequestParam (value = "seconds", required = false) Integer seconds
    ) throws ParseException {
        log.debug("post form /expense-add form...");

        String hoursStr = hours.intValue() < 10 ? " 0" + hours.intValue() :  " " + hours.toString();
        String minutesStr = minutes.intValue() < 10 ? ":0" + minutes.intValue() :  ":" + minutes.toString();
        String secondsStr = seconds.intValue() < 10 ? ":0" + seconds.intValue() :  ":" + seconds.toString();

        Date dateCreateDate = datePay.trim().equals("") ? null :
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(datePay + hoursStr + minutesStr + secondsStr);
        Expense expense = this.expenseService.addExpense(userId,description,comment,amount,dateCreateDate);
        StringBuilder url = new StringBuilder();
        url.append("redirect:/expense-add?expid=");
        url.append(expense.getExpenseIdString());
        return  url.toString();
    }

    @PostMapping(path = "/expense-update")
    public String expenseUpdatePost(@RequestParam Integer expenseId,
                                     @RequestParam String description,
                                     @RequestParam String comment,
                                     @RequestParam Double amount,
                                     @RequestParam (value = "datePay", required = false) String datePay,
                                     @RequestParam (value = "hours", required = false) Integer hours,
                                     @RequestParam (value = "minutes", required = false) Integer minutes,
                                     @RequestParam (value = "seconds", required = false) Integer seconds
    ) throws ParseException {
        log.debug("post form /expense-update form...");

        String hoursStr = hours.intValue() < 10 ? " 0" + hours.intValue() :  " " + hours.toString();
        String minutesStr = minutes.intValue() < 10 ? ":0" + minutes.intValue() :  ":" + minutes.toString();
        String secondsStr = seconds.intValue() < 10 ? ":0" + seconds.intValue() :  ":" + seconds.toString();

        Date dateCreateDate = datePay.trim().equals("") ? null :
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(datePay + hoursStr + minutesStr + secondsStr);
        Expense expense = this.expenseService.updateExpense(expenseId,description,comment,amount,dateCreateDate);
        StringBuilder url = new StringBuilder();
        url.append("redirect:/expense-update?expid=");
        url.append(expense.getExpenseIdString());
        return  url.toString();
    }

    @GetMapping(value = {"/expense-add", "/expense-update"})
    public String expenseAddUpdateGet(@RequestParam(value="expid") Integer expenseId) {
        log.debug("redirect POST -> GET (from /expense-add|update POST to /expense-add|update GET) form...");
        StringBuilder url = new StringBuilder();
        url.append("redirect:/expense?expid=");
        url.append(expenseId);
        return  url.toString();
    }

}