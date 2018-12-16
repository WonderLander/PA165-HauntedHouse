package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.service.services.AbilityService;
import cz.muni.fi.pa165.service.services.BogeymanService;
import cz.muni.fi.pa165.service.services.CommentService;
import cz.muni.fi.pa165.service.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

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
        House house = createHouse("House 1","Address 1",LocalDate.now(),"text 1",null,null);
        Comment comment = createComment("Author 1",LocalDate.now(),"Text 1",null);

        //for bogeymen's .jsp
        Ability fire = createAbility("Fire", "Creature throws fire", 1000);
        Ability ice = createAbility("Ice", "Creature throws ice", 1000);
        Ability invisibility = createAbility("Invisibility", "Bogeyman is not visible", 10000);
        Ability magic = createAbility("Magic", "Casts spells", 10);
        Set<Ability> peterAbilities = new HashSet<>();
        peterAbilities.add(invisibility);
        Set<Ability> witchAbilities = new HashSet<>();
        witchAbilities.add(fire);
        witchAbilities.add(ice);
        witchAbilities.add(magic);
        Bogeyman peterGhost = createBogeyman("Peter's ghost", BogeymanType.GHOST, peterAbilities,
                LocalTime.of(15, 15, 15), LocalTime.of(16, 15, 15),
                "Peter died and did not want to leave his house full of memories",
                "Sentimentality");
        Bogeyman oldWitch = createBogeyman("Old witch", BogeymanType.WITCH, witchAbilities,
                LocalTime.of(20, 15, 0), LocalTime.of(6, 0, 0),
                "For her, magic is fun", "Fun");
        Bogeyman zombie = createBogeyman("Zombie", BogeymanType.ZOMBIE, new HashSet<>(),
                LocalTime.of(20, 15, 16), LocalTime.of(0, 15, 47),
                "Haunts after sunset", "Revenge");
        House treeHouse = createHouse("Tree house", "On tha tree", LocalDate.of(2011, 5, 6),
                "This house was built on atree for old witch.", null, oldWitch);
        House familyHouse = createHouse("Family house", "Green Garden, Salisbury 56", LocalDate.of(2018, 1, 1),
                "Owner of this house did not want to leave ...", null, peterGhost);
        House industryHall = createHouse("Industry hall", "By the river, Kopfburger 85",
                LocalDate.of(2017, 5, 8), "An explosion appeared in 2017.", null,
                zombie);
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

    private Bogeyman createBogeyman(String name, BogeymanType type, Set<Ability> abilities,
                                    LocalTime hauntStartTime, LocalTime hauntEndTime, String description,
                                    String reason) {
        Bogeyman bogeyman = new Bogeyman();
        bogeyman.setName(name);
        //bogeyman.setHouse(house);
        bogeyman.setDescription(description);
        bogeyman.setHauntEndTime(hauntEndTime);
        bogeyman.setHauntStartTime(hauntStartTime);
        bogeyman.setType(type);
        bogeyman.setReason(reason);
        for (Ability ability: abilities) {
            bogeyman.addAbility(ability);
        }
        bogeymanService.create(bogeyman);
        return bogeyman;
    }

    private Ability createAbility(String name, String description, int cooldown) {
        Ability ability = new Ability();
        ability.setName(name);
        ability.setDescription(description);
        ability.setCooldown(cooldown);
        abilityService.createAbility(ability);
        return ability;
    }
}
