package cz.muni.fi.pa165.controllers;


import cz.muni.fi.pa165.dto.AbilityCreateDto;
import cz.muni.fi.pa165.facade.AbilityFacade;
import cz.muni.fi.pa165.service.services.AbilityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/ability")
public class AbilityController {

    private final AbilityFacade abilityFacade;
    private final AbilityService abilityService;

    public AbilityController(AbilityFacade abilityFacade, AbilityService abilityService) {
        this.abilityFacade = abilityFacade;
        this.abilityService = abilityService;
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String abilities(Model model) {
        model.addAttribute("abilities", abilityService.getAllAbilities());
        return "ability/all";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newAbility(Model model) {
        model.addAttribute("abilityCreate", new AbilityCreateDto());
        return "ability/new";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String createAbility(@Valid @ModelAttribute("abilityCreate") AbilityCreateDto formBean, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        abilityFacade.createAbility(formBean);
        return "redirect:" + uriBuilder.path("/ability").toUriString();
    }

}
