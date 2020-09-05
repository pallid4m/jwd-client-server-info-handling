package edu.training.info.handling.data.dao.impl;

import edu.training.info.handling.data.dao.TextDAO;
import edu.training.info.handling.data.dao.exception.DAOException;
import edu.training.info.handling.data.dao.parser.BufferedParser;
import edu.training.info.handling.data.dao.parser.ParserProvider;
import edu.training.info.handling.data.dao.parser.TextParser;
import edu.training.info.handling.domain.bean.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TextDAOImpl implements TextDAO {

    private final String FILE_PATH = "resources/file.txt";

    @Override
    public Text getText() throws DAOException {
        TextParser parser = new BufferedParser();
        parser.setNextParser(ParserProvider.provide());
        try (Reader fileReader = new FileReader(FILE_PATH); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            bufferedReader.lines().forEach(parser::parse);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return parser.getText();
    }
}
