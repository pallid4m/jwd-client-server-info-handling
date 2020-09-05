package edu.training.info.handling.data.dao.impl;

import edu.training.info.handling.data.dao.UserDAO;
import edu.training.info.handling.domain.bean.User;

import java.io.*;

public class UserDAOImpl implements UserDAO {

    @Override
    public byte[] serialize(User user) {
        byte[] obj = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(user);
            obj = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public User deserialize(byte[] bytes) {
        User user = null;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            user = (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
