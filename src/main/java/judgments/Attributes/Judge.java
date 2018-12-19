package judgments.Attributes;

import java.util.List;
import java.util.Objects;

public class Judge {

    public void setName(String name) {
        this.name = name;
    }

    String name;
    String function;

    public void setSpecialRoles(List<SpecialRole> specialRoles) {
        this.specialRoles = specialRoles;
    }

    List<SpecialRole> specialRoles;

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    public List<SpecialRole> getSpecialRoles() {
        return specialRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Judge judge = (Judge) o;
        return Objects.equals(name, judge.name) &&
                Objects.equals(function, judge.function) &&
                Objects.equals(specialRoles, judge.specialRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, function, specialRoles);
    }

    public String toString(){
        return getName();
    }
}
