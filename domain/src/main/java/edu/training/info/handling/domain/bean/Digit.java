package edu.training.info.handling.domain.bean;

import java.util.Objects;

public class Digit implements TextComponent {
    private String digit;

    public Digit(String digit) {
        this.digit = digit;
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit;
    }

    @Override
    public String view() {
        return digit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digit digit1 = (Digit) o;
        return Objects.equals(digit, digit1.digit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digit);
    }

    @Override
    public String toString() {
        return "Digit{" +
                "digit='" + digit + '\'' +
                '}';
    }
}
