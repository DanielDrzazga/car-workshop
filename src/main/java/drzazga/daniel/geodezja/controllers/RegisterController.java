package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.Dtos.UserRegistrationDto;
import drzazga.daniel.geodezja.services.UserService;
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
public class RegisterController {

    private final UserService userService;
    private MessageSource messageSource;

    @Autowired
    public RegisterController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user",new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/adduser")
    public String registerAction(
                                 @Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto,
                                 BindingResult result,
                                 Model model,
                                 Locale locale){

        if(userService.isEmailExist(userRegistrationDto.getEmail())){
            result.rejectValue("email","error.userEmailExist");
        }

        if(!result.hasErrors()){
            userService.saveUser(userRegistrationDto);
            model.addAttribute("message", messageSource.getMessage("user.register.success", null, locale));
            model.addAttribute("user", new UserRegistrationDto());
            return "register";
        }

        return "register";
    }
}
