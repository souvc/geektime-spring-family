package geektime.spring.data.multidatasourcedemo.service;


import geektime.spring.data.multidatasourcedemo.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List selectUsersFromDs();

    List selectUserFromDsGroup();
}
