package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.CommentCreateDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.facade.CommentFacade;
import cz.muni.fi.pa165.facade.HouseFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(path = {"comments"})
public class CommentController
{
    private final CommentFacade commentFacade;
    private final HouseFacade houseFacade;

    @Inject
    public CommentController(CommentFacade commentFacade, HouseFacade houseFacade) {
        this.commentFacade = commentFacade;
        this.houseFacade = houseFacade;
    }


    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView model = new ModelAndView();
        List<CommentDto>comments = commentFacade.findAll();
        model.addObject("comments",comments);
        model.setViewName("comments");
        return model;
    }

    @RequestMapping(value = {"/create"},method= RequestMethod.POST)
    public String create(@ModelAttribute("comment")CommentCreateDto comment,@ModelAttribute("id")long id){
        comment.setDate(LocalDate.now());
        HouseDto houseDto = houseFacade.findHouseById(id);

        commentFacade.create(comment);

        List<CommentDto> commentDtos = commentFacade.findByAuthor(comment.getAuthor());
        CommentDto commentDto = commentDtos.stream().max(Comparator.comparing(v->v.getId())).get();

        houseFacade.commentHouse(houseDto,commentDto);

        return "redirect:/comments/house/"+id;
    }

    @RequestMapping(value = {"/house/{houseId}"},method = RequestMethod.GET)
    public ModelAndView houseComments(@PathVariable("houseId") long houseId){
        ModelAndView model = new ModelAndView();
        HouseDto house = houseFacade.findHouseById(houseId);

        model.addObject("house",house);
        model.addObject("comments",house.getComments());
        model.setViewName("comment/houseComments");

        return model;
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id")long id){
        commentFacade.delete(commentFacade.findById(id));

        return "redirect:/houses";
    }
}
