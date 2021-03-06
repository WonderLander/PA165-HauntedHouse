package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.config.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


/**
 * Test methods for CommentDAO
 * @author Lukas Sadlek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CommentDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private HouseDao houseDao;

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;
    private House house1;

    @BeforeMethod
    private void createComments() {
        comment1 = new Comment();
        comment2 = new Comment();
        comment3 = new Comment();

        LocalDate firstDay = LocalDate.of(2018, 10, 28);
        LocalDate theDayBefore = LocalDate.of(2018, 10, 27);

        comment1.setAuthor("Jirina");
        comment1.setDate(Date.valueOf(firstDay));
        comment1.setText("Na pokoji cislo 11 se kazdy den o sedme rano zjevi duch stareho namornika. " +
                         "Vstane z postele, oblekne se, vezme si svuj kufr a odejde pryc. " +
                         "Na ubytovani na pokoji 11 nabizeji vyraznou slevu.");

        comment2.setAuthor("Anonym");
        comment2.setDate(Date.valueOf(firstDay));
        comment2.setText("Prilis daleko do centra");

        comment3.setAuthor("Anonym");
        comment3.setDate(Date.valueOf(theDayBefore));
        comment3.setText("Nemam slov");

        house1 = new House();
        house1.setName("Hotel");
        house1.setAddress("Na plazi");
        house1.setDate(Date.valueOf(LocalDate.of(2018, 10, 15)));
        house1.setHistory("This hotel was built in 1997.");
        comment1.setHouse(house1);
        comment2.setHouse(house1);


        houseDao.createHouse(house1);
        commentDao.create(comment1);
        commentDao.create(comment2);
        commentDao.create(comment3);
    }

    @Test
    public void createAndFindAll() {
        List<Comment> comments = commentDao.findAll();
        Assert.assertEquals(comments.size(), 3);
        Assert.assertTrue(comments.contains(comment1));
        Assert.assertTrue(comments.contains(comment2));
        Assert.assertTrue(comments.contains(comment3));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullId() {
        commentDao.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullAuthor() {
        commentDao.findByAuthor(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullHouse() {
        commentDao.findByHouse(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullComment() {
        commentDao.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullComment() {
        commentDao.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullComment() {
        commentDao.update(null);
    }

    @Test
    public void delete() {
        commentDao.delete(comment1);
        Assert.assertFalse(commentDao.findAll().contains(comment1));
        //Assert.assertEquals(commentDao.findAll().size(), 2);
        //commentDao.delete(comment2);
        Assert.assertFalse(commentDao.findAll().contains(comment2));
        Assert.assertEquals(commentDao.findAll().size(), 1);
        commentDao.delete(comment3);
        Assert.assertEquals(commentDao.findAll().size(), 0);
    }

    @Test
    public void findById() {
        Comment comment = commentDao.findById(comment1.getId());
        Assert.assertEquals(comment1, comment);
    }

    @Test
    public void findByAuthor() {
        List<Comment> comments = commentDao.findByAuthor("Anonym");
        Assert.assertEquals(comments.size(), 2);
        Assert.assertTrue(comments.contains(comment2));
        Assert.assertTrue(comments.contains(comment3));
    }

    @Test
    public void findByHouse() {
        List<Comment> comments = commentDao.findByHouse(house1);
        Assert.assertEquals(comments.size(), 2);
        Assert.assertTrue(comments.contains(comment1));
        Assert.assertTrue(comments.contains(comment2));
    }

    @Test
    public void updateComment() {
        comment1.setText("Zmenila som nazor.");
        commentDao.create(comment1);
        List<Comment> comments = commentDao.findByAuthor("Jirina");
        Assert.assertEquals(comments.size(), 1);
        Assert.assertEquals(comments.get(0).getText(), "Zmenila som nazor.");
    }
}
