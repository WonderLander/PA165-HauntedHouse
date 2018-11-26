package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.AbilityDao;
import cz.muni.fi.pa165.dao.CommentDao;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.service.exception.HauntedHouseException;
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
        Comment comment = null;
        try{
            comment = commentDao.findById(id);
        }catch (Exception e){
            throw new HauntedHouseException("Comment id",e);
        }
        return comment;
    }

    @Override
    public List<Comment> findAll() throws DataAccessException {
        List<Comment>comments=null;
        try {
            comments = commentDao.findAll();
        }catch (Exception e){
            throw new HauntedHouseException("Comment find all",e);
        }
        return comments;
    }

    @Override
    public List<Comment> findAllSortedByDate() throws DataAccessException {
        List<Comment>comments=null;
        try {
            comments = commentDao.findAll();
        }catch (Exception e){
            throw new HauntedHouseException("Comment sorted by date",e);
        }

        comments.sort(Comparator.comparing(o->o.getDate()));

        return comments;
    }

    @Override
    public List<Comment> findAllSortedByAuthor() throws DataAccessException {

        List<Comment>comments=null;
        try {
            comments = commentDao.findAll();
        }catch (Exception e){
            throw new HauntedHouseException("Comment sorted by author",e);
        }

        comments.sort((Comment o1, Comment o2)->o1.getAuthor().compareTo(o2.getAuthor()));


        return comments;
    }

    @Override
    public List<Comment> findByAuthor(String author) throws DataAccessException {
        List<Comment>comments=null;
        try{
            comments=commentDao.findByAuthor(author);
        }catch (Exception e){
            throw new HauntedHouseException("Comment find by author",e);
        }
        return comments;
    }

    @Override
    public List<Comment> findByHouse(House house) throws DataAccessException, IllegalArgumentException {
        List<Comment>comments=null;
        try {
            comments = commentDao.findByHouse(house);
        }catch (Exception e){
            throw new HauntedHouseException("Comment find by house",e);
        }
        return comments;
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
        try {
            commentDao.create(comment);
        }catch (Exception e){
            throw new HauntedHouseException("Comment create", e);
        }
    }

    @Override
    public void delete(Comment comment) throws DataAccessException, IllegalArgumentException {
        try {
            commentDao.delete(comment);
        }catch (Exception e){
            throw new HauntedHouseException("Comment delete", e);
        }
    }

    @Override
    public void update(Comment comment) throws DataAccessException, IllegalArgumentException {
        try {
            commentDao.update(comment);
        }catch (Exception e){
            throw new HauntedHouseException("Comment update", e);
        }
    }

    @Override
    public List<Ability> findMostCommentedAbility() {

        List<Comment>comments = null;
        List<Ability>abilities = null;
        try {
            comments = commentDao.findAll();;
            abilities =abilityDao.findAll();
        }catch (Exception e){
            throw new HauntedHouseException("Comment find most commented ability",e);
        }
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
