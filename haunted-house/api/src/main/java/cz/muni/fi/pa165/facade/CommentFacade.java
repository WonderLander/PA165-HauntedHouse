package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CommentCreateDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseDto;

import java.util.List;

/**
 * @author Ondrej Stursa
 */
public interface CommentFacade {
    /**
     * find comment by id
     * @param id comment id
     * @return comment dto with id
     */
    CommentDto findById(long id);

    /**
     * find all comments
     * @return list of comments
     */
    List<CommentDto>findAll();

    /**
     * find all comments sorted by date
     * @return list of comments
     */
    List<CommentDto>findAllSortedByDate();

    /**
     * find all comments sorted by author
     * @return list of comments
     */
    List<CommentDto>findAllSortedByAuthor();

    /**
     * find all comments by author
     * @param author
     * @return list of comments
     */
    List<CommentDto>findByAuthor(String author);

    /**
     * find comments by house
     * @param house
     * @return list of comments related to the house
     */
    List<CommentDto>findByHouse(HouseDto house);

    /**
     * get house related to the comment
     * @param comment
     * @return
     */
    HouseDto getHouse(CommentDto comment);

    /**
     * check if commented house is haunted
     * @param comment
     * @return true if haunts is related house
     */
    boolean isCommentedHouseHaunted(CommentDto comment);

    /**
     * create comment in the database
     * @param comment comment to be created
     */
    void create(CommentCreateDto comment);

    /**
     * dalete comment from the database
     * @param comment comment to be deleted
     */
    void delete(CommentDto comment);

    /**
     * update comment in the database
     * @param comment comment to be updated
     */
    void update(CommentDto comment);
}
