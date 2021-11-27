package pl.com.januszex.paka.gui.configuration;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.net.URI;

@Configuration
@ConfigurationProperties("cache")
@Data
public class CacheConfiguration {

    private String redisUri;
    private long expiration;
    private boolean cacheEnabled;

    @Bean
    @SneakyThrows
    public Jedis jedis() {
        return new Jedis(new URI(redisUri));
    }
}
