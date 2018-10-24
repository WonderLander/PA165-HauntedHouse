package cz.muni.fi.hauntedhouse;

import cz.muni.fi.hauntedhouse.entity.Ability;
import cz.muni.fi.hauntedhouse.entity.Bogeyman;
import cz.muni.fi.hauntedhouse.entity.BogeymanType;
import cz.muni.fi.hauntedhouse.entity.House;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * @author Martin Wenzl
 */
public class EntityFactory {
    private static int bogeyCounter = 0;
    private static int houseCounter = 0;

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
        bogey.setHountEndTime(end);
        bogey.setReason(reason);
        bogey.setDescription(description);

        return bogey;
    }

    private static House createHouse(String name, String adress, Date date, String history) {
         House house = new House();
         house.setAddress(adress);
         house.setDate(date);
         house.setName(name);
         house.setHistory(history);
         return house;
    }


    /**
     * Creates Bogeyman with random notnull attributes.
     * @return random Bogeyman
     */
    public static Bogeyman createCompulsoryBogeyman() {
         return createCompulsoryBogeyman(createCompulsoryHouse());
    }

    /**
     * Creates Bogeyman with random notnull attributes but leaves user to choose
     * his own house.
     * @param house House...
     * @return random Bogeyman
     */
    public static Bogeyman createCompulsoryBogeyman(House house) {
        bogeyCounter++;
        String name = "BogeyName" + bogeyCounter;
        BogeymanType type = BogeymanType.values()[bogeyCounter % 10];
        return createBogeyman(name, type, null, house,
                null, null, null, null);
    }

    /**
     * Creates House with random notnull attributes.
     * @return random House
     */
    public static House createCompulsoryHouse() {
        houseCounter++;
        String name = "HouseName" + houseCounter;
        String adress = "Adress" + houseCounter;
        Date date = new Date();
        String history = "BriefHistory" + houseCounter;
        return createHouse(name, adress, date, history);
    }
}
