package edu.training.info.handling.domain.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Text implements TextComponent {
    private List<TextComponent> components = new ArrayList<>();

    public void addComponent(TextComponent component) {
        components.add(component);
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public String view() {
        String text = components.stream()
                .map(TextComponent::view)
                .collect(Collectors.joining("\n"));
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(components, text.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return "Text{" +
                "components=" + components +
                '}';
    }
}
