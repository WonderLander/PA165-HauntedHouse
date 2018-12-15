package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.config.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dto.AbilityDto;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = {"cz.muni.fi.pa165.service","cz.muni.fi.pa165.service.facade"})
public class ServiceConfig {

    @Bean
    public Mapper dozer() {
        /*DozerBeanMapper dozer = new DozerBeanMapper();
        //dozer.addMapping(new DozerCustomConfig());

        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");
        dozer.setMappingFiles(mappingFiles);
        return dozer;*/
        return DozerBeanMapperBuilder.create()
                .withMappingFiles("dozerMapping.xml", "dozerJdk8Converters.xml")
                .build();
    }

    public class DozerCustomConfig extends BeanMappingBuilder {

        @Override
        protected void configure() {
            mapping(Ability.class, AbilityDto.class);
            mapping(House.class, HouseDto.class);
            mapping(Bogeyman.class, BogeymanDto.class);
            mapping(Comment.class, CommentDto.class);
            mapping(LocalDate.class,LocalDate.class);

        }
    }
}
