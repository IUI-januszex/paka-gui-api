package pl.com.januszex.paka.gui.user.api.service;

public interface CurrentUserServicePort {
    boolean isAnonymous();

    String getCurrentJwt();

    Long getId();

    String getUserName();
}
