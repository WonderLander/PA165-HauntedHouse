package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.services.AbilityService;
import cz.muni.fi.pa165.service.services.BogeymanService;
import cz.muni.fi.pa165.service.services.CommentService;
import cz.muni.fi.pa165.service.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
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
    @Autowired
    private UserFacade userFacade;

//    @Inject
//    public SampleDataFacadeImpl(AbilityService abilityService, BogeymanService bogeymanService, CommentService commentService, HouseService houseService) {
//        this.abilityService = abilityService;
//        this.bogeymanService = bogeymanService;
//        this.commentService = commentService;
//        this.houseService = houseService;
//    }

    @Override
    public void init() {
        House house = createHouse("House 1","Address 1",Date.valueOf(LocalDate.now()),"text 1",null,null);
        Comment comment = createComment("Author 1",Date.valueOf(LocalDate.now()),"Text 1",null);

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
        /*Bogeyman peterGhost = createBogeyman("Peter's ghost", BogeymanType.GHOST, peterAbilities,
                Time.valueOf(LocalTime.of(15, 15, 15)),
                Time.valueOf(LocalTime.of(16, 15, 15)),
                "Peter died and did not want to leave his house full of memories",
                "Sentimentality");
        Bogeyman oldWitch = createBogeyman("Old witch", BogeymanType.WITCH, witchAbilities,
                Time.valueOf(LocalTime.of(20, 15, 0)),
                Time.valueOf(LocalTime.of(6, 0, 0)),
                "For her, magic is fun", "Fun");
        Bogeyman zombie = createBogeyman("Zombie", BogeymanType.ZOMBIE, new HashSet<>(),
                Time.valueOf(LocalTime.of(20, 15, 16)),
                Time.valueOf(LocalTime.of(0, 15, 47)),
                "Haunts after sunset", "Revenge");
        House treeHouse = createHouse("Tree house", "On tha tree",
                Date.valueOf(LocalDate.of(2011, 5, 6)),
                "This house was built on atree for old witch.", null, oldWitch);
        House familyHouse = createHouse("Family house", "Green Garden, Salisbury 56",
                Date.valueOf(LocalDate.of(2018, 1, 1)),
                "Owner of this house did not want to leave ...", null, peterGhost);
        House industryHall = createHouse("Industry hall", "By the river, Kopfburger 85",
                Date.valueOf(LocalDate.of(2017, 5, 8)), "An explosion appeared in 2017.", null,
                zombie);
        peterGhost.setHouse(familyHouse);
        oldWitch.setHouse(treeHouse);
        zombie.setHouse(industryHall);*/
        House house1 = createHouse("Residence at the Corner of the Damned", "Corner of the Damned 666", Date.valueOf(LocalDate.now()),
                "It is said that this house was once a residence of one of the most dreadful sabbath", null, null);

        House house2 = createHouse("Old Shack", "Forest road 123", Date.valueOf(LocalDate.now()),
                "Once a place where werewolves celebrated full moon", null, null);

        House house3 = createHouse("Ravenholm's Asylum", "Ravenholm 321", Date.valueOf(LocalDate.now()),
                "When Ravenholm was overrun by zombies the asylum was last place of sanity", null, null);

        Comment comment1 = createComment("Vila de Cruel", Date.valueOf(LocalDate.now()), "It was great. Almost shat my pants", house1);
        Comment comment11 = createComment("Pierre-Simon Laplace", Date.valueOf(LocalDate.now()), "I almost forgot my theorems. 10/10", house1);
        Comment comment2 = createComment("Sarah Luis Kerrigan", Date.valueOf(LocalDate.now()), "Would not dare to enter without my swarm again.", house2);

        Ability ability2 = createAbility("Salamander eyes broth", "This powerful broth is mainstay of witches cuisine.", 5);
        Ability ability3 = createAbility("Undead", "Nothing can stop a zombie. Not even cannon fire.", 1);
        Ability ability1 = createAbility("Lich Immortality", "Life of a lich is bound to powerful artifact, where the power of lich resides.", 1);

        Bogeyman bogeyman1 = createBogeyman("Lich", house1, BogeymanType.LICH, ability1,
                Time.valueOf(LocalTime.of(8, 15, 0)),
                Time.valueOf(LocalTime.of(22, 0)),
                "Life of a lich is bound to powerful artifact, where the power of lich resides. Destroy the artifact and you " +
                        "shall destroy the lich", "Unfulfilled dreams of becoming a IT specialist drove Mark to bound his life with his old Intel 8080.");

        Bogeyman bogeyman12 = createBogeyman("Witch", house1, BogeymanType.WITCH, ability2,
                Time.valueOf(LocalTime.of(0, 12, 45)),
                Time.valueOf(LocalTime.of(23, 59)),
                "For a witch is a burning at stake only a pleasant entertainment.", "Who would resist the temptation of a broom flight");

        Bogeyman bogeyman3 = createBogeyman("Zombie", house3, BogeymanType.ZOMBIE, ability3,
                Time.valueOf(LocalTime.of(20, 0)),
                Time.valueOf(LocalTime.of(4, 0)),
                "Embodiment ceaseless hunger.", "Brains are brains. For zombies it does not matter whose brain it is.");


        UserDto adminUserDto = new UserDto();
        adminUserDto.setAdmin(true);
        adminUserDto.setEmail("admin@hauntedhouses.com");
        userFacade.registerUser(adminUserDto, "admin");

        UserDto userDto = new UserDto();
        userDto.setAdmin(false);
        userDto.setEmail("user@gmail.com");
        userFacade.registerUser(userDto, "password");
    }

    private Comment createComment(String author, Date date, String text, House house){
        Comment comment = new Comment(author,date,text);
        comment.setHouse(house);

        commentService.create(comment);

        return comment;
    }

    private House createHouse(String name, String address, Date date,
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
                                    Time hauntStartTime, Time hauntEndTime, String description,
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

