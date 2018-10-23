package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao
{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Comment> findAll() {
        List<Comment> comments = manager.createQuery("SELECT c FROM Comment c",Comment.class).getResultList();
        return comments;
    }

    @Override
    public Comment findById(Long id) {
        return manager.find(Comment.class,id);
    }

    @Override
    public List<Comment> findByAuthor(String author) {
        try {
            List<Comment> comments = manager.createQuery(
                    "SELECT c FROM Comment c WHERE c.author = :author").setParameter("author", author).getResultList();
            return comments;
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void create(Comment comment) {
        manager.persist(comment);
    }

    @Override
    public void delete(Comment comment) {
        manager.remove(comment);
    }

}
