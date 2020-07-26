package dmroy.expensetracker.controller;

import dmroy.expensetracker.model.CustomUser;
import dmroy.expensetracker.model.UserRole;
import dmroy.expensetracker.service.CustomUserService;
import dmroy.expensetracker.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomUserController {

    @Autowired
    CustomUserService customUserService;
    @Autowired
    UserRoleService userRoleService;

    @GetMapping(value = {"/custom-users"})
    public String customUsers(Model model, Principal principal, HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /custom-users form... - user : " + user.getUsername());
        CustomUser currentUser = customUserService.findByUsername(user.getUsername());
        model.addAttribute("currentUser", currentUser);

        List<CustomUser> customUsers = customUserService.getAll();
        customUsers = customUsers == null ? new ArrayList<>() : customUsers;
        model.addAttribute("header", "All Users: "
                + customUsers == null ? 0 : customUsers.size()
                + " pcs.");
        model.addAttribute("customUsers", customUsers);

        List<UserRole> userRoles = userRoleService.getAll();
        model.addAttribute("userRoles", userRoles);

        model.addAttribute("activeMenuLevel_1", "collapseUser");
        model.addAttribute("activeMenuLevel_2", "custom-users");

        model.addAttribute("request", request);
        return "custom_users";
    }

    @GetMapping(value = {"/custom-user"})
    public String customUserView(@RequestParam(value="cuid") Integer customUserId,
                                 Model model,
                                 Principal principal,
                                 HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /custom-user?cuid=customUserId form... - user : " + user.getUsername() + "; cuid=" + customUserId);
        CustomUser currentUser = customUserService.findByUsername(user.getUsername());
        model.addAttribute("currentUser", currentUser);

        CustomUser customUser = customUserService.findById(customUserId);
        model.addAttribute("customUser",customUser);

        List<UserRole> userRoles = userRoleService.getAll();
        model.addAttribute("userRoles", userRoles);

        model.addAttribute("header", "User: #ID=" + customUserId + "<br>" + customUser.getFI());
        model.addAttribute("activeMenuLevel_1", "collapseUser");
        model.addAttribute("request", request);
        return "custom_user_view";
    }

    @GetMapping(value = {"/custom-user-edit"})
    public String customUserEdit(@RequestParam(value="cuid") Integer customUserId,
                                 Model model,
                                 Principal principal,
                                 HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /custom-user-edit?cuid=customUserId form... - user : " + user.getUsername() + "; cuid=" + customUserId);
        CustomUser currentUser = customUserService.findByUsername(user.getUsername());
        model.addAttribute("currentUser", currentUser);

        CustomUser customUser = customUserService.findById(customUserId);
        model.addAttribute("customUser",customUser);

        List<UserRole> userRoles = userRoleService.getAll();
        model.addAttribute("userRoles", userRoles);

        model.addAttribute("header", "User: #ID=" + customUserId + "<br>" + customUser.getFI());
        model.addAttribute("activeMenuLevel_1", "collapseUser");
        model.addAttribute("request", request);
        return "custom_user_edit";
    }

    @GetMapping(value = {"/custom-user-new"})
    public String customUserNew(Model model,Principal principal,HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /custom-user-new form... - user : " + user.getUsername());
        CustomUser currentUser = customUserService.findByUsername(user.getUsername());
        model.addAttribute("currentUser", currentUser);

        List<UserRole> userRoles = userRoleService.getAll();
        model.addAttribute("userRoles", userRoles);

        model.addAttribute("header", "Add new CustomUser");
        model.addAttribute("activeMenuLevel_1", "collapseUser");
        model.addAttribute("activeMenuLevel_2", "custom-user-new");
        model.addAttribute("request", request);
        return "custom_user_add_new";
    }

    @PostMapping(path = "/custom-user-add")
    public String customUserAddPost(@RequestParam Integer roleId,
                                    @RequestParam String fName,
                                    @RequestParam String sName,
                                    @RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam String repeatPassword,
                                    Principal principal, Model model, HttpServletRequest request) {
        log.debug("post form /custom-user-add form...");

        model.addAttribute("request", request);

        if(!password.trim().equals(repeatPassword.trim())){
            User user = (User) ((Authentication) principal).getPrincipal();
            CustomUser currentUser = customUserService.findByUsername(user.getUsername());
            model.addAttribute("currentUser", currentUser);
            List<UserRole> userRoles = userRoleService.getAll();
            model.addAttribute("userRoles", userRoles);

            model.addAttribute("header", "Password mismatch");
            model.addAttribute("activeMenuLevel_1", "collapseUser");
            model.addAttribute("activeMenuLevel_2", "custom-user-new");
            return "custom_user_add_new";
        }

        CustomUser customUser = this.customUserService.findByUsername(username.trim().toLowerCase());
        if(customUser != null){
            User user = (User) ((Authentication) principal).getPrincipal();
            CustomUser currentUser = customUserService.findByUsername(user.getUsername());
            model.addAttribute("currentUser", currentUser);

            List<UserRole> userRoles = userRoleService.getAll();
            model.addAttribute("userRoles", userRoles);

            model.addAttribute("header", "This user already exists. Try again.");
            model.addAttribute("activeMenuLevel_1", "collapseUser");
            model.addAttribute("activeMenuLevel_2", "custom-user-new");
            return "custom_user_add_new";
        }

        CustomUser customUser1 = customUserService.addCustomUser(fName, sName, username, password, roleId);
        StringBuilder url = new StringBuilder();
        url.append("redirect:/custom-user-add?cuid=");
        url.append(customUser1.getUserIdString());
        return url.toString();
    }

    @PostMapping(path = "/custom-user-update")
    public String customUserUpdatePost(@RequestParam Integer userId,
                                       @RequestParam Integer roleId,
                                       @RequestParam String fName,
                                       @RequestParam String sName,
                                       @RequestParam String username,
                                       @RequestParam(required = false) String password,
                                       @RequestParam(required = false) String repeatPassword,
                                       Model model, HttpServletRequest request
    ) {
        log.debug("post form /custom-user-update form...");

        if(password != null && repeatPassword != null && !password.trim().equals(repeatPassword.trim())){
            CustomUser customUser = customUserService.findById(userId);
            model.addAttribute("header", "Password mismatch");
            model.addAttribute("customUser",customUser);
            model.addAttribute("activeMenuLevel_1", "collapseUser");
            model.addAttribute("request", request);
//            StringBuilder url = new StringBuilder();
//            url.append("redirect:/custom-user-edit?cuid=");
//            url.append(userId);
//            return url.toString();
            return "custom_user_edit";
        }

        CustomUser customUser = this.customUserService.updateCustomUser(userId,fName,sName,username,password,roleId);
        StringBuilder url = new StringBuilder();
        url.append("redirect:/custom-user-update?cuid=");
        url.append(userId);
        return  url.toString();
    }

    @GetMapping(value = {"/custom-user-add", "/custom-user-update"})
    public String customUserAddUpdateGet(@RequestParam(value="cuid") Integer customUserId) {
        log.debug("redirect POST -> GET (from /custom-user-add|update POST to /custom-user-add|update GET) form...");
        StringBuilder url = new StringBuilder();
        url.append("redirect:/custom-users");
        return  url.toString();
    }

    /* ****************************************************** */

    @GetMapping(value = {"/profile-view"})
    public String profileView(Model model, Principal principal, HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /profile-view form... - user : " + user.getUsername());

        CustomUser currentUser = customUserService.findByUsername(user.getUsername());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("customUser",currentUser);

        model.addAttribute("profile","profile");

        List<UserRole> userRoles = userRoleService.getAll();
        model.addAttribute("userRoles", userRoles);

        model.addAttribute("header", "User: #ID=" + currentUser.getUserId() + "<br>" + currentUser.getFI());
        model.addAttribute("activeMenuLevel_1", "collapseUser");
        model.addAttribute("request", request);
        return "custom_user_view";
    }
    @GetMapping(value = {"/profile-edit"})
    public String profileEdit(Model model,Principal principal,HttpServletRequest request) {
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /profile-edit form... - user : " + user.getUsername());
        CustomUser currentUser = customUserService.findByUsername(user.getUsername());
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("customUser",currentUser);

        List<UserRole> userRoles = userRoleService.getAll();
        model.addAttribute("userRoles", userRoles);

        model.addAttribute("profile", "profile");

        model.addAttribute("header", "User: #ID=" + currentUser.getUserId() + "<br>" + currentUser.getFI());
        model.addAttribute("activeMenuLevel_1", "collapseUser");
        model.addAttribute("request", request);
        return "custom_user_edit";
    }

    @PostMapping(path = "/profile-update")
    public String profileUpdatePost(@RequestParam Integer roleId,
                                    @RequestParam String fName,
                                    @RequestParam String sName,
                                    @RequestParam String username,
                                    @RequestParam(required = false) String password,
                                    @RequestParam(required = false) String repeatPassword,
                                    Principal principal, Model model, HttpServletRequest request
    ) {
        log.debug("post form /profile-update form...");
        User user = (User) ((Authentication) principal).getPrincipal();
        log.debug("show /profile-view form... - user : " + user.getUsername());

        CustomUser currentUser = customUserService.findByUsername(user.getUsername());

        if(password != null && repeatPassword != null && !password.trim().equals(repeatPassword.trim())){
            model.addAttribute("header", "Password mismatch");
            model.addAttribute("customUser",currentUser);
            model.addAttribute("profile","profile");
            model.addAttribute("activeMenuLevel_1", "collapseUser");
            model.addAttribute("request", request);
            return "custom_user_edit";
        }

        CustomUser customUser = this.customUserService.updateCustomUser(currentUser.getUserId(),fName,sName,username,password,roleId);
        StringBuilder url = new StringBuilder();
        url.append("redirect:/profile-update");
        return  url.toString();
    }

    @GetMapping(value = {"/profile-update"})
    public String profileUpdateGet() {
        log.debug("redirect POST -> GET (from /profile-update POST to /profiler-update GET) form...");
        StringBuilder url = new StringBuilder();
        url.append("redirect:/profile-view");
        return  url.toString();
    }
}