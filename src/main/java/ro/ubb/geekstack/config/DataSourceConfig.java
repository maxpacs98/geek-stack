package ro.ubb.geekstack.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        String ip = System.getenv("OUTSIDE_IP");
        dataSourceBuilder.url("jdbc:postgresql://" + ip + "/social_media");
        dataSourceBuilder.username("alex");
        dataSourceBuilder.password("alex982411");

        return dataSourceBuilder.build();
    }
}