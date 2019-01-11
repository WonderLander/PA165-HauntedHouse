package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.HouseCreateDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.facade.HouseFacade;
import cz.muni.fi.pa165.validator.HouseCreateDtoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.inject.Inject;
import javax.validation.Valid;



/**
 * @author Ondrej Stursa
 */
@Controller
@RequestMapping(path = {"houses"})
public class HouseController
{
    final
    HouseFacade houseFacade;

    @Inject
    public HouseController(HouseFacade houseFacade) {
        this.houseFacade = houseFacade;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof HouseCreateDto) {
            binder.addValidators(new HouseCreateDtoValidator(houseFacade));
        }
    }

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView houses(ModelAndView model) {
        model.addObject("houses",houseFacade.findAll());
        model.setViewName("house/houses");
        return model;
    }

    @RequestMapping(value = {"/{id}"},method = RequestMethod.GET)
    public ModelAndView getHouse(@PathVariable("id") long id){
        ModelAndView model = new ModelAndView();
        HouseDto house = houseFacade.findHouseById(id);
        if(house != null){
            model.addObject("house",house);
            model.setViewName("house/houseView");
            return model;
        }else{
            model.setViewName("house/houseView");
            return model;
        }
    }

    @RequestMapping(value = {"/delete/{id}"})
    public String delete(@PathVariable("id")long id){
        houseFacade.deleteHouse(houseFacade.findHouseById(id));

        return "redirect:/houses";
    }

    @RequestMapping(value = {"/create"},method= RequestMethod.POST)
    public String create(@Valid @ModelAttribute("house") HouseCreateDto house,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "house/new";
        }

        houseFacade.createHouse(house);

        return "redirect:/houses";
    }
    @RequestMapping(value = {"/create"},method= RequestMethod.GET)
    public ModelAndView createView(){

        ModelAndView model = new ModelAndView();
        model.addObject("house",new HouseCreateDto());
        model.setViewName("house/new");
        return model;
    }

    @RequestMapping(value = {"/edit"},method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("house") HouseDto house,BindingResult bindingResult, Model model){
        System.err.println("updated house name is "+house.getName());
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                model.addAttribute("savedHouse",house);
            }
            return "house/houseEdit";
        }

        houseFacade.updateHouse(house);

        return "redirect:/houses";
    }

    @RequestMapping(value = {"/edit/{id}"},method = RequestMethod.GET)
    public ModelAndView editView(@PathVariable("id")long id){
        ModelAndView model = new ModelAndView();

        HouseDto house = houseFacade.findHouseById(id);
        model.addObject("savedHouse",house);
        model.addObject("house",new HouseDto());
        model.setViewName("house/houseEdit");
        return model;
    }



}
