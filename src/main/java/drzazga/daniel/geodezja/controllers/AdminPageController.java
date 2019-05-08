package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminPageController {

    private final UserService userService;

    @Autowired
    public AdminPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String openAdminMainPage(){
        return "admin/admin";
    }

    @GetMapping("/admin/users")
    public String openAdminAllUsersPage(Model model){
        model.addAttribute("userList",userService.findAll());
        return "admin/users";
    }

}