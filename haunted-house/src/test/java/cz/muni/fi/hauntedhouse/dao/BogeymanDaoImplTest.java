package cz.muni.fi.hauntedhouse.dao;


import cz.muni.fi.hauntedhouse.config.PersistenceSampleApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes= PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BogeymanDaoImplTest  extends AbstractTestNGSpringContextTests {

    

}
