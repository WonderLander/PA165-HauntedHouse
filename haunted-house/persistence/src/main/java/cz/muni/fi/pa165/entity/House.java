package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class for representing house
 * @author Lukas Sadlek
 */

@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    private Date date;

    @NotNull
    private String history;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    private List<Bogeyman> bogeymen = new ArrayList<>();

    public House() {}

    public House(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public List<Comment> getComments(){ return Collections.unmodifiableList(this.comments);}

    public void addBogeyman(Bogeyman bogeyman) {
        this.bogeymen.add(bogeyman);
    }

    public List<Bogeyman> getBogeymen() {return Collections.unmodifiableList(this.bogeymen);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return Objects.equals(getName(), house.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
