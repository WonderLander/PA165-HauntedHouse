package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Bogeyman;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
@Repository
public class BogeymanDaoImpl implements BogeymanDao {

    @Override
    public void create(Bogeyman bogeyman) {

    }

    @Override
    public Bogeyman findById(Long id) {
        return null;
    }

    @Override
    public Bogeyman findByName(String name) {
        return null;
    }

    @Override
    public List<Bogeyman> findAll() {
        return null;
    }

    @Override
    public void delete(Bogeyman bogeyman) {

    }

}
