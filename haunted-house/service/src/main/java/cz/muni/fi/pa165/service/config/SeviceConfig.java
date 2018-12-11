package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.config.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = {"cz.muni.fi.pa165.service"})
public class SeviceConfig {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        //dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }


}
