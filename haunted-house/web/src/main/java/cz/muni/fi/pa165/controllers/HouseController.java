package cz.muni.fi.pa165.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.HouseCreateDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import cz.muni.fi.pa165.facade.HouseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;


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
    public String create(@ModelAttribute("house") HouseCreateDto house,@ModelAttribute("StringDate")String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date, formatter);
        }catch (DateTimeParseException e){
            localDate = LocalDate.now();
        }
        house.setDate(java.sql.Date.valueOf(localDate));
        System.err.println(localDate);
        houseFacade.createHouse(house);

        return "redirect:/houses";
    }
    @RequestMapping(value = {"/create"},method= RequestMethod.GET)
    public ModelAndView createView(){

        ModelAndView model = new ModelAndView();
        model.setViewName("house/new");
        return model;
    }

    @RequestMapping(value = {"/update"},method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("comment") HouseDto house){
        houseFacade.updateHouse(house);

        ModelAndView model = new ModelAndView();
        model.addObject("house",houseFacade.findHouseById(house.getId()));
        model.setViewName("house/houseView");
        return model;
    }

}
