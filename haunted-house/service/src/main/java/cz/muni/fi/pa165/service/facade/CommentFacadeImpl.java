package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CommentCreateDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.facade.CommentFacade;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import cz.muni.fi.pa165.service.services.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Ondrej Stursa
 */
@Service
@Transactional
public class CommentFacadeImpl implements CommentFacade
{
    private CommentService commentService;

    private BeanMappingService beanMappingService;

    @Inject
    public CommentFacadeImpl(CommentService commentService, BeanMappingService beanMappingService) {
        this.commentService = commentService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public CommentDto findById(long id) {
        return beanMappingService.mapTo(commentService.findById(id),CommentDto.class);
    }

    @Override
    public List<CommentDto> findAll() {
        return beanMappingService.mapTo(commentService.findAll(),CommentDto.class);
    }

    @Override
    public List<CommentDto> findAllSortedByDate() {
        List<Comment>comments=commentService.findAllSortedByDate();
        if(comments!=null){
            return beanMappingService.mapTo(comments,CommentDto.class);
        }
        return null;
    }

    @Override
    public List<CommentDto> findAllSortedByAuthor() {
        List<Comment>comments=commentService.findAllSortedByAuthor();
        if(comments!=null){
            return beanMappingService.mapTo(comments,CommentDto.class);
        }
        return null;
    }

    @Override
    public List<CommentDto> findByAuthor(String author) {
        List<Comment>comments=commentService.findByAuthor(author);
        if(comments!=null){
            return beanMappingService.mapTo(comments,CommentDto.class);
        }
        return null;

    }

    @Override
    public List<CommentDto> findByHouse(HouseDto house) {
        List<Comment>comments=commentService.findByHouse(beanMappingService.mapTo(house, House.class));
        if(comments!=null){
            return beanMappingService.mapTo(comments,CommentDto.class);
        }
        return null;
    }

    @Override
    public HouseDto getHouse(CommentDto comment) {
        return beanMappingService.mapTo(commentService.getHouse(beanMappingService.mapTo(comment,Comment.class)),HouseDto.class);
    }

    @Override
    public boolean isCommentedHouseHaunted(CommentDto comment) {
        return commentService.isCommentedHouseHaunted(beanMappingService.mapTo(comment,Comment.class));
    }

    @Override
    public void create(CommentCreateDto comment) {
        commentService.create(beanMappingService.mapTo(comment,Comment.class));
    }

    @Override
    public void delete(CommentDto comment) {
        commentService.delete(beanMappingService.mapTo(comment,Comment.class));
    }

    @Override
    public void update(CommentDto comment) {
        commentService.update(beanMappingService.mapTo(comment,Comment.class));
    }
}
