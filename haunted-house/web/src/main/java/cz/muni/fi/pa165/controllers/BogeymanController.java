package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.enums.BogeymanType;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import cz.muni.fi.pa165.facade.HouseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bogeyman")
public class BogeymanController {

    @Autowired
    private BogeymanFacade bogeymanFacade;

    @Autowired
    private HouseFacade houseFacade;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String bogeymen(Model model) {
        model.addAttribute("bogeymen", bogeymanFacade.findAll());
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
    public String[] houses() {
        List<HouseDto> allHouses = houseFacade.findAll();
        List<String> descriptionList = new ArrayList<>();
        for (HouseDto houseDto: allHouses) {
            descriptionList.add(houseDto.getName());
        }
        return descriptionList.toArray(new String[allHouses.size()]);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBogeyman(@Valid @ModelAttribute("bogeymanCreate") BogeymanCreateDto formBean, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        bogeymanFacade.create(formBean);
        return "redirect:" + uriBuilder.path("/bogeyman/all").toUriString();
    }

}
