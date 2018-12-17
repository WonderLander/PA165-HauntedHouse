package cz.muni.fi.pa165.dto;

import java.sql.Date;
import java.util.Objects;

public class HouseCreateDto {
    private String name;
    private String address;
    private Date date;
    private String history;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HouseCreateDto)) return false;
        HouseCreateDto that = (HouseCreateDto) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getHistory(), that.getHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getDate(), getHistory());
    }

    @Override
    public String toString() {
        return "HouseCreateDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", history='" + history + '\'' +
                '}';
    }
}
