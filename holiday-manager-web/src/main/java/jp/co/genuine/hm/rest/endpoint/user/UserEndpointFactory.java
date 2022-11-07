package jp.co.genuine.hm.rest.endpoint.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserEndpointFactory {
    @Value("${holiday-manager-api.base}")
    private String base;
    @Value("${holiday-manager-api.endpoint.user.get-user-list}")
    private String getUserListEndpoint;
    @Value("${holiday-manager-api.endpoint.user.get-user}")
    private String getUserEndpoint;
    @Value("${holiday-manager-api.endpoint.user.post-user}")
    private String postUserEndpoint;
    @Value("${holiday-manager-api.endpoint.user.put-user}")
    private String putUserEndpoint;
    @Value("${holiday-manager-api.endpoint.user.delete-user}")
    private String deleteUserEndpoint;

    public String createGetUserListEndpoint() {
        return base + getUserListEndpoint;
    }

    public String createGetUserEndpoint(Integer userId) {
        return base + String.format(getUserEndpoint, userId);
    }

    public String createPostUserEndpoint() {
        return base + postUserEndpoint;
    }

    public String createPutUserEndpoint(Integer userId) {
        return base + String.format(putUserEndpoint, userId);
    }

    public String createDeleteUserEndpoint(Integer userId) {
        return base + String.format(deleteUserEndpoint, userId);
    }
}
