package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Comment;

import java.util.List;

public interface CommentDao
{
    public List<Comment>findAll();
    public Comment findById(Long id);
    public List<Comment> findByAuthor(String author);
    public void create(Comment comment);
    public void delete(Comment comment);
}
