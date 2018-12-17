package cz.muni.fi.pa165.dto;

import java.sql.Date;
import java.util.Objects;

/**
 * @author Ondrej Stursa
 */
public class CommentCreateDto
{
    private String author;

    private Date date;

    private String text;

    private HouseDto house;

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

    public HouseDto getHouse() {
        return house;
    }

    public void setHouse(HouseDto house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentCreateDto)) return false;
        CommentCreateDto that = (CommentCreateDto) o;
        return Objects.equals(getAuthor(), that.getAuthor()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getDate(), getText());
    }
}
