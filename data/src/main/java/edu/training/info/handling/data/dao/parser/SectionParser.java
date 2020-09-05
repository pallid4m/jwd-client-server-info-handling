package edu.training.info.handling.data.dao.parser;

import edu.training.info.handling.domain.bean.Section;
import edu.training.info.handling.domain.bean.Sentence;
import edu.training.info.handling.domain.bean.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SectionParser extends TextParser {

    private final String SECTION_REGEX = "^(?<prefix>(?:[0-9]\\.?)+|(?:[-]))(?:\\s*)(?<sentence>[A-Z].*)$";

    private Pattern pattern = Pattern.compile(SECTION_REGEX);

    @Override
    public List<? extends TextComponent> parse(String string) {
        List<Section> sections = new ArrayList<>();

        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            do {
                String prefix = matcher.group("prefix");
                String sentence = matcher.group("sentence");
                PartSentenceParser partSentenceParser = new PartSentenceParser();
                List<TextComponent> partsOfSentence = partSentenceParser.parse(sentence);
                sections.add(new Section(prefix, List.of(new Sentence(partsOfSentence))));
            } while (matcher.find());
        } else {
            if (nextParser != null) {
                return nextParser.parse(string);
            }
        }

        return sections;
    }
}
