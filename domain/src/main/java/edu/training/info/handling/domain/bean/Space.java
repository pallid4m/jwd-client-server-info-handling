package edu.training.info.handling.domain.bean;

import java.util.Objects;

public class Space implements TextComponent {
    private String space;

    public Space(String space) {
        this.space = space;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    @Override
    public String view() {
        return space;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space1 = (Space) o;
        return Objects.equals(space, space1.space);
    }

    @Override
    public int hashCode() {
        return Objects.hash(space);
    }

    @Override
    public String toString() {
        return "Space{" +
                "space='" + space + '\'' +
                '}';
    }
}
