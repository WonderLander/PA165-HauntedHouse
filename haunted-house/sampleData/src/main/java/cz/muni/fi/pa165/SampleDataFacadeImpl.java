package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.service.services.AbilityService;
import cz.muni.fi.pa165.service.services.BogeymanService;
import cz.muni.fi.pa165.service.services.CommentService;
import cz.muni.fi.pa165.service.services.HouseService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Named
public class SampleDataFacadeImpl implements SampleDataFacade {

    //private AbilityService abilityService;
    private BogeymanService bogeymanService;
    private CommentService commentService;
    private HouseService houseService;

    @Inject
    public SampleDataFacadeImpl(/*AbilityService abilityService, */BogeymanService bogeymanService, CommentService commentService, HouseService houseService) {
        //this.abilityService = abilityService;
        this.bogeymanService = bogeymanService;
        this.commentService = commentService;
        this.houseService = houseService;
    }

    @Override
    public void init() {
        House house = createHouse("House 1","Address 1",LocalDate.now(),"text 1",null,null);
        Comment comment = createComment("Author 1",LocalDate.now(),"Text 1",null);
    }

    private Comment createComment(String author, LocalDate date, String text, House house){
        Comment comment = new Comment(author,date,text);
        comment.setHouse(house);

        commentService.create(comment);

        return comment;
    }

    private House createHouse(String name, String address, LocalDate date,
                              String history, Comment comment, Bogeyman bogeyman){
        House house = new House();
        house.setName(name);
        house.setAddress(address);
        house.setDate(date);
        house.setHistory(history);
        house.addComment(comment);
        house.addBogeyman(bogeyman);

        houseService.createHouse(house);

        return house;
    }
}
