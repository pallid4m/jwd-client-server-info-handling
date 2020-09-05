package edu.training.info.handling.service;

import edu.training.info.handling.domain.bean.Sentence;
import edu.training.info.handling.domain.bean.Text;
import edu.training.info.handling.domain.bean.Word;
import edu.training.info.handling.service.exception.ServiceException;

import java.util.List;

public interface TextService {
    Text getText() throws ServiceException;
//    5
    Text swapFirstLastSentenceWord(Text text) throws ServiceException;
//    2
    List<Sentence> getSentencesSortedByCountWords(Text text) throws ServiceException;
//    7
    List<Word> getWordsSortedByVowelsRatioToCount(Text text) throws ServiceException;
}
