package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.AbilityDao;
import cz.muni.fi.pa165.dao.CommentDao;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

/**
 * @author Ondrej Stursa
 */
@Service
public class CommentServiceImpl implements CommentService
{


    AbilityDao abilityDao;

    CommentDao commentDao;

    @Inject
    public CommentServiceImpl(CommentDao commentDao,AbilityDao abilityDao) {
        this.abilityDao = abilityDao;
        this.commentDao = commentDao;
    }


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

    @Override
    public List<Ability> findMostCommentedAbility() {

        List<Comment>comments = commentDao.findAll();
        List<Ability>abilities = abilityDao.findAll();
        Map<Ability,Integer> map = new HashMap<>();
        for(Ability a: abilities){
            map.put(a,0);
        }
        for(Comment c: comments){
            for(Ability a: abilities){
                if(c.getText().toLowerCase().contains(a.getName())){
                    map.put(a,map.get(a) + 1);
                }
            }
        }
        int maxValue=Collections.max(map.values());
        if(maxValue==0){
            return new ArrayList<>();
        }
        List<Ability>ret = new ArrayList<>();
        for(Map.Entry<Ability,Integer>entry:map.entrySet()){
            if(entry.getValue()==maxValue){
                ret.add(entry.getKey());
            }
        }


        return ret;
    }
}
