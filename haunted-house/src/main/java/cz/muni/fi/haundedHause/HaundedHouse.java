package cz.muni.fi.haundedHause;


import cz.muni.fi.haundedHause.config.InMemoryConfig;
import cz.muni.fi.haundedHause.entity.Info;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static javafx.application.Platform.exit;


public class HaundedHouse
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
        Info info1 = new Info("info1");
        Info info2 = new Info("info2");

        em.getTransaction().begin();
        em.persist(info1);
        em.persist(info2);
        em.getTransaction().commit();
        em.close();


        //Get
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Info> infos = em.createQuery("select i from Info i",Info.class).getResultList();
        for(Info i : infos){
            System.out.println("Id: "+i.getId()+" text: "+i.getText());
        }
        em.close();
        appContext.close();
    }
}
