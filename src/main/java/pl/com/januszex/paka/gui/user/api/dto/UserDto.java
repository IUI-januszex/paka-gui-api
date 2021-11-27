package pl.com.januszex.paka.gui.user.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(timeToLive = 300)
public class UserDto {
    @Id
    private String id;
    private String userName;
    private String email;
    @JsonProperty(value = "isActive")
    private boolean active;
    private int userType;
}
