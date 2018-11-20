package cz.muni.fi.pa165.dto;

import java.util.Objects;

public class HouseCreateDto {
    private String name;
    private String address;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HouseCreateDto)) return false;
        HouseCreateDto that = (HouseCreateDto) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress());
    }

    @Override
    public String toString() {
        return "HouseCreateDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
