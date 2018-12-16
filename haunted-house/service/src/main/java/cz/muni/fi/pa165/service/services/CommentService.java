package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ondrej Stursa
 */
@Service
public interface CommentService
{
    /**
     * find comment by id
     * @param id comment id
     * @return comment with id
     * @throws DataAccessException if there is a error in persistence layer
     */
    Comment findById(long id)throws DataAccessException;

    /**
     * find all comments
     * @return list of comments
     * @throws DataAccessException if there is a error in persistence layer
     */
    List<Comment> findAll() throws DataAccessException;

    /**
     * find all comments sorted by date
     * @return list of comments
     * @throws DataAccessException if there is a error in persistence layer
     */
    List<Comment>findAllSortedByDate() throws DataAccessException;

    /**
     * find all comments sorted by author
     * @return list of comments
     * @throws DataAccessException if there is a error in persistence layer
     */
    List<Comment>findAllSortedByAuthor() throws DataAccessException;

    /**
     * find all comments by author
     * @param author
     * @return list of comments
     * @throws DataAccessException if there is a error in persistence layer
     * @throws IllegalArgumentException if author is null
     */
    List<Comment>findByAuthor(String author) throws DataAccessException, IllegalArgumentException;

    /**
     * find comments by house
     * @param house
     * @return list of comments related to the house
     * @throws DataAccessException if there is a error in persistence layer
     * @throws IllegalArgumentException if house is null
     */
    List<Comment>findByHouse(House house)throws DataAccessException, IllegalArgumentException;

    /**
     * get house related to the comment
     * @param comment
     * @return
     * @throws DataAccessException if there is a error in persistence layer
     * @throws IllegalArgumentException if comment is null
     */
    House getHouse(Comment comment)throws DataAccessException,IllegalArgumentException;


    /**
     * check if commented house is haunted
     * @param comment
     * @return true if haunts is related house
     * @throws DataAccessException if there is a error in persistence layer
     * @throws IllegalArgumentException if comment is null
     */
    boolean isCommentedHouseHaunted(Comment comment)throws DataAccessException,IllegalArgumentException;

    /**
     * create comment in the database
     * @param comment comment to be created
     * @throws DataAccessException if there is a error in persistence layer
     * @throws IllegalArgumentException if comment is null
     */
    void create(Comment comment)throws DataAccessException, IllegalArgumentException;

    /**
     * dalete comment from the database
     * @param comment comment to be deleted
     * @throws DataAccessException if there is a error in persistence layer
     * @throws IllegalArgumentException
     */
    void delete(Comment comment)throws DataAccessException,IllegalArgumentException;

    /**
     * update comment in the database
     * @param comment comment to be updated
     * @throws DataAccessException if there is a error in persistence layer
     * @throws IllegalArgumentException if comment is null
     */
    void update(Comment comment)throws DataAccessException,IllegalArgumentException;

    /**
     * find most commented abilities
     * @return most commented abilities
     */
    List<Ability> findMostCommentedAbility();
}
