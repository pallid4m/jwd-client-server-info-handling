package edu.training.info.handling.domain.bean;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sentence implements TextComponent {
    private List<TextComponent> components;

    public Sentence(List<TextComponent> components) {
        this.components = components;
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    public void setComponents(List<TextComponent> components) {
        this.components = components;
    }

    @Override
    public String view() {
        String sentence = components.stream()
                .map(TextComponent::view)
                .collect(Collectors.joining());
        return sentence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(components, sentence.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "components=" + components +
                '}';
    }
}
