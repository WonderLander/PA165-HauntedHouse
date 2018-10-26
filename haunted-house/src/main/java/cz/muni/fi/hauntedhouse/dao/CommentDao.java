package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Comment;

import java.util.List;

public interface CommentDao
{
    /**
     * Find all comments in database
     * @return
     */
    public List<Comment>findAll();

    /**
     * Find comment with id
     * @param id: id of a comment
     * @return instance of comment of null
     */
    public Comment findById(Long id);

    /**
     * Find all comments by author
     * @param author: name of the author
     * @return list fo comments
     */
    public List<Comment> findByAuthor(String author);
    public void create(Comment comment);
    public void delete(Comment comment);
}
