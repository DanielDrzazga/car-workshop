package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.model.User;
import drzazga.daniel.geodezja.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminPageController {

    private final AdminService adminService;

    @Autowired
    public AdminPageController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String openAdminMainPage() {
        return "admin/admin";
    }

    @GetMapping("/admin/users/{page}")
    public String openAdminAllUsersPage(@PathVariable("page") int page, Model model) {
        int element = 5;
        Page<User> userPage = adminService.findAll(PageRequest.of(page - 1, element));
        int totalPages = userPage.getTotalPages();
        int currentPage = userPage.getNumber();
        List<User> userList = userPage.getContent();

        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage + 1);
        model.addAttribute("userList",userList);
        return "admin/users";
    }

}