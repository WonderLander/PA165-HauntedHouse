package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Comment;
import cz.muni.fi.hauntedhouse.entity.House;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * @author Ondřej Štursa
 */
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
        if(id == null){
            throw new IllegalArgumentException("id is null");
        }
        return manager.find(Comment.class,id);
    }

    @Override
    public List<Comment> findByAuthor(String author) {
        if(author == null){
            throw new IllegalArgumentException("Author name is null");
        }
        try {
            List<Comment> comments = manager.createQuery(
                    "SELECT c FROM Comment c WHERE c.author = :author").setParameter("author", author).getResultList();
            return comments;
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Comment> findByHouse(House house) {
        if(house == null){
            throw new IllegalArgumentException("House is null");
        }
        //if(manager.find(House.class,house.getId())==null){
        if(manager.createQuery("SELECT h FROM House h WHERE h.name = :name").setParameter("name",house.getName()).getResultList().size()==0){
            return null;
        }
        List<Comment>comments=manager.createQuery("SELECT c FROM Comment c WHERE c.house = :house").setParameter("house",house).getResultList();
        return comments;
    }

    @Override
    public void create(Comment comment) {
        if(comment == null){
            throw new IllegalArgumentException("Comment is null");
        }
        manager.persist(comment);
    }

    @Override
    public void delete(Comment comment) {
        if(comment == null){
            throw new IllegalArgumentException("Comment is null");
        }
        manager.remove(comment);
    }

}
