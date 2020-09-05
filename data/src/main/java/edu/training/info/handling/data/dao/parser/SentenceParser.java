package edu.training.info.handling.data.dao.parser;

import edu.training.info.handling.domain.bean.Sentence;
import edu.training.info.handling.domain.bean.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends TextParser {

    private final String SENTENCE_REGEX = "([A-Z][^\\.!?:]*[\\.!?:])";

    private Pattern pattern = Pattern.compile(SENTENCE_REGEX);

    @Override
    public List<Sentence> parse(String string) {
        List<Sentence> sentences = new ArrayList<>();

        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            do {
                PartSentenceParser partSentenceParser = new PartSentenceParser();
                String sentence = matcher.group().replace('\n', ' ');
                List<TextComponent> partsOfSentence = partSentenceParser.parse(sentence);
                sentences.add(new Sentence(partsOfSentence));
            } while (matcher.find());
        }

        return sentences;
    }
}
