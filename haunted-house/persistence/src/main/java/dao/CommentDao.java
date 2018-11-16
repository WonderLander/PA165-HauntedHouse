package dao;



import entity.Comment;
import entity.House;

import java.util.List;

public interface CommentDao
{
    /**
     * Find all comments in database
     * @return list of all comments in database
     */
    public List<Comment>findAll();

    /**
     * Find comment with id
     * @param id: id of the comment
     * @return instance of the comment
     * @throws IllegalArgumentException if id is null
     */
    public Comment findById(Long id);

    /**
     * Find all comments by author
     * @param author: name of the author
     * @return list of comments
     * @throws IllegalArgumentException if author is null
     */
    public List<Comment> findByAuthor(String author);

    /**
     * Find all comments about the house
     * @param house
     * @return list of comments related to the house or null if house is not in database
     * @throws IllegalArgumentException if house is null
     */
    public List<Comment>findByHouse(House house);

    /**
     * Create new entry in database
     * @param comment comment to be saved to the database
     * @throws IllegalArgumentException if comment is null
     */
    public void create(Comment comment);

    /**
     * Delete comment from database
     * @param comment comment to be removed from the database
     * @throws IllegalArgumentException if comment is null
     */
    public void delete(Comment comment);
}
