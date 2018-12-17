package cz.muni.fi.pa165.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import cz.muni.fi.pa165.facade.HouseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping(path = {"houses"})
public class HouseController
{
    final
    HouseFacade houseFacade;

    @Autowired
    BogeymanFacade bogeymanFacade;

    @Inject
    public HouseController(HouseFacade houseFacade) {
        this.houseFacade = houseFacade;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView houses(ModelAndView model) {
        model.addObject("houses",houseFacade.findAll());
        model.setViewName("house/houses");
        for(HouseDto houses:houseFacade.findAll()){
            System.err.println(houses.getName());
            System.err.println("comments "+ houses.getComments().size());
            System.err.println("bogyes "+houses.getBogeymen().size());
            System.err.println("----------------");
        }
        for(BogeymanDto bogey:bogeymanFacade.findAll()){
            System.err.println(bogey.getName());
            System.err.println("abilities "+bogey.getAbilities().size());
            System.err.println("house "+bogey.getHouse().getName());
            System.err.println("----------------");
        }
        return model;
    }

    @RequestMapping(value = {"/{id}"},method = RequestMethod.GET)
    public ModelAndView getHouse(@PathVariable("id") long id){
        ModelAndView model = new ModelAndView();
        HouseDto house = houseFacade.findHouseById(id);
        if(house != null){
            System.err.println("--------------");
            System.err.println("bogeys "+house.getBogeymen().size());
            System.err.println("--------------");
            model.addObject("house",house);
            model.setViewName("house/houseView");
            return model;
        }else{
            //todo exception
            throw new IllegalArgumentException();
        }
    }



}
