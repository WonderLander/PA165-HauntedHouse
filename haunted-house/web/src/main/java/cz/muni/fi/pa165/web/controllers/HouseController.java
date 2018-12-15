package cz.muni.fi.pa165.web.controllers;

import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseCreateDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.facade.HouseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Lukas Sadlek
 */
@Controller
@RequestMapping("/house")
public class HouseController {
    private final HouseFacade houseFacade;

    @Autowired
    public HouseController(HouseFacade houseFacade) {
        this.houseFacade = houseFacade;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<HouseDto> findAll() {
        return houseFacade.findAll();
    }

    @RequestMapping
    public HouseDto findHouseById(@PathVariable Long id) {
        return houseFacade.findHouseById(id);
    }

    @RequestMapping
    public HouseDto findHouseByName(String name) {
        return houseFacade.findHouseByName(name);
    }

    @RequestMapping
    public void createHouse(HouseCreateDto house) {
        houseFacade.createHouse(house);
    }

    @RequestMapping
    public void deleteHouse(HouseDto house) {
        houseFacade.deleteHouse(house);
    }

    @RequestMapping
    public void updateHouse(HouseDto house) {
        houseFacade.updateHouse(house);
    }

    @RequestMapping
    public List<HouseDto> getSortedHousesAfterDate(LocalDate date) {
        return houseFacade.getSortedHousesAfterDate(date);
    }

    @RequestMapping
    public HouseDto findByAddress(String address) {
        return houseFacade.findByAddress(address);
    }

    @RequestMapping
    public boolean isHauntedByBogeyman(HouseDto house) {
        return houseFacade.isHauntedByBogeyman(house);
    }

    @RequestMapping
    public List<CommentDto> getComments(HouseDto house) {
        return houseFacade.getComments(house);
    }

    @RequestMapping
    public List<BogeymanDto> getBogeymen(HouseDto house) {
        return houseFacade.getBogeymen(house);
    }

    @RequestMapping
    public void commentHouse(HouseDto house, CommentDto comment) {
        houseFacade.commentHouse(house, comment);
    }
}
