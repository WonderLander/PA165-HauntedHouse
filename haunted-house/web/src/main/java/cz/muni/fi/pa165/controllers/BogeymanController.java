package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.enums.BogeymanType;
import cz.muni.fi.pa165.facade.AbilityFacade;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import cz.muni.fi.pa165.facade.HouseFacade;
import cz.muni.fi.pa165.service.services.BogeymanService;
import cz.muni.fi.pa165.validator.BogeymanCreateDtoValidator;
import cz.muni.fi.pa165.validator.BogeymanDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
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
    private final AbilityFacade abilityFacade;

    @Autowired
    public BogeymanController(BogeymanService bogeymanService, BogeymanFacade bogeymanFacade, HouseFacade houseFacade,
                              AbilityFacade abilityFacade) {
        this.bogeymanService = bogeymanService;
        this.bogeymanFacade = bogeymanFacade;
        this.houseFacade = houseFacade;
        this.abilityFacade = abilityFacade;
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
        if (binder.getTarget() instanceof BogeymanDto) {
            binder.addValidators(new BogeymanDtoValidator());
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

    @ModelAttribute("freeAbilities")
    public List<String> freeAbilities() {
        List<String> abilities = new ArrayList<>();
        for (AbilityDto ability: abilityFacade.getAllAbilities()) {
            abilities.add(ability.getName());
        }
        return Collections.unmodifiableList(abilities);
    }

    @RequestMapping(value = { "/detail/{id}" }, method = RequestMethod.GET)
    public String bogeymanDetail(@PathVariable("id") long id,
                                  Model model) {
        BogeymanDto bogeyman = bogeymanFacade.findById(id);
        model.addAttribute("bogeyman", bogeyman);
        model.addAttribute("abilities", bogeyman.getAbilities());
        model.addAttribute("addAbility", new AbilityCreateDto());
        return "bogeyman/detail";
    }

    @RequestMapping(value = "/addAbility/{id}", method = RequestMethod.POST)
    public String addAbility(@PathVariable("id") long id, @Valid @ModelAttribute("addAbility") AbilityCreateDto ability,
                             BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        AbilityDto newAbility = abilityFacade.getAbilityByName(ability.getName());
        BogeymanDto bogeymanDto = bogeymanFacade.findById(id);
        bogeymanDto.addAbility(newAbility);
        bogeymanFacade.update(bogeymanDto);
        return "redirect:" + uriBuilder.path("/bogeyman/detail/{id}").toUriString();
    }

    @RequestMapping(value = {"/edit/{id}"},method = RequestMethod.GET)
    public ModelAndView editView(@PathVariable("id")long id){
        ModelAndView model = new ModelAndView();

        BogeymanDto bogeymanDto = bogeymanFacade.findById(id);
        model.addObject("savedBogey",bogeymanDto);
        model.addObject("bogey",new BogeymanDto());
        model.addObject("houseString",new String());
        model.setViewName("bogeyman/edit");
        return model;
    }

    @RequestMapping(value = {"/edit"},method = RequestMethod.POST)
    public ModelAndView update(@Valid @ModelAttribute("bogey") BogeymanDto bogey,BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                //model.addAttribute(fe.getField() + "_error", true);
                modelAndView.addObject(fe.getField() + "_error", true);
            }
            modelAndView.setViewName("bogeyman/edit");
            return modelAndView;
        }

        bogeymanFacade.update(bogey);


        modelAndView.addObject("bogeyman",bogeymanFacade.findById(bogey.getId()));
        modelAndView.setViewName("bogeyman/detail");
        return modelAndView;

    }


}
