package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.Dtos.UserPasswordChangeDto;
import drzazga.daniel.geodezja.services.UserService;
import drzazga.daniel.geodezja.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class ProfileController {

    private final UserService userService;
    private final MessageSource messageSource;


    @Autowired
    public ProfileController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/profil")
    public String showUserProfile(Model model) {
        String userName = UserUtilities.getLoggedUser();

        model.addAttribute("user", userService.findByEmail(userName));
        return "profil";
    }

    @GetMapping("/editpassword")
    public String editUserPassword(Model model) {
        String userName = UserUtilities.getLoggedUser();

        model.addAttribute("user", userService.findByEmailPassChanger(userName));
        return "editpassword";
    }

    @PostMapping("/updatepass")
    public String changeUSerPassword(@Valid @ModelAttribute("user") UserPasswordChangeDto userPasswordChangeDto,
                                     BindingResult result,
                                     Model model,
                                     Locale locale) {

        if(!result.hasErrors()){
            userService.updateUserPassword(userPasswordChangeDto.getPassword(), userPasswordChangeDto.getEmail());
            model.addAttribute("message", messageSource.getMessage("passwordChange.success", null, locale));
        }
        return "editpassword";
    }
}