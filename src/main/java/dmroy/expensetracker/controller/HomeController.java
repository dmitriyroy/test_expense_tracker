package dmroy.expensetracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dmroy.expensetracker.model.FaqChapter;
import dmroy.expensetracker.model.FaqSection;
import dmroy.expensetracker.model.CustomUser;
import dmroy.expensetracker.service.*;
import dmroy.expensetracker.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    MenuService menuService;

    @RequestMapping(value = {"/" , "/index"})
    public String index(Model model, Principal principal, HttpServletRequest request) throws IOException {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /index page... - user : " + user.getUsername());
        CustomUser currentCustomUser = userService.findByUsername(user.getUsername());
        model.addAttribute("currentUser", currentCustomUser);
        if(request != null) {
            model.addAttribute("request", request);
        }

        Collection<GrantedAuthority> grantedAuthorities = user.getAuthorities();
        for(GrantedAuthority grantedAuthority : grantedAuthorities) {
            if(grantedAuthority.getAuthority().equals("ROLE_WAIT_VERIF")){
                return "redirect:/wait-verification-mail/" + currentCustomUser.getHashValidation();
            }
        }
        if(currentCustomUser == null){
            return "unknown_user";
        }

        return "redirect:/workplace?l=1&w=1";
    }

    @GetMapping(value = "/login")
    public String showLogin(@RequestParam(value = "error", required = false) String error,
                            Model model, HttpServletRequest request) {
        log.debug("show login form..." + error);

        model.addAttribute("request", request);

        if(error != null){
            model.addAttribute("message", "Unknown user");
        }
        return "login_form";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal, HttpServletRequest request) {
        log.debug("show /403 ...");
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            CustomUser currentCustomUser = userService.findByUsername(loginedUser.getUsername());

            Collection<GrantedAuthority> grantedAuthorities = loginedUser.getAuthorities();
            for(GrantedAuthority grantedAuthority : grantedAuthorities) {
                if(grantedAuthority.getAuthority().equals("ROLE_WAIT_VERIF")){
                    return "redirect:/wait-verification-mail/" + currentCustomUser.getHashValidation();
                }
            }
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
            model.addAttribute("currentUser", currentCustomUser);
        }
        model.addAttribute("request", request);
        return "403";
    }

    @RequestMapping(value = {"/404"})
    public String notFound404(Model model, Principal principal, HttpServletRequest request) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            log.debug("show /404 form... - user : " + loginedUser.getUsername());
            boolean access =  menuService.addMenuLeftAttributes(model, principal, request, level == null ? 1 : level, enterpriseId, currentEmployeeId, null);
            CustomUser currentCustomUser = userService.findByUsername(loginedUser.getUsername());

            Collection<GrantedAuthority> grantedAuthorities = loginedUser.getAuthorities();
            for(GrantedAuthority grantedAuthority : grantedAuthorities) {
                if(grantedAuthority.getAuthority().equals("ROLE_WAIT_VERIF")){
                    return "redirect:/wait-verification-mail/" + currentCustomUser.getHashValidation();
                }
            }
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
            model.addAttribute("currentUser", currentCustomUser);
        }else{
            log.debug("show /404 - principal == null");
        }
        model.addAttribute("request", request);
        return "_404";
    }

}