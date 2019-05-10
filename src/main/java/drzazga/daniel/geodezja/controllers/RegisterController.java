package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.Dtos.UserRegistrationDto;
import drzazga.daniel.geodezja.services.EmailSenderService;
import drzazga.daniel.geodezja.services.UserService;
import drzazga.daniel.geodezja.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class RegisterController {

    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private MessageSource messageSource;

    @Autowired
    public RegisterController(UserService userService, EmailSenderService emailSenderService, MessageSource messageSource) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
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

        if(!result.hasErrors()){
            userRegistrationDto.setActivationCode(UserUtilities.randomStringGenerator());

            String content = messageSource.getMessage("user.register.email.message",null,locale) + userRegistrationDto.getActivationCode();

            emailSenderService.sendEmail(userRegistrationDto.getEmail(),
                    messageSource.getMessage("user.register.email.message.accept", null, locale),
                    content);

            userService.saveUser(userRegistrationDto);

            model.addAttribute("message", messageSource.getMessage("user.register.success.email", null, locale));
            model.addAttribute("user", new UserRegistrationDto());
            return "index";
        }

        return "register";
    }

    @GetMapping("/activatelink/{activationCode}")
    public String activateAccount(@PathVariable("activationCode") String activationCode, Model model, Locale locale) {
        userService.updateUserActivation(1, activationCode);
        model.addAttribute("message", messageSource.getMessage("user.register.success", null, locale));
        return "index";
    }
}
