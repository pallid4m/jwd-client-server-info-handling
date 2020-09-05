package edu.training.info.handling.service;

import edu.training.info.handling.service.impl.UserServiceImpl;
import edu.training.info.handling.service.impl.TextServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final TextService textService = new TextServiceImpl();

    private ServiceFactory() {}

    public UserService getUserService() {
        return userService;
    }

    public TextService getTextService() {
        return textService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
