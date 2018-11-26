package cz.muni.fi.pa165.factory;

import cz.muni.fi.pa165.dto.AbilityDto;
import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /*public static Set<AbilityDto> createAbilityDtosFromEntityForOneBogeyman(Set<Ability> abilities,
                                                                            BogeymanDto bogeymanDto) {
        Set<AbilityDto> dtos = new HashSet<>();
        for (Ability ability : abilities) {
            AbilityDto dto = new AbilityDto();
            List<BogeymanDto> bogeymanDtoList = new ArrayList<>();
            bogeymanDtoList.add(bogeymanDto);
            dto.setBogeymen(bogeymanDtoList);
            dto.setCooldown(ability.getCooldown());
            dto.setDescription(ability.getDescription());
            dto.setName(ability.getName());
            dtos.add(dto);
        }
        return dtos;
    }

    public static HouseDto createHouseDtoFromEntityForOneBogeyman(House house, BogeymanDto bogeymanDto) {
        HouseDto houseDto = new HouseDto();
        List<BogeymanDto> bogeymanDtoList = new ArrayList<>();
        bogeymanDtoList.add(bogeymanDto);
        houseDto.setBogeymen(bogeymanDtoList);
        houseDto.setDate(house.getDate());
        houseDto.setHistory(house.getHistory());
        houseDto.setAddress(house.getAddress());
        houseDto.setName(house.getName());

        return houseDto;
    }

    public static BogeymanCreateDto createBogeymanCreateDtoFromEntity(Bogeyman bogeyman) {
        BogeymanCreateDto bogeymanDto = new BogeymanCreateDto();
        if (bogeyman.getAbilities() != null) {
            bogeymanDto.setAbilities(createAbilityDtosFromEntityForOneBogeyman(
                    bogeyman.getAbilities(), bogeymanDto));
        }
        bogeymanDto.setDescription(bogeyman.getDescription());
        bogeymanDto.setHauntEndTime(bogeyman.getHauntEndTime());
        bogeymanDto.setHauntStartTime(bogeyman.getHauntStartTime());
        bogeymanDto.setHouse(createHouseDtoFromEntityForOneBogeyman(bogeyman.getHouse(), bogeymanDto));
        bogeymanDto.setReason(bogeyman.getReason());
        bogeymanDto.setName(bogeyman.getName());
        bogeymanDto.setType(cz.muni.fi.pa165.enums.BogeymanType.valueOf(bogeyman.getType().name()));

        return bogeymanDto;
    }*/

}
