package cz.muni.fi.pa165.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CommentCreateDto
{
    private String author;

    private LocalDate date;

    private String text;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
