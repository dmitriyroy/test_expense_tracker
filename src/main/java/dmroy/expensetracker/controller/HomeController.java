package dmroy.expensetracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dmroy.expensetracker.model.CustomUser;
import dmroy.expensetracker.service.*;
import dmroy.expensetracker.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    CustomUserService customUserService;

    @RequestMapping(value = {"/" , "/index"})
    public String index(Model model, Principal principal, HttpServletRequest request) throws IOException {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /index page... - user : " + user.getUsername());
        CustomUser currentCustomUser = customUserService.findByUsername(user.getUsername());
        model.addAttribute("currentUser", currentCustomUser);
        if(request != null) {
            model.addAttribute("request", request);
        }
        if(currentCustomUser == null){
            return "registration_form";
        }
        return "index";
    }

    @GetMapping(value = "/login")
    public String showLogin(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        log.debug("show login form..." + error);

        if(error != null){
            model.addAttribute("message", "Unknown user or bad password");
        }
        return "login_form";
    }

    @GetMapping(value = {"/new-registration"})
    public String registrationNew(Model model, HttpServletRequest request) {
        log.debug("show , /new-registration form...");
        model.addAttribute("request", request);
        return "registration_form";
    }

    @PostMapping(path = "/registration")
    public String registrationPost(@RequestParam String fName,
                                   @RequestParam String sName,
                                   @RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String repeatPassword,
                                   Model model, HttpServletRequest request) {
        log.debug("post form /registration form...");

        model.addAttribute("request", request);

        if(!password.trim().equals(repeatPassword.trim())){
            model.addAttribute("fName", fName);
            model.addAttribute("sName", sName);
            model.addAttribute("username", username);
            model.addAttribute("message", "Password mismatch");
            return "registration_form";
        }

        CustomUser customUser = this.customUserService.findByUsername(username.trim().toLowerCase());
        if(customUser != null){
            model.addAttribute("message", "This user already exists. Try again.");
            return "registration_form";
        }

        CustomUser customUser1 = customUserService.addCustomUser(fName, sName, username, password, 2);
        return "redirect:/registration";
    }

    @GetMapping(value = {"/registration"})
    public String registrationGet() {
        log.debug("redirect POST -> GET (from /registration POST to /registration GET) form...");
        return "redirect:/login";
    }
}