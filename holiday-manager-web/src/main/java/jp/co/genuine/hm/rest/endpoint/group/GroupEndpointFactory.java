package jp.co.genuine.hm.rest.endpoint.group;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GroupEndpointFactory {
    @Value("${holiday-manager-api.base}")
    private String base;
    @Value("${holiday-manager-api.endpoint.group.get-group-list}")
    private String getGroupListEndpoint;
    @Value("${holiday-manager-api.endpoint.group.get-group}")
    private String getGroupEndpoint;
    @Value("${holiday-manager-api.endpoint.group.post-group}")
    private String postGroupEndpoint;
    @Value("${holiday-manager-api.endpoint.group.put-group}")
    private String putGroupEndpoint;
    @Value("${holiday-manager-api.endpoint.group.delete-group}")
    private String deleteGroupEndpoint;
    @Value("${holiday-manager-api.endpoint.group.post-group-members}")
    private String postGroupMembersEndpoint;

    public String createGetGroupListEndpoint() {
        return base + getGroupListEndpoint;
    }

    public String createGetGroupEndpoint(Integer GroupId) {
        return base + String.format(getGroupEndpoint, GroupId);
    }

    public String createPostGroupEndpoint() {
        return base + postGroupEndpoint;
    }

    public String createPutGroupEndpoint(Integer GroupId) {
        return base + String.format(putGroupEndpoint, GroupId);
    }

    public String createDeleteGroupEndpoint(Integer GroupId) {
        return base + String.format(deleteGroupEndpoint, GroupId);
    }

    public String createPostGroupMembersEndpoint() {
        return base + postGroupMembersEndpoint;
    }
}
