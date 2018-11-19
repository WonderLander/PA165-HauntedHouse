package dto;

import java.util.List;
import java.util.Objects;

/**
 * @autor Martin Wenzl
 */
public class AbilityDto {
    private Long id;
    private String name;
    private String description;
    private int cooldown;
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
        if (!(o instanceof AbilityDto)) return false;
        AbilityDto that = (AbilityDto) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
