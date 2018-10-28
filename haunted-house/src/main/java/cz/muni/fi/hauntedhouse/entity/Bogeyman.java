package cz.muni.fi.hauntedhouse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ondrej Krcma 451363
 */
@Entity
public class Bogeyman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Enumerated
    private BogeymanType type;

    @ManyToMany
    private Set<Ability> abilities = new HashSet<>();

    @NotNull
    @ManyToOne
    private House house;

    private LocalTime hauntStartTime;

    private LocalTime hauntEndTime;

    @Column(length = 2000)
    private String description;

    @Column(length = 500)
    private String reason;

    public Bogeyman(){}

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

    public Set<Ability> getAbilities() {
        return Collections.unmodifiableSet(abilities);
    }

    public void addAbility(Ability ability) {
        abilities.add(ability);
        ability.addBogeyman(this);
    }

    public void removeAbility(Ability ability) {
        abilities.remove(ability);
        ability.removeBogeyman(this);
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public LocalTime getHauntStartTime() {
        return hauntStartTime;
    }

    public void setHauntStartTime(LocalTime hauntStartTime) {
        this.hauntStartTime = hauntStartTime;
    }

    public LocalTime getHauntEndTime() {
        return hauntEndTime;
    }

    public void setHauntEndTime(LocalTime hauntEndTime) {
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
        if (!(o instanceof Bogeyman)) return false;
        Bogeyman bogeyman = (Bogeyman) o;
        return Objects.equals(getName(), bogeyman.getName());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(getName());
    }
}
