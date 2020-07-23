package dmroy.expensetracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import dmroy.expensetracker.model.CustomUser;
import dmroy.expensetracker.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;

@Controller
@Slf4j
public class GlobalUserController {

    @Autowired
    UserService userService;
    @GetMapping(value = {"/global-user"})
    public String profileView(@RequestParam(value="l") Integer level,
                              @RequestParam(value="b", required = false) Integer enterpriseId,
                              @RequestParam(value="e", required = false) Integer currentEmployeeId,
                              Model model,Principal principal,HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        CustomUser currentCustomUser = userService.findByUsername(user.getUsername());
        log.debug("show /global-user form... - user : " + user.getUsername());
        boolean access =  menuService.addMenuLeftAttributes(model, principal, request, level, enterpriseId, currentEmployeeId, null);
        if(!access){
            return menuService.getForbiddenUrl(level, enterpriseId, currentEmployeeId);
        }

        model.addAttribute("header", "Профиль пользователя:<br>" + currentCustomUser.getFIO());
        model.addAttribute("activeMenuLevel_1", "collapseProfile");
        model.addAttribute("activeMenuLevel_2", "global-user");
        return "global_user_view";
    }

    @GetMapping(value = {"/global-user-edit"})
    public String profileEdit(@RequestParam(value="l") Integer level,
                              @RequestParam(value="b", required=false) Integer enterpriseId,
                              @RequestParam(value="e", required=false) Integer employeeId,
                              Model model,
                              Principal principal,
                              HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        CustomUser currentCustomUser = userService.getGlobalUserFull(user.getUsername());
        log.debug("show /global-user-edit?enterpriseId&employeeId form... - user : " + user.getUsername()
                + "; enterpriseId=" + enterpriseId + "; employeeId=" + employeeId);
        boolean access =  menuService.addMenuLeftAttributes(model, principal, request, level, enterpriseId, employeeId, null);
        if(!access){
            return menuService.getForbiddenUrl(level, enterpriseId, employeeId);
        }

        model.addAttribute("header", "Редактирование глобального профиля:<br>" + currentCustomUser.getFIO());
        model.addAttribute("activeMenuLevel_1", "collapseProfile");
        model.addAttribute("activeMenuLevel_2", "global-user");
        return "global_user_edit";
    }

    @PostMapping(path = "/global-user-update")
    public String registrationPost(@RequestParam String username,
                                   @RequestParam String fname,
                                   @RequestParam String sname,
                                   @RequestParam String pname,
                                   @RequestParam String mail,
                                   @RequestParam String phone,
                                   @RequestParam String sex,
                                   @RequestParam String dateBorn,
                                   @RequestParam String dateRegistration,
                                   @RequestParam (value = "profilePhoto", required = false) MultipartFile profilePhoto
                                   ) throws ParseException, IOException {
        log.debug("post form /global-user-update form...");

        this.userService.updateGlobalUser(username,fname,sname,pname,mail,phone,sex,dateBorn,dateRegistration,profilePhoto);
        return "redirect:/global-user-update";
    }

    @GetMapping(value = {"/global-user-update"})
    public String registrationGet() {
        log.debug("redirect POST -> GET (from /global-user-update POST to /global-user-update GET) form...");
        return "redirect:/global-user?l=1";
    }

    @GetMapping(value = {"/global-user-change-pass"})
    public String viewPageChangePassGet(@RequestParam(value="l") Integer level,
                                        @RequestParam(value="b", required=false) Integer enterpriseId,
                                        @RequestParam(value="e", required=false) Integer employeeId,
                                        Model model,
                                        Principal principal,
                                        HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        CustomUser currentCustomUser = userService.getGlobalUserFull(user.getUsername());
        log.debug("show /global-user-change-pass?enterpriseId&employeeId form... - user : " + user.getUsername()
                + "; enterpriseId=" + enterpriseId + "; employeeId=" + employeeId);
        boolean access =  menuService.addMenuLeftAttributes(model, principal, request, level, enterpriseId, employeeId, null);
        if(!access){
            return menuService.getForbiddenUrl(level, enterpriseId, employeeId);
        }
        model.addAttribute("header", "Изменение пароля профиля:<br>" + currentCustomUser.getFIO());
        model.addAttribute("activeMenuLevel_1", "collapseProfile");
        model.addAttribute("activeMenuLevel_2", "global-user");
        return "global_user_change_pass";
    }

    @PostMapping(path = "/change-pass")
    public String changePassPost(@RequestParam (value="l") Integer level,
                                 @RequestParam (value="b", required = false) Integer enterpriseId,
                                 @RequestParam (value="e", required = false) Integer employeeId,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String repeatPassword) {
        CustomUser currentCustomUser = userService.getGlobalUserFull(username);
        log.debug("post form /change-pass POST form...");

        if(password.trim().equals(repeatPassword.trim())) {
            this.userService.setNewPass(currentCustomUser, password);
            log.warn("password has been changed");
        }else{
            log.warn("password doesn't changed");
        }
        StringBuilder url = new StringBuilder();
        url.append("redirect:/change-pass?l=");
        url.append(level);
        if(enterpriseId != null) {
            url.append("&b=");
            url.append(enterpriseId);
        }
        if(employeeId != null) {
            url.append("&e=");
            url.append(employeeId);
        }
        return  url.toString();
    }

    @GetMapping(value = {"/change-pass"})
    public String changePassGet(@RequestParam (value="l") Integer level,
                                @RequestParam (value="b", required = false) Integer enterpriseId,
                                @RequestParam (value="e", required = false) Integer employeeId) {
        log.debug("redirect POST -> GET (from /change-pass POST to /change-pass GET) form...");
        StringBuilder url = new StringBuilder();
        url.append("redirect:/global-user?l=");
        url.append(level);
        if(enterpriseId != null) {
            url.append("&b=");
            url.append(enterpriseId);
        }
        if(employeeId != null) {
            url.append("&e=");
            url.append(employeeId);
        }
        return  url.toString();
    }
}