package edu.training.info.handling.data.dao.parser;

import edu.training.info.handling.domain.bean.TextComponent;
import edu.training.info.handling.domain.bean.Word;
import edu.training.info.handling.domain.bean.Digit;
import edu.training.info.handling.domain.bean.Mark;
import edu.training.info.handling.domain.bean.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartSentenceParser extends TextParser {

    private final String WORD_MARK_REGEX = "(?<word>[A-za-z]+)|(?<digit>\\d+)|(?<mark>[^\\dA-Za-z\\s])|(?<space>\\s+)";

    private Pattern pattern = Pattern.compile(WORD_MARK_REGEX);

    @Override
    public List<TextComponent> parse(String string) {
        List<TextComponent> components = new ArrayList<>();

        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            do {
                String word = matcher.group("word");
                String digit = matcher.group("digit");
                String mark = matcher.group("mark");
                String space = matcher.group("space");
                if (word != null) {
                    components.add(new Word(word));
                }
                if (digit != null) {
                    components.add(new Digit(digit));
                }
                if (mark != null) {
                    components.add(new Mark(mark));
                }
                if (space != null) {
                    components.add(new Space(space));
                }
            } while (matcher.find());
        }

        return components;
    }
}
