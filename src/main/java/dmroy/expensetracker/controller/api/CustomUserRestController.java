package dmroy.expensetracker.controller.api;

import dmroy.expensetracker.model.CustomUser;
import dmroy.expensetracker.service.CustomUserService;
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
public class CustomUserRestController {

    @Autowired
    CustomUserService customUserService;

    @GetMapping(value = {"/custom-users"})
    public ResponseEntity customUsers() {
        List<CustomUser> customUsers = customUserService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(customUsers == null ? new ArrayList<>() : customUsers);
    }

    @GetMapping(value = {"/custom-user"})
    public ResponseEntity customUserView(@RequestParam(value="cuid") Integer customUserId) {
        CustomUser customUser = customUserService.findById(customUserId);
        return ResponseEntity.status(HttpStatus.OK).body(customUser);
    }

    @PostMapping(value = "/user-exist")
    public ResponseEntity isUsernameExist(@RequestParam(value = "username") String username) {
        CustomUser customUser = customUserService.findByUsername(username);
        UserExist userExist = new UserExist();
        if(customUser != null){
            userExist.setExistUser("Y");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userExist);
    }

    class UserExist {
        String existUser = "N";

        public String getExistUser() {
            return existUser;
        }

        public void setExistUser(String existUser) {
            this.existUser = existUser;
        }
    }
}
