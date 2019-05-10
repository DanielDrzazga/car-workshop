package drzazga.daniel.geodezja.controllers;

import drzazga.daniel.geodezja.Dtos.PartDto;
import drzazga.daniel.geodezja.Dtos.WorkDto;
import drzazga.daniel.geodezja.model.Part;
import drzazga.daniel.geodezja.model.Work;
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
public class WorkController {

    private final Integer ELEMENTS = 10;
    private WorkService workService;
    private MessageSource messageSource;

    @Autowired
    public WorkController(WorkService workService, MessageSource messageSource) {
        this.workService = workService;
        this.messageSource = messageSource;
    }

    @GetMapping("/addwork")
    public String workForm(Model model) {
        model.addAttribute("work", new WorkDto());
        return "employee/addwork";
    }

    @PostMapping("/addwork")
    public String workAction(@Valid @ModelAttribute("work") WorkDto workDto,
                                 BindingResult result,
                                 Model model,
                                 Locale locale) {

        if (!result.hasErrors()) {
            workService.saveWork(workDto);
            model.addAttribute("message", messageSource.getMessage("works.success", null, locale));
            return "redirect:/employee/works/1";
        }

        return "employee/addwork";
    }

    @GetMapping("/employee/works/{page}")
    public String openAllWorksPage(@PathVariable("page") int page, Model model) {
        Page<Work> workPage = workService.findAll(PageRequest.of(page - 1, ELEMENTS));
        int totalPages = workPage.getTotalPages();
        int currentPage = workPage.getNumber();

        List<Work> workList = workPage.getContent();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("workList", workList);
        model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
        return "employee/parts";
    }

    @GetMapping("/employee/works/edit/{id}")
    public String getWorkToEdit(@PathVariable("id") Long id, Model model) {
        WorkDto workDto = workService.findById(id);
        model.addAttribute("work", workDto);
        return "employee/workedit";
    }

    @PostMapping("/employee/updatework/{id}")
    public String updatePart(@PathVariable("id") Long id,
                             @ModelAttribute WorkDto workDto) {
        workService.updatePart(workDto);
        return "redirect:/employee/works/1";
    }

    @GetMapping(value = "/employee/works/search/{searchWord}/{page}")
    public String openSearchPartsPage(@PathVariable("searchWord") String searchWord,
                                      @PathVariable("page") int page, Model model) {
        Page<Work> workPage = workService.findAllSearch(searchWord, PageRequest.of(page - 1, ELEMENTS));
        int totalPages = workPage.getTotalPages();
        int currentPage = workPage.getNumber();

        List<Work> workList = workPage.getContent();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("workList", workList);

        return "employee/worksearch";
    }

    @GetMapping("/employee/work/delete/{id}")
    public String deletePart(@PathVariable("id") Long id) {
        workService.deleteWorkById(id);
        return "redirect:/employee/works/1";
    }
}
