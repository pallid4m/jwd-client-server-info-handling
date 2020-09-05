package edu.training.info.handling.data.dao;

import edu.training.info.handling.domain.bean.User;

public interface UserDAO {
    byte[] serialize(User user);
    User deserialize(byte[] bytes);
}
