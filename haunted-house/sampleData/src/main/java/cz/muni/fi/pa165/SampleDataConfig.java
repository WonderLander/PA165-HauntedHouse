package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.service.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Import(ServiceConfig.class)
//@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses = {SampleDataFacadeImpl.class})
public class SampleDataConfig {

    private final SampleDataFacade data;

    @Autowired
    public SampleDataConfig(SampleDataFacade data) {
        this.data = data;
    }


    @PostConstruct
    public void initData() throws IOException {
        data.init();
    }
}
