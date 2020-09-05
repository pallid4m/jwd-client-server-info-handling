package edu.training.info.handling.service.impl;

import edu.training.info.handling.data.dao.DAOFactory;
import edu.training.info.handling.domain.bean.User;
import edu.training.info.handling.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public byte[] serialize(User user) {
        return DAOFactory.getInstance().getUserDAO().serialize(user);
    }

    @Override
    public User deserialize(byte[] bytes) {
        return DAOFactory.getInstance().getUserDAO().deserialize(bytes);
    }
}
