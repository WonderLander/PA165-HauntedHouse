package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @autor Martin Wenzl
 */
public class AbilityCreateDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 500)
    private String description;
    private int cooldown;
    private List<BogeymanDto> bogeymen = new ArrayList<>();

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

    public List<BogeymanDto> getBogeymen() {
        return bogeymen;
    }

    public void setBogeymen(List<BogeymanDto> bogeymen) {
        this.bogeymen = bogeymen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbilityCreateDto)) return false;
        AbilityCreateDto that = (AbilityCreateDto) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
