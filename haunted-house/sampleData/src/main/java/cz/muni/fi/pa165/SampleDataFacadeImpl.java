package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.service.services.AbilityService;
import cz.muni.fi.pa165.service.services.BogeymanService;
import cz.muni.fi.pa165.service.services.CommentService;
import cz.muni.fi.pa165.service.services.HouseService;
import net.bytebuddy.asm.Advice;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Transactional
public class SampleDataFacadeImpl implements SampleDataFacade {

    @Autowired
    private AbilityService abilityService;
    @Autowired
    private BogeymanService bogeymanService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private HouseService houseService;

//    @Inject
//    public SampleDataFacadeImpl(AbilityService abilityService, BogeymanService bogeymanService, CommentService commentService, HouseService houseService) {
//        this.abilityService = abilityService;
//        this.bogeymanService = bogeymanService;
//        this.commentService = commentService;
//        this.houseService = houseService;
//    }

    @Override
    public void init() {
        House house1 = createHouse("Residence at the Corner of the Damned", "Corner of the Damned 666", LocalDate.now(),
                "It is said that this house was once a residence of one of the most dreadful sabbath", null, null);

        House house2 = createHouse("Old Shack", "Forest road 123", LocalDate.now(),
                "Once a place where werewolves celebrated full moon", null, null);

        House house3 = createHouse("Ravenholm's Asylum", "Ravenholm 321", LocalDate.now(),
                "When Ravenholm was overrun by zombies the asylum was last place of sanity", null, null);

        Comment comment1 = createComment("Vila de Cruel", LocalDate.now(), "It was great. Almost shat my pants", house1);
        Comment comment11 = createComment("Pierre-Simon Laplace", LocalDate.now(), "I almost forgot my theorems. 10/10", house1);
        Comment comment2 = createComment("Sarah Luis Kerrigan", LocalDate.now(), "Would not dare to enter without my swarm again.", house2);

        Ability ability2 = createAbility("Salamander eyes broth", "This powerful broth is mainstay of witches cuisine.", 5);
        Ability ability3 = createAbility("Undead", "Nothing can stop a zombie. Not even cannon fire.", 1);
        Ability ability1 = createAbility("Lich Immortality", "Life of a lich is bound to powerful artifact, where the power of lich resides.", 1);

        Bogeyman bogeyman1 = createBogeyman("Lich", house1, BogeymanType.LICH, ability1, LocalTime.now(), LocalTime.now(),
                "Life of a lich is bound to powerful artifact, where the power of lich resides. Destroy the artifact and you " +
                        "shall destroy the lich", "Unfulfilled dreams of becoming a IT specialist drove Mark to bound his life with his old Intel 8080.");

        Bogeyman bogeyman12 = createBogeyman("Witch", house1, BogeymanType.WITCH, ability2, LocalTime.now(), LocalTime.now(),
                "For a witch is a burning at stake only a pleasant entertainment.", "Who would resist the temptation of a broom flight");

        Bogeyman bogeyman3 = createBogeyman("Zombie", house3, BogeymanType.ZOMBIE, ability3, LocalTime.now(), LocalTime.now(),
                "Embodiment ceaseless hunger.", "Brains are brains. For zombies it does not matter whose brain it is.");
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

    private Bogeyman createBogeyman(String name, House house, BogeymanType type, Ability ability,
                                    LocalTime hauntStartTime, LocalTime hauntEndTime, String description,
                                    String reason) {
        Bogeyman bogeyman = new Bogeyman();
        bogeyman.setHouse(house);
        bogeyman.setName(name);
        bogeyman.setType(type);
        bogeyman.addAbility(ability);
        bogeyman.setHauntStartTime(hauntStartTime);
        bogeyman.setHauntEndTime(hauntEndTime);
        bogeyman.setDescription(description);
        bogeyman.setReason(reason);

        bogeymanService.create(bogeyman);

        return bogeyman;
    }

    private Ability createAbility(String name, String description, int cooldown) {
        Ability ability =  new Ability();
        ability.setName(name);
        ability.setCooldown(cooldown);
        ability.setDescription(description);

        abilityService.createAbility(ability);

        return ability;
    }


}
