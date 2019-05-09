package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.Dtos.UserUpdateDto;
import drzazga.daniel.geodezja.model.User;
import drzazga.daniel.geodezja.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminPageController {

    private final Integer ELEMENTS = 3;
    private final AdminService adminService;
    private final MessageSource messageSource;

    @Autowired
    public AdminPageController(AdminService adminService, MessageSource messageSource) {
        this.adminService = adminService;
        this.messageSource = messageSource;
    }

    @GetMapping("/admin")
    public String openAdminMainPage() {
        return "admin/admin";
    }

    @GetMapping("/admin/users/{page}")
    public String openAdminAllUsersPage(@PathVariable("page") int page, Model model) {
        Page<User> userPage = adminService.findAll(PageRequest.of(page - 1, ELEMENTS));
        int totalPages = userPage.getTotalPages();
        int currentPage = userPage.getNumber();
        List<User> userList = userPage.getContent();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("userList", userList);
        model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
        return "admin/users";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String getUserToEdit(@PathVariable("id") Long id, Model model) {

        UserUpdateDto userUpdateDto = adminService.findUserToUpdateById(id);

        Map<Integer, String> roleMap = prepareRoleMap();
        Map<Integer, String> activityMap = prepareActivityMap();

        model.addAttribute("roleMap", roleMap);
        model.addAttribute("activityMap", activityMap);
        model.addAttribute("user", userUpdateDto);
        model.addAttribute("nrRoli", 1);
        return "admin/useredit";
    }

    @PostMapping("/admin/updateuser/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @ModelAttribute UserUpdateDto userUpdateDto) {
        Long nrRoli = userUpdateDto.getNrRoli();
        int czyActive = userUpdateDto.getActive();
        adminService.updateUser(id, nrRoli, czyActive);
        return "redirect:/admin/users/1";
    }

    @GetMapping(value = "/admin/users/search/{searchWord}/{page}")
    public String openSearchUsersPage(@PathVariable("searchWord") String searchWord,
                                      @PathVariable("page") int page, Model model) {
        Page<User> pages = getAllUsersPageable(page - 1, true, searchWord);
        int totalPages = pages.getTotalPages();
        int currentPage = pages.getNumber();
        List<User> userList = pages.getContent();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("userList", userList);
        model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("userList", userList);

        return "admin/usersearch";
    }

    private Page<User> getAllUsersPageable(int page, boolean search, String param) {
        Page<User> pages;
        if (!search) {
            pages = adminService.findAll(PageRequest.of(page, ELEMENTS));
        } else {
            pages = adminService.findAllSearch(param, PageRequest.of(page, ELEMENTS));
        }
        return pages;
    }

    private Map<Integer, String> prepareRoleMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> roleMap = new HashMap<>();
        roleMap.put(1, messageSource.getMessage("word.admin", null, locale));
        roleMap.put(2, messageSource.getMessage("word.user", null, locale));
        return roleMap;
    }

    private Map<Integer, String> prepareActivityMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> activityMap = new HashMap<>();
        activityMap.put(0, messageSource.getMessage("word.nie", null, locale));
        activityMap.put(1, messageSource.getMessage("word.tak", null, locale));
        return activityMap;
    }

}