package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.Dtos.ApplicationWorkRegistrationDto;
import drzazga.daniel.geodezja.Dtos.UserDto;
import drzazga.daniel.geodezja.Dtos.UserRegistrationDto;
import drzazga.daniel.geodezja.services.ApplicationWorkService;
import drzazga.daniel.geodezja.services.PartService;
import drzazga.daniel.geodezja.services.UserService;
import drzazga.daniel.geodezja.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Locale;

@Controller
@Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
@RequestMapping("/application")
public class ApplicationRegistrationController {

    private final ApplicationWorkService applicationWorkService;
    private final UserService userService;
    private final PartService partService;
    private final WorkService workService;
    private MessageSource messageSource;

    @Autowired
    public ApplicationRegistrationController(ApplicationWorkService applicationWorkService, UserService userService, PartService partService, WorkService workService, MessageSource messageSource) {
        this.applicationWorkService = applicationWorkService;
        this.userService = userService;
        this.partService = partService;
        this.workService = workService;
        this.messageSource = messageSource;
    }

    @GetMapping("/register")
    public String applicationForm(Model model){
        model.addAttribute("application",new ApplicationWorkRegistrationDto());
        return "employee/applicationregister";
    }

    @PostMapping("/register")
    public String applicationAction(
            @Valid @ModelAttribute("application") ApplicationWorkRegistrationDto applicationWorkRegistrationDto,
            BindingResult result,
            Model model,
            Locale locale){


        if(!result.hasErrors()){
            applicationWorkService.saveApplication(applicationWorkRegistrationDto);
            model.addAttribute("message", messageSource.getMessage("application.register.success", null, locale));
            model.addAttribute("user", new UserRegistrationDto());
            return "index";
        }
        return "employee/applicationregister";
    }

    @ModelAttribute("userDto")
    public Collection<UserDto> findAllAgencyDto(Model model) {
        Collection<UserDto> userDto = userService.findAllCustomers();
        model.addAttribute("userDto", userDto);
        return userDto;
    }
}
