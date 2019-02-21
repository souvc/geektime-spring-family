package geektime.spring.data.multidatasourcedemo.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import geektime.spring.data.multidatasourcedemo.entity.User;
import geektime.spring.data.multidatasourcedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {
        log.info("addUser的数据源信息：{}",jdbcTemplate.getDataSource().toString());
        jdbcTemplate.update("INSERT INTO user (name,age) VALUES(?, ?)", new Object[]{user.getName(), user.getAge()});
    }

    @DS("slave_1")
    @Override
    public List selectUsersFromDs() {
        log.info("selectUsersFromDs的数据源信息：{}",jdbcTemplate.getDataSource().toString());
        return jdbcTemplate.queryForList("SELECT * FROM user");
    }

    @DS("slave")
    @Override
    public List selectUserFromDsGroup() {
        log.info("selectUserFromDsGroup的数据源信息：{}",jdbcTemplate.getDataSource().toString());
        return jdbcTemplate.queryForList("SELECT * FROM user");
    }
}
