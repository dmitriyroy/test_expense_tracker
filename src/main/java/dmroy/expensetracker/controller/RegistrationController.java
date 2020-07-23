package dmroy.expensetracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dmroy.expensetracker.model.FaqChapter;
import dmroy.expensetracker.model.FaqSection;
import dmroy.expensetracker.model.CustomUser;
import dmroy.expensetracker.service.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/verification-mail/{hash_registration}")
    public String registration(@PathVariable("hash_registration") String hash_registration,
                               Model model, HttpServletRequest request) {
        log.debug("show verification-mail form...");

        StringBuilder message = new StringBuilder();

        CustomUser customUser = userService.validationMail(hash_registration);

        if(customUser == null){
            message.append("Ошибочная ссылка на верификацию email");
        }else{
            message.append("Вы подтвердили свой email. <a href=\"/login\" title=\"Войти в аккаунт\" >Войти в аккаунт</a>");
            model.addAttribute("globalUser", customUser);
        }

        model.addAttribute("message", message);

        model.addAttribute("request", request);

        return "verification_complite";
    }

    @GetMapping(value = "/send-verification-mail/{hash_registration}")
    public String sendRegistrationMail(@PathVariable("hash_registration") String hash_registration,
                                       Model model, HttpServletRequest request) {
        log.debug("show /send-verification-mail/{hash_registration} form...");

        CustomUser customUser = userService.findByHashValidation(hash_registration);
        userService.sendLetterVerificationMail(customUser);

        model.addAttribute("globalUser", customUser);
        String urlRepeat = appPropertyService.getSendVerificationUrl() + hash_registration;
        model.addAttribute("urlRepeat", urlRepeat);

        model.addAttribute("request", request);

        return "repeat_verification_mail";
    }

    @PostMapping(path = "/registration")
    public String registrationPost(@RequestParam String fName,
                                   @RequestParam String sName,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String repeatPassword,
                                   Model model, HttpServletRequest request) throws ParseException {
        log.debug("post form /registration form...");

        model.addAttribute("request", request);

        if(!password.trim().equals(repeatPassword.trim())){
            model.addAttribute("fName", fName);
            model.addAttribute("sName", sName);
            model.addAttribute("email", email);
            return "registration_form";
        }

        CustomUser customUser = this.userService.findByUsername(email.trim().toLowerCase());
        if(customUser != null){
            if(customUser.getIsValidMail() != null && customUser.getIsValidMail().equals("Y")) {
                // пользователь есть и прошел валидацию email
                model.addAttribute("message", "Такой пользователь уже есть");
                model.addAttribute("globalUser", customUser);
                return "registration_form";
            }else{
                // пользователь есть, без учета валидации
                // поскольку была проверка на наличие непровалидированного юзера
                // нужно повтороно отправить письмо на валидацию и открыть страницу с этим текстом
                userService.sendLetterVerificationMail(customUser);
                model.addAttribute("message", "Вы уже ранее регистрировались, но не подтвердили свой email");
                model.addAttribute("urlRepeat", appPropertyService.getSendVerificationUrl() + customUser.getHashValidation());
                return "wait_verification_mail";
            }
        }

        CustomUser customUser1 = userService.addGlobalUserMini(fName, sName, email, password);
        return "redirect:/registration/" + customUser1.getHashValidation();
    }

    @GetMapping(value = {"/registration/{hash_registration}"})
    public String registrationGet(@PathVariable("hash_registration") String hash_registration) {
        log.debug("redirect POST -> GET (from registration POST to registration GET) form...");
        return "redirect:/wait-verification-mail/" + hash_registration;
    }

    @GetMapping(value = {"/wait-verification-mail"})
    public String waitVerificationMail( Model model, HttpServletRequest request) {
        log.debug("show /wait-verification-mail form...");
        model.addAttribute("request", request);
        return "wait_verification_mail";
    }

    @GetMapping(value = {"/wait-verification-mail/{hash_registration}"})
    public String waitVerificationMailExist(@PathVariable("hash_registration") String hash_registration,
                                            Model model, HttpServletRequest request) {
        log.debug("show /wait-verification-mail/{hash_registration} form...");
        CustomUser customUser = userService.findByHashValidation(hash_registration);
        if(customUser != null){
            model.addAttribute("globalUser", customUser);
            model.addAttribute("urlRepeat", appPropertyService.getSendVerificationUrl() + customUser.getHashValidation());
        }

        model.addAttribute("request", request);
        return "wait_verification_mail";
    }

    @GetMapping(value = {"/new-registration"})
    public String registrationNew(Model model, HttpServletRequest request) {
        log.debug("show , /new-registration form...");
        model.addAttribute("request", request);
        return "registration_form";
    }

    @GetMapping(value = {"/forgot-password"})
    public String registrationRedirectFromLogin(Model model, HttpServletRequest request) {
        log.debug("show /redirect-registration form...");
        model.addAttribute("message", "Такого пользователя нет в системе");
        model.addAttribute("request", request);
        return "forgot-password";
    }

    @PostMapping(path = "/reset-password")
    public String resetPasswordPost(@RequestParam String email,
                                   Model model) {
        log.debug("post form /reset-password form...");
        CustomUser customUser = this.userService.findByUsername(email.trim());
        if(customUser == null){
                model.addAttribute("message", "Такого пользователя нет в системе");
                return "forgot-password";
        }
        userService.sendResetPasswordMail(customUser);
        return "redirect:/reset-password";
    }

    @GetMapping(value = {"/reset-password"})
    public String resetPasswordGet(Model model, HttpServletRequest request) {
        log.debug("redirect POST -> GET (from reset-password POST to reset-password GET) form...");
        model.addAttribute("request", request);
        return "wait_reset_password_mail";
    }

    @GetMapping(value = "/set-new-pass/{hash_registration}")
    public String setNewPassword(@PathVariable("hash_registration") String hash_registration,
                                 Model model, HttpServletRequest request) {
        log.debug("show /set-new-pass form...");
        StringBuilder message = new StringBuilder();
        CustomUser customUser = userService.validationMail(hash_registration);
        if(customUser == null){
            message.append("Ошибочная ссылка на верификацию email");
        }else{
            model.addAttribute("fName", customUser.getFirstName());
            model.addAttribute("sName", customUser.getSecondName());
            model.addAttribute("email", customUser.getMail());
        }
        model.addAttribute("message", message);
        model.addAttribute("request", request);
        return "registration_form_reset_pass";
    }

    @PostMapping(path = "/reset-password-form")
    public String setNewPasswordPost(@RequestParam String fName,
                                   @RequestParam String sName,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String repeatPassword,
                                   Model model) {
        log.debug("post form /reset-password-form form...");
        if(!password.trim().equals(repeatPassword.trim())){
            model.addAttribute("fName", fName);
            model.addAttribute("sName", sName);
            model.addAttribute("email", email);
            return "registration_form_reset_pass";
        }
        CustomUser customUser = this.userService.findByUsername(email.trim());
        customUser = userService.setNewPass(customUser, password.trim());
        return "redirect:/reset-password-form";
    }

    @GetMapping(value = {"/reset-password-form"})
    public String setNewPasswordGet() {
        log.debug("redirect POST -> GET (from reset-password-form POST to reset-password-form GET) form...");
        return "redirect:/login";
    }
}