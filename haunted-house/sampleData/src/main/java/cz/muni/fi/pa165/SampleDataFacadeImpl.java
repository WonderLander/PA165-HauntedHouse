package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.service.services.AbilityService;
import cz.muni.fi.pa165.service.services.BogeymanService;
import cz.muni.fi.pa165.service.services.CommentService;
import cz.muni.fi.pa165.service.services.HouseService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class SampleDataFacadeImpl implements SampleDataFacade {

    private AbilityService abilityService;
    private BogeymanService bogeymanService;
    private CommentService commentService;
    private HouseService houseService;

    @Inject
    public SampleDataFacadeImpl(AbilityService abilityService, BogeymanService bogeymanService, CommentService commentService, HouseService houseService) {
        this.abilityService = abilityService;
        this.bogeymanService = bogeymanService;
        this.commentService = commentService;
        this.houseService = houseService;
    }

    @Override
    public void init() {

        House house = createHouse("Old residence","Address 1",LocalDate.now(),"text 1", null,null);
        Comment comment = createComment("Author 1",LocalDate.now(),"Text 1",house);
        House house1 = createHouse("House 2","addr2",LocalDate.now(),"history2",null,null);
        House house2 = createHouse("House 3","addr3",LocalDate.now(),"history3",null,null);
        Comment comment2 = createComment("Author 2",LocalDate.now(),"Text 2",house);
        createComment("Author2",LocalDate.now(),"Text2",house2);
    }

    private Comment createComment(String author, LocalDate date, String text, House house){
        Comment comment = new Comment(author,date,text);
        comment.setHouse(house);

        commentService.create(comment);

        return comment;
    }

    private House createHouse(String name, String address, LocalDate date,
                              String history, List<Comment> comments, Bogeyman bogeyman){
        House house = new House();
        house.setName(name);
        house.setAddress(address);
        house.setDate(date);
        house.setHistory(history);
        if(comments!=null) {
            for (Comment c : comments) {
                house.addComment(c);
            }
        }

        house.addBogeyman(bogeyman);

        houseService.createHouse(house);

        return house;
    }
}
