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
        //houseFacade.updateHouse(houseDto);
        comment.setHouse(houseDto);
        commentFacade.create(comment);
        //CommentDto commentDto = commentFacade.findByAuthor(comment.getAuthor()).get(0);
        //comment.setHouse(houseDto);

        //List<CommentDto>comments = houseDto.getComments();
        //comments.add(commentDto);
        //houseDto.setComments(comments);
        //commentDto.setHouse(houseDto);

        //houseFacade.updateHouse(houseDto);
        //commentFacade.update(commentDto);
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




    @RequestMapping(path = {"/comments"},method = RequestMethod.GET)
    @ResponseBody
    public List<CommentDto> comments(){
        return commentFacade.findAll();
    }

    @RequestMapping(value = {"/rest/{id}"},method = RequestMethod.GET)
    @ResponseBody
    public CommentDto getComment(@PathVariable("id") long id){
        CommentDto comment = commentFacade.findById(id);
        if(comment != null){
            return comment;
        }else{
            //todo exception
            throw new IllegalArgumentException();
        }
    }

    @RequestMapping(path = {"/{id}"},method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteComment(@PathVariable("id") long id){
        try{
            CommentDto comment = commentFacade.findById(id);
            commentFacade.delete(comment);
        }catch (Exception e){
            //todo exception
            throw new IllegalArgumentException();
        }
    }

    @RequestMapping(path = {"/rest/create"},method = RequestMethod.POST)
    @ResponseBody
    public void createRest(@RequestBody CommentCreateDto comment){
        try{
            commentFacade.create(comment);
        }catch (Exception e){
            //todo exception
            throw new IllegalArgumentException();
        }
    }

    @RequestMapping(path = {"/{id}"},method = RequestMethod.PUT)
    @ResponseBody
    public void update(@PathVariable("id")long id,@RequestBody CommentDto comment){
        try{
            commentFacade.update(comment);
        }catch (Exception e){
            //todo exception
            throw new IllegalArgumentException();
        }
    }

    @RequestMapping(path = {"/sorted/date"},method = RequestMethod.GET)
    @ResponseBody
    public List<CommentDto> findAllSortedByDate(){
        return commentFacade.findAllSortedByDate();
    }

    @RequestMapping(path = {"/sorted/author"},method = RequestMethod.GET)
    @ResponseBody
    public List<CommentDto> findAllSortedByAuthor(){
        return commentFacade.findAllSortedByAuthor();
    }

    @RequestMapping(path = {"/find/house/{id}"},method = RequestMethod.GET)
    @ResponseBody
    public List<CommentDto> findByHouse(@PathVariable("id")long commentId){
        try {
            CommentDto comment = commentFacade.findById(commentId);
            return commentFacade.findByHouse(commentFacade.getHouse(comment));
        }catch (Exception e){
            //todo exception
            throw new IllegalArgumentException();
        }
    }

    @RequestMapping(path = {"/find/author/{name}"},method = RequestMethod.GET)
    @ResponseBody
    public List<CommentDto> findByAuthor(@PathVariable("name")String name){
        try {
            return commentFacade.findByAuthor(name);
        }catch (Exception e){
            //todo exception
            throw new IllegalArgumentException();
        }
    }
}
