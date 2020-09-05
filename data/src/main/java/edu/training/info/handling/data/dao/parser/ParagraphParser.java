package edu.training.info.handling.data.dao.parser;

import edu.training.info.handling.domain.bean.Paragraph;
import edu.training.info.handling.domain.bean.Sentence;
import edu.training.info.handling.domain.bean.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends TextParser {

//    private final String PARAGRAPH_REGEX = "(?s)^(?:\s)*[A-Z].*?[.:!?]+(?:[\\n]|$)";
    private final String PARAGRAPH_REGEX = "(?s)^[A-Z].*?[.:!?]+(?:[\\n]|$)";

    private Pattern pattern = Pattern.compile(PARAGRAPH_REGEX, Pattern.MULTILINE);

    @Override
    public List<? extends TextComponent> parse(String string) {
        List<Paragraph> paragraphs = new ArrayList<>();

        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            do {
                SentenceParser sentenceParser = new SentenceParser();
                List<Sentence> sentences = sentenceParser.parse(matcher.group());
                paragraphs.add(new Paragraph(sentences));
            } while (matcher.find());
        } else {
            if (nextParser != null) {
                return nextParser.parse(string);
            }
        }

        return paragraphs;
    }
}
