package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Ondřej Štursa
 */
@Entity
public class Comment
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String author;

    @NotNull
    private Date date;

    @Column(length = 2000)
    private String text;

    @ManyToOne
    private House house;

    public Comment(){}

    public Comment(@NotNull String author, @NotNull Date date, String text) {
        this.author = author;
        this.date = date;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getAuthor(), comment.getAuthor()) &&
                Objects.equals(getDate(), comment.getDate()) &&
                Objects.equals(getText(), comment.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getDate(), getText());
    }
}
