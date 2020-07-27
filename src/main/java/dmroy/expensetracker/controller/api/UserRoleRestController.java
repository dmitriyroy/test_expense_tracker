package dmroy.expensetracker.controller.api;

import dmroy.expensetracker.model.UserRole;
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
public class UserRoleRestController {

    @Autowired
    UserRoleService userRoleService;

    @GetMapping(value = {"/user-roles"})
    public ResponseEntity customUsers() {
        List<UserRole> userRoles = userRoleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(userRoles == null ? new ArrayList<>() : userRoles);
    }

    @GetMapping(value = {"/user-role"})
    public ResponseEntity customUserView(@RequestParam(value="urid") Integer userRoleId) {
        UserRole userRole = userRoleService.findById(userRoleId);
        return ResponseEntity.status(HttpStatus.OK).body(userRole);
    }

}