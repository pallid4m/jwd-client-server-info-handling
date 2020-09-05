package edu.training.info.handling.controller;

import edu.training.info.handling.domain.bean.Text;
import edu.training.info.handling.service.ServiceFactory;
import edu.training.info.handling.service.exception.ServiceException;

public class TextController {

    public Object dispatch(String request) throws ServiceException {
        switch (request) {
            case "1" :
                return ServiceFactory.getInstance().getTextService().getText();
            case "2" :
                Text text1 = ServiceFactory.getInstance().getTextService().getText();
                return ServiceFactory.getInstance().getTextService().getSentencesSortedByCountWords(text1);
            case "3" :
                Text text2 = ServiceFactory.getInstance().getTextService().getText();
                return ServiceFactory.getInstance().getTextService().getWordsSortedByVowelsRatioToCount(text2);
            case "4" :
                Text text3 = ServiceFactory.getInstance().getTextService().getText();
                return ServiceFactory.getInstance().getTextService().swapFirstLastSentenceWord(text3);
            default :
                return "todo help";
        }
    }
}
