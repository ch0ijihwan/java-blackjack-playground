package model.participant.vo;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(String value) {
        validateNameIsNotBlank(value);
        this.value = value;
    }

    private void validateNameIsNotBlank(String value) {
        if (value.length() == 0) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
