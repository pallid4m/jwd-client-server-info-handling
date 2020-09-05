package edu.training.info.handling.service;

import edu.training.info.handling.domain.bean.User;

public interface UserService {
    byte[] serialize(User user);
    User deserialize(byte[] bytes);
}
