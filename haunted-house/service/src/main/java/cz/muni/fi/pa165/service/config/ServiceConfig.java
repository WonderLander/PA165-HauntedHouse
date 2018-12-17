package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.config.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.service.facade.HouseFacadeImpl;
import cz.muni.fi.pa165.service.services.AbilityServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
//@ComponentScan(basePackages = {"cz.muni.fi.pa165.service","cz.muni.fi.pa165.service.facade"})
@ComponentScan(basePackageClasses = {AbilityServiceImpl.class, HouseFacadeImpl.class})
public class ServiceConfig {

    @Bean
    public Mapper dozer() {
        /*DozerBeanMapper dozer = new DozerBeanMapper();
        //dozer.addMapping(new DozerCustomConfig());

        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");
        dozer.setMappingFiles(mappingFiles);
        return dozer;*/
//        return DozerBeanMapperBuilder.create()
//                .withMappingFiles("dozerMapping.xml", "dozerJdk8Converters.xml")
//                .build();
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {

        @Override
        protected void configure() {
            mapping(Ability.class, AbilityDto.class);
            mapping(House.class, HouseDto.class);
            mapping(Bogeyman.class, BogeymanDto.class);
            mapping(Comment.class, CommentDto.class);
            mapping(LocalDate.class,LocalDate.class);
            mapping(User.class, UserDto.class);

        }
    }
}
