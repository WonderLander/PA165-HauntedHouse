package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.BogeymanType;

import java.sql.Time;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ondrej Krcma 451363
 */
public class BogeymanDto {

    private long id;
    private String name;
    private BogeymanType type;
    private Set<AbilityDto> abilities = new HashSet<>();
    private HouseDto house;
    private Time hauntStartTime;
    private Time hauntEndTime;
    private String description;
    private String reason;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BogeymanType getType() {
        return type;
    }

    public void setType(BogeymanType type) {
        this.type = type;
    }

    public Set<AbilityDto> getAbilities() {
        //return Collections.unmodifiableSet(abilities);
        return abilities;
    }

    public void setAbilities(Set<AbilityDto> abilities) {
        this.abilities = abilities;
    }

    public void addAbility(AbilityDto ability) {
        this.abilities.add(ability);
    }

    public void removeAbility(AbilityDto ability) {
        this.abilities.remove(ability);
    }

    public HouseDto getHouse() {
        return house;
    }

    public void setHouse(HouseDto house) {
        this.house = house;
    }

    public Time getHauntStartTime() {
        return hauntStartTime;
    }

    public void setHauntStartTime(Time hauntStartTime) {
        this.hauntStartTime = hauntStartTime;
    }

    public Time getHauntEndTime() {
        return hauntEndTime;
    }

    public void setHauntEndTime(Time hauntEndTime) {
        this.hauntEndTime = hauntEndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BogeymanDto)) return false;
        BogeymanDto that = (BogeymanDto) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return 41 * Objects.hash(getName());
    }
}
