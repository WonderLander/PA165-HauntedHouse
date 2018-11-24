package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.CommentDao;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService
{
    @Inject
    CommentDao commentDao;

    @Override
    public Comment findById(long id) throws DataAccessException {
        return commentDao.findById(id);
    }

    @Override
    public List<Comment> findAll() throws DataAccessException {
        return commentDao.findAll();
    }

    @Override
    public List<Comment> findAllSortedByDate() throws DataAccessException {
        List<Comment>comments=commentDao.findAll();

        comments.sort((Comment o1, Comment o2)->o1.getDate().compareTo(o2.getDate()));

        return comments;
    }

    @Override
    public List<Comment> findAllSortedByAuthor() throws DataAccessException {
        List<Comment>comments=commentDao.findAll();

        comments.sort((Comment o1, Comment o2)->o1.getAuthor().compareTo(o2.getAuthor()));

        return comments;
    }

    @Override
    public List<Comment> findByAuthor(String author) throws DataAccessException, IllegalArgumentException {
        return commentDao.findByAuthor(author);
    }

    @Override
    public List<Comment> findByHouse(House house) throws DataAccessException, IllegalArgumentException {
        return commentDao.findByHouse(house);
    }

    @Override
    public House getHouse(Comment comment) throws DataAccessException, IllegalArgumentException {
        return comment.getHouse();
    }

    @Override
    public boolean isCommentedHouseHaunted(Comment comment) throws DataAccessException, IllegalArgumentException {
        return !(comment.getHouse().getBogeymen().isEmpty());
    }

    @Override
    public void create(Comment comment) throws DataAccessException, IllegalArgumentException {
        commentDao.create(comment);
    }

    @Override
    public void delete(Comment comment) throws DataAccessException, IllegalArgumentException {
        commentDao.delete(comment);
    }

    @Override
    public void update(Comment comment) throws DataAccessException, IllegalArgumentException {
        update(comment);
    }
}
