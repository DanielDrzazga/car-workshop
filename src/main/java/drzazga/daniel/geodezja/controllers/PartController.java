package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.Dtos.PartDto;
import drzazga.daniel.geodezja.model.Part;
import drzazga.daniel.geodezja.services.PartService;
import drzazga.daniel.geodezja.services.UserService;
import drzazga.daniel.geodezja.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
public class PartController {

    private final Integer ELEMENTS = 10;
    private final PartService partService;
    private final UserService userService;
    private final MessageSource messageSource;

    @Autowired
    public PartController(PartService partService, UserService userService, MessageSource messageSource) {
        this.partService = partService;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/addpart")
    public String partForm(Model model) {
        model.addAttribute("part", new PartDto());
        return "part/addpart";
    }

    @PostMapping("/addpart")
    public String partAction(@Valid @ModelAttribute("part") PartDto partDto,
                                 BindingResult result,
                                 Model model,
                                 Locale locale) {

        if (!result.hasErrors()) {
            partService.savePart(partDto);
            model.addAttribute("message", messageSource.getMessage("parts.success", null, locale));
            return "redirect:/parts/1";
        }
        return "part/addpart";
    }

    @GetMapping("/parts/{page}")
    public String openAllPartsPage(@PathVariable("page") int page, Model model) {
        Page<Part> partPage = partService.findAll(PageRequest.of(page - 1, ELEMENTS));
        int totalPages = partPage.getTotalPages();
        int currentPage = partPage.getNumber();

        List<Part> partList = partPage.getContent();

        String loggedUser = UserUtilities.getLoggedUser();
        Long role = userService.findByEmail(loggedUser).getRolesDto().iterator().next().getId();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("partList", partList);
        model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
        model.addAttribute("role",role);
        return "part/parts";
    }

    @GetMapping("/parts/edit/{id}")
    public String getPartToEdit(@PathVariable("id") Long id, Model model) {
        PartDto partDto = partService.findById(id);
        model.addAttribute("part", partDto);
        return "part/partedit";
    }

    @PostMapping("/parts/updatepart/{id}")
    public String updatePart(@PathVariable("id") Long id,
                             @ModelAttribute PartDto partDto) {
        partService.updatePart(partDto);
        return "redirect:/parts/1";
    }

    @GetMapping(value = "/parts/search/{searchWord}/{page}")
    public String openSearchPartsPage(@PathVariable("searchWord") String searchWord,
                                      @PathVariable("page") int page, Model model) {
        Page<Part> partPage = partService.findAllSearch(searchWord, PageRequest.of(page - 1, ELEMENTS));
        int totalPages = partPage.getTotalPages();
        int currentPage = partPage.getNumber();

        List<Part> partList = partPage.getContent();

        String loggedUser = UserUtilities.getLoggedUser();
        Long role = userService.findByEmail(loggedUser).getRolesDto().iterator().next().getId();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("partList", partList);
        model.addAttribute("role",role);
        return "part/partssearch";
    }

    @GetMapping("/parts/delete/{id}")
    public String deletePart(@PathVariable("id") Long id) {
        partService.deletePartById(id);
        return "redirect:/parts/1";
    }
}
