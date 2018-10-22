package cz.muni.fi;


import cz.muni.fi.haundedHause.config.InMemoryConfig;
import cz.muni.fi.haundedHause.entity.Comment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class HauntedHouse
{
    private static EntityManagerFactory emf;

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(InMemoryConfig.class);
        emf = Persistence.createEntityManagerFactory("default");
        System.out.println("tady");
        EntityManager em = emf.createEntityManager();
        //Example
        //Save
        Comment comment1 = new Comment("comment1");
        Comment comment2 = new Comment("comment2");

        em.getTransaction().begin();
        em.persist(comment1);
        em.persist(comment2);
        em.getTransaction().commit();
        em.close();


        //Get
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Comment> comments = em.createQuery("select i from Comment i", Comment.class).getResultList();
        for(Comment i : comments){
            System.out.println("Id: "+i.getId()+" text: "+i.getText());
        }
        em.close();
        appContext.close();
    }
}
