package geektime.spring.data.multidatasourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MultiDynamicDataSourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDynamicDataSourceDemoApplication.class, args);
    }
}

