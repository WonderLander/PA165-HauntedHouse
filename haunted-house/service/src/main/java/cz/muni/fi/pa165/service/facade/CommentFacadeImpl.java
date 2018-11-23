package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CommentCreateDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.facade.CommentFacade;

import java.util.List;

public class CommentFacadeImpl implements CommentFacade
{
    @Override
    public CommentDto findById(long id) {
        return null;
    }

    @Override
    public List<CommentDto> findAll() {
        return null;
    }

    @Override
    public List<CommentDto> findAllSortedByDate() {
        return null;
    }

    @Override
    public List<CommentDto> findAllSortedByAuthor() {
        return null;
    }

    @Override
    public List<CommentDto> findByAuthor(String author) {
        return null;
    }

    @Override
    public List<CommentDto> findByHouse(HouseDto house) {
        return null;
    }

    @Override
    public HouseDto getHouse(CommentDto comment) {
        return null;
    }

    @Override
    public boolean isCommentedHouseHaunted(CommentDto comment) {
        return false;
    }

    @Override
    public void create(CommentCreateDto Comment) {

    }

    @Override
    public void delete(CommentDto Comment) {

    }

    @Override
    public void update(CommentDto Comment) {

    }
}
