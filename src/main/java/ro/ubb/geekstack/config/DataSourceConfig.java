package ro.ubb.geekstack.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        String ip = "";
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("google.com", 80));
            ip = socket.getLocalAddress().toString();
        } catch (IOException e) {
            ip = "/localhost";
        }
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();


        dataSourceBuilder.url("jdbc:postgresql:/" + ip + "/social_media");
        dataSourceBuilder.username("alex");
        dataSourceBuilder.password("alex982411");
        return dataSourceBuilder.build();
    }
}