package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.HouseCreateDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.dto.UserDto;
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
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


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
    public String delete(@PathVariable("id")long id,ServletRequest r){
        UserDto user = (UserDto) r.getAttribute("authenticatedUser");
        if (!user.isAdmin())
        {
            return "redirect:/houses/error";
        }
        houseFacade.deleteHouse(houseFacade.findHouseById(id));

        return "redirect:/houses";
    }

    @RequestMapping(value = {"/create"},method= RequestMethod.POST)
    public String create(@Valid @ModelAttribute("house") HouseCreateDto house,BindingResult bindingResult,Model model){
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
    public ModelAndView createView( ServletRequest r){
        UserDto user = (UserDto) r.getAttribute("authenticatedUser");
        ModelAndView model = new ModelAndView();
        if (!user.isAdmin())
        {
            model.setViewName("house/error");
            return model;
        }

        model.addObject("house",new HouseCreateDto());
        model.setViewName("house/new");
        return model;
    }

    @RequestMapping(value = {"/edit"},method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("house") HouseDto house,@ModelAttribute("StringDate") String date){
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date, formatter);
        }catch (DateTimeParseException e){
            localDate = LocalDate.now();
        }
        house.setDate(java.sql.Date.valueOf(localDate));

        houseFacade.updateHouse(house);

        ModelAndView model = new ModelAndView();
        model.addObject("house",houseFacade.findHouseById(house.getId()));
        model.setViewName("house/houseView");
        return model;

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

    @RequestMapping(value = {"/error"})
    public ModelAndView error(){
        ModelAndView model = new ModelAndView();
        model.setViewName("house/houseError");
        return model;
    }

}
