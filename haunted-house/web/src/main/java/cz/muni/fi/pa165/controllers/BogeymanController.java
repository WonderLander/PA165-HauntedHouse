package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.enums.BogeymanType;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import cz.muni.fi.pa165.facade.HouseFacade;
import cz.muni.fi.pa165.service.services.BogeymanService;
import cz.muni.fi.pa165.validator.BogeymanCreateDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Lukas Sadlek
 */
@Controller
@RequestMapping("/bogeyman")
public class BogeymanController {

    private final BogeymanService bogeymanService;
    private final BogeymanFacade bogeymanFacade;
    private final HouseFacade houseFacade;

    @Autowired
    public BogeymanController(BogeymanService bogeymanService, BogeymanFacade bogeymanFacade, HouseFacade houseFacade) {
        this.bogeymanService = bogeymanService;
        this.bogeymanFacade = bogeymanFacade;
        this.houseFacade = houseFacade;
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String bogeymen(Model model) {
        model.addAttribute("bogeymen", bogeymanService.findAll());
        return "bogeyman/all";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newBogeyman(Model model) {
        model.addAttribute("bogeymanCreate", new BogeymanCreateDto());
        return "bogeyman/new";
    }

    @ModelAttribute("types")
    public BogeymanType[] types() {
            return BogeymanType.values();
    }

    @ModelAttribute("houses")
    public List<String> houses() {
        return houseFacade.getNames();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof BogeymanCreateDto) {
            binder.addValidators(new BogeymanCreateDtoValidator());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBogeyman(@Valid @ModelAttribute("bogeymanCreate") BogeymanCreateDto formBean ,BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "bogeyman/new";
        }
        bogeymanFacade.create(formBean);
        return "redirect:" + uriBuilder.path("/bogeyman").toUriString();
    }

    @RequestMapping(value = "/delete/{bogeymanId}", method = RequestMethod.GET)
    public String deleteBogeyman(@PathVariable("bogeymanId") long bogeymanId, UriComponentsBuilder uriBuilder,
                                 ServletRequest r) {
        UserDto user = (UserDto) r.getAttribute("authenticatedUser");
        if (!user.isAdmin())
        {
            return "bogeyman/unableToDelete";
        }
        try
        {
            bogeymanFacade.delete(bogeymanFacade.findById(bogeymanId));

        } catch (Exception ex)
        {
            return "bogeyman/unableToDelete";
        }
        return "redirect:" + uriBuilder.path("/bogeyman").toUriString();
    }

    @RequestMapping(value = { "/detail/{id}" }, method = RequestMethod.GET)
    public String bogeymanDetail(@PathVariable("id") long id, Model model) {
        BogeymanDto bogeyman = bogeymanFacade.findById(id);
        model.addAttribute("bogeyman", bogeyman);
        model.addAttribute("abilities", bogeyman.getAbilities());
        return "bogeyman/detail";
    }

}
