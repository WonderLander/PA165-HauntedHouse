package cz.muni.fi.pa165.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Lukas Sadlek
 */
public class HouseDto {
    private Long id;
    private String name;
    private String address;
    private Date date;
    private String history;
    private List<CommentDto> comments = new ArrayList<>();
    private List<BogeymanDto> bogeymen = new ArrayList<>();

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

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<BogeymanDto> getBogeymen() {
        return bogeymen;
    }

    public void setBogeymen(List<BogeymanDto> bogeymen) {
        this.bogeymen = bogeymen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HouseDto)) return false;
        HouseDto houseDto = (HouseDto) o;
        return Objects.equals(getName(), houseDto.getName()) &&
                Objects.equals(getAddress(), houseDto.getAddress()) &&
                Objects.equals(getDate(), houseDto.getDate()) &&
                Objects.equals(getHistory(), houseDto.getHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getDate(), getHistory());
    }

    @Override
    public String toString() {
        return "HouseDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", history='" + history + '\'' +
                '}';
    }
}
