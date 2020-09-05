package edu.training.info.handling.data.dao;

import edu.training.info.handling.data.dao.exception.DAOException;
import edu.training.info.handling.domain.bean.Text;

public interface TextDAO {
    Text getText() throws DAOException;
}
