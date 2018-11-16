package entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author Martin Wenzl
 */


@Entity
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column
    private String description;

    @Column
    private int cooldown;

    @Column
    @ManyToMany(mappedBy = "abilities")
    private List<Bogeyman> bogeymen = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public List<Bogeyman> getBogeymen() {
        return bogeymen;
    }

    public void setBogeymen(List<Bogeyman> bogeymen) {
        this.bogeymen = bogeymen;
    }

    public void addBogeyman (Bogeyman bogeyman) {
        this.bogeymen.add(bogeyman);
    }
    public void removeBogeyman (Bogeyman bogeyman) {
        bogeymen.remove(bogeyman);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ability)) return false;
        Ability ability = (Ability) o;
        return Objects.equals(getName(), ability.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
