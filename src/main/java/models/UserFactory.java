package models;

import config.EnvConfig;
import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PUBLIC;

public class UserFactory {
    @Getter(PUBLIC)
    @Setter(PUBLIC)
    private User user;

    public UserFactory() {
        this.user = User.builder()
            .userName(new EnvConfig().getUserName())
            .password(new EnvConfig().getPassword())
            .build();
    }
}
