package cz.muni.fi.pa165.service.services;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Lukas Sadlek
 */
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper mapper;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedList = new ArrayList<>();
        for (Object object : objects) {
            mappedList.add(mapper.map(object, mapToClass));
        }
        return mappedList;
    }

    @Override
    public <T> T mapTo(Object o, Class<T> mapToClass) {
        return mapper.map(o, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }
}
