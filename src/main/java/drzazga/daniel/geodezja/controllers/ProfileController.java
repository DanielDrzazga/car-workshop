package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.services.UserService;
import drzazga.daniel.geodezja.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profil")
    public String showUserProfile(Model model){
        String userName = UserUtilities.getLoggedUser();

        model.addAttribute("user", userService.findByEmail(userName));

        return "profil";
    }
}
