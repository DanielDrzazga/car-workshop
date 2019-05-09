package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.Dtos.UserFileDto;
import drzazga.daniel.geodezja.Dtos.UserUpdateDto;
import drzazga.daniel.geodezja.model.User;
import drzazga.daniel.geodezja.services.AdminService;
import drzazga.daniel.geodezja.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminPageController {

    private final Integer ELEMENTS = 10;
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
        Page<User> pages = adminService.findAllSearch(searchWord, PageRequest.of(page-1, ELEMENTS));
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

    @GetMapping("/admin/users/importusers")
    public String showUploadPageFromXML(Model model) {
        return "admin/importusers";
    }

    @PostMapping("/admin/users/upload")
    public String importUsersFromXML(@RequestParam("filename") MultipartFile mFile) {

        String uploadDir = System.getProperty("user.dir") + "/uploads";
        File file;
        try {
            file = new File(uploadDir);
            if (!file.exists()) {
                file.mkdir();
            }
            Path fileAndPath = Paths.get(uploadDir, mFile.getOriginalFilename());
            Files.write(fileAndPath, mFile.getBytes());
            file = new File(fileAndPath.toString());
            List<UserFileDto> userList = UserUtilities.usersDataLoader(file);
            //adminService.insertInBatch(userList);
            adminService.saveAll(userList);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/users/1";
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