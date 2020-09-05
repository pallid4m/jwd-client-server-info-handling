package edu.training.info.handling.service.impl;

import edu.training.info.handling.data.dao.DAOFactory;
import edu.training.info.handling.data.dao.TextDAO;
import edu.training.info.handling.data.dao.exception.DAOException;
import edu.training.info.handling.domain.bean.*;
import edu.training.info.handling.service.TextService;
import edu.training.info.handling.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class TextServiceImpl implements TextService {

    @Override
    public Text getText() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        TextDAO textDAO = factory.getTextDAO();

        Text text;
        try {
            text = textDAO.getText();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return text;
    }

    @Override
    public Text swapFirstLastSentenceWord(Text text) throws ServiceException {
        for (TextComponent component : text.getComponents()) {
            if (component.getClass() == Section.class) {
                Section section = (Section) component;
                swapFirstLastSentenceWord(section.getSentences());
            } else if (component.getClass() == Paragraph.class) {
                Paragraph paragraph = (Paragraph) component;
                swapFirstLastSentenceWord(paragraph.getSentences());
            }
        }
        return text;
    }

    @Override
    public List<Sentence> getSentencesSortedByCountWords(Text text) throws ServiceException {
        List<Sentence> sentences = getAllSentences(text);
        sentences.sort((o1, o2) -> {
            List<Word> words1 = new ArrayList<>();
            List<Word> words2 = new ArrayList<>();
            for (TextComponent component : o1.getComponents()) {
                if (component.getClass() == Word.class) {
                    words1.add((Word) component);
                }
            }
            for (TextComponent component : o2.getComponents()) {
                if (component.getClass() == Word.class) {
                    words2.add((Word) component);
                }
            }
            return words1.size() - words2.size();
        });
        return sentences;
    }

    @Override
    public List<Word> getWordsSortedByVowelsRatioToCount(Text text) throws ServiceException {
        List<Word> words = getAllWords(text);
        words.sort((o1, o2) -> {
            double ratio1 = ratioVowelsToCount(o1.getWord());
            double ratio2 = ratioVowelsToCount(o2.getWord());
            if (ratio1 == ratio2) {
                return 0;
            }
            return ratio1 > ratio2 ? 1 : -1;
        });
        return words;
    }

    private double ratioVowelsToCount(String string) {
        double vowelsCount = 0;
        for (char ch : string.toLowerCase().toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch =='u' || ch == 'y') {
                vowelsCount++;
            }
        }
        return string.length() == 0 ? 0 : vowelsCount / string.length();
    }

    private List<Sentence> getAllSentences(Text text) {
        List<Sentence> sentences = new ArrayList<>();
        for (TextComponent component : text.getComponents()) {
            if (component.getClass() == Section.class) {
                Section section = (Section) component;
                sentences.addAll(section.getSentences());
            } else if (component.getClass() == Paragraph.class) {
                Paragraph paragraph = (Paragraph) component;
                sentences.addAll(paragraph.getSentences());
            }
        }
        return sentences;
    }

    private List<Word> getAllWords(Text text) {
        List<Word> words = new ArrayList<>();
        List<Sentence> sentences = getAllSentences(text);
        for (Sentence sentence : sentences) {
            for (TextComponent component : sentence.getComponents()) {
                if (component.getClass() == Word.class) {
                    Word word = (Word) component;
                    words.add(word);
                }
            }
        }
        return words;
    }

    private void swapFirstLastSentenceWord(List<Sentence> sentences) {
        for (Sentence sentence : sentences) {
            Word firstWord = getFirstWord(sentence);
            Word lastWord = getLastWord(sentence);

            int firstWordIndex = sentence.getComponents().indexOf(firstWord);
            int lastWordIndex = sentence.getComponents().indexOf(lastWord);

            sentence.getComponents().set(firstWordIndex, lastWord);
            sentence.getComponents().set(lastWordIndex, firstWord);
        }
    }

    private Word getFirstWord(Sentence sentence) {
        for (TextComponent component : sentence.getComponents()) {
            if (component.getClass() == Word.class) {
                return (Word) component;
            }
        }
        return null;
    }

    private Word getLastWord(Sentence sentence) {
        Word word = null;
        for (TextComponent component : sentence.getComponents()) {
            if (component.getClass() == Word.class) {
                word = (Word) component;
            }
        }
        return word;
    }
}
