package geektime.spring.data.multidatasourcedemo;

import geektime.spring.data.multidatasourcedemo.entity.User;
import geektime.spring.data.multidatasourcedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MultiDynamicDataSourceDemoApplicationTests {

    private Random random = new Random();

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    /**执行之前，先创建表*/
    @Before
    public void beforeTest() {
        try {
            Connection connection = dataSource.getConnection();
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS  USER (\n" +
                    "  id BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
                    "  name VARCHAR(30) NULL DEFAULT NULL ,\n" +
                    "  age INT(11) NULL DEFAULT NULL ,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ");");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**默认数据源*/
    @Test
    public void addUser() {
        User user = new User();
        user.setName("测试用户" + random.nextInt());
        user.setAge(random.nextInt(100));
        userService.addUser(user);
    }

    //@DS("slave_1")
    @Test
    public void selectUsersFromDs() {
        List<Map<String, Object>> list = userService.selectUsersFromDs();
        log.info("user:{}",list.toString());
    }

    // @DS("slave")
    @Test
    public void selectUserFromDsGroup() {
        userService.selectUserFromDsGroup();
    }

}

