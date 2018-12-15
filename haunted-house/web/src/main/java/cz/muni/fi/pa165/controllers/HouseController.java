package cz.muni.fi.pa165.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.facade.HouseFacade;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;

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
    public String sayHello(Model model) {
        model.addAttribute("message","Hello Spring MVC!");

        //Java 8 LocalDate
        DateTimeFormatter formatter=DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        LocalDate date=LocalDate.now();
        model.addAttribute("date", date.format(formatter));

        return "houses";
    }

    @RequestMapping(path = {"/houses"},method = RequestMethod.GET)
    @ResponseBody
    public  String houses(){

        //HouseDto house = new HouseDto();
        //house.setName("house1");
        //house.setAddress("address1");
        List<HouseDto> houses = houseFacade.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String json=null;
        try {
            json = objectMapper.writeValueAsString(houses);
        }catch (Exception e){
            System.err.println("exception");
        }
        return json;
        //return houseFacade.findAll();
        //HouseDto house = new HouseDto();
        //house.setName("house1");
        //house.setAddress("address1");

        //List<HouseDto> ret = Collections.singletonList(house);


    }


}
