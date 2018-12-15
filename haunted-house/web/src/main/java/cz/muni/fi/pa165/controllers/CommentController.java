package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.CommentCreateDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.facade.CommentFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(path = {"comments"})
public class CommentController
{
    private final CommentFacade commentFacade;

    @Inject
    public CommentController(CommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    @RequestMapping(path = {"/comments"},method = RequestMethod.GET)
    @ResponseBody
    public List<CommentDto> comments(){
        return commentFacade.findAll();
    }

    @RequestMapping(value = {"/{id}"},method = RequestMethod.GET)
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

    @RequestMapping(path = {"/create"},method = RequestMethod.POST)
    @ResponseBody
    public void create(@RequestBody CommentCreateDto comment){
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
