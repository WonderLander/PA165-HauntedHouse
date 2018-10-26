package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Ability;
import cz.muni.fi.hauntedhouse.entity.Bogeyman;
import cz.muni.fi.hauntedhouse.entity.BogeymanType;
import cz.muni.fi.hauntedhouse.entity.House;
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
    public List<Bogeyman> findByHouse(House house) {
        return null;
    }

    @Override
    public List<Bogeyman> findByAbility(Ability ability) {
        return null;
    }

    @Override
    public List<Bogeyman> findByType(BogeymanType type) {
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
