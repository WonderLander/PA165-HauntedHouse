package cz.muni.fi.hauntedhouse;

import cz.muni.fi.hauntedhouse.entity.Ability;
import cz.muni.fi.hauntedhouse.entity.Bogeyman;
import cz.muni.fi.hauntedhouse.entity.BogeymanType;
import cz.muni.fi.hauntedhouse.entity.House;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Wenzl
 */
public class EntityFactory {
    private static int bogeyCounter = 0;
    private static int houseCounter = 0;
    private static int abilityCounter = 0;

     private static Bogeyman createBogeyman(String name, BogeymanType type,
                                            List<Ability> abilities, House house,
                                            LocalTime start, LocalTime end,
                                            String description, String reason) {
        Bogeyman bogey = new Bogeyman();
        bogey.setName(name);
        bogey.setType(type);
        bogey.setHouse(house);
        if (abilities != null) {
            for(Ability a : abilities) {
                bogey.addAbility(a);
            }
        }
        bogey.setHauntStartTime(start);
        bogey.setHauntEndTime(end);
        bogey.setReason(reason);
        bogey.setDescription(description);

        return bogey;
    }

    private static House createHouse(String name, String adress, LocalDate date, String history) {
         House house = new House();
         house.setAddress(adress);
         house.setDate(date);
         house.setName(name);
         house.setHistory(history);
         return house;
    }


    /**
     * Creates Bogeyman with random notnull attributes.
     * @return Bogeyman
     */
    public static Bogeyman createCompulsoryBogeyman() {
         return createCompulsoryBogeyman(createCompulsoryHouse(), createCompulsoryAbility());
    }

    /**
     * Creates Bogeyman with random notnull attributes but leaves user to choose
     * his own house.
     * @param house House...
     * @return Bogeyman
     */
    public static Bogeyman createCompulsoryBogeyman(House house, Ability ability) {
        bogeyCounter++;
        String name = "BogeyName" + bogeyCounter;
        BogeymanType type = BogeymanType.values()[bogeyCounter % 10];
        List<Ability> abilities = new ArrayList<>();
        abilities.add(ability);
        return createBogeyman(name, type, abilities, house,
                null, null, null, null);
    }

    /**
     * Creates House with random notnull attributes.
     * @return House
     */
    public static House createCompulsoryHouse() {
        houseCounter++;
        String name = "HouseName" + houseCounter;
        String adress = "Adress" + houseCounter;
        LocalDate date = LocalDate.of(2018 - houseCounter, 10, 23);
        String history = "BriefHistory" + houseCounter;
        return createHouse(name, adress, date, history);
    }

    /**
     * Creates Ability with notnull attributes without bogeyman
     * @return Ability
     */
    public static Ability createCompulsoryAbility() {
        abilityCounter++;
        String name = "AbilityName" + abilityCounter;
        String desc = "Description for ability" + abilityCounter + ".";
        int cooldown = abilityCounter;

        Ability ability = new Ability();
        ability.setName(name);
        ability.setDescription(desc);
        ability.setCooldown(cooldown);

        return ability;
    }
}
