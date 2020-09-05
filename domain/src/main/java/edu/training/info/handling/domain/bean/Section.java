package edu.training.info.handling.domain.bean;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Section implements TextComponent {
    private String prefix;
    private List<Sentence> sentences;

    public Section(String prefix, List<Sentence> sentences) {
        this.prefix = prefix;
        this.sentences = sentences;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String view() {
        String section = sentences.stream()
                .map(TextComponent::view)
                .collect(Collectors.joining());
        section = prefix.concat(section);
        return section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(prefix, section.prefix) &&
                Objects.equals(sentences, section.sentences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, sentences);
    }

    @Override
    public String toString() {
        return "Section{" +
                "prefix='" + prefix + '\'' +
                ", sentences=" + sentences +
                '}';
    }
}
