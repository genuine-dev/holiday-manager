package jp.co.genuine.hm.rest.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupResponse {
    private Integer groupId;
    private String groupName;
    private List<UserResponse> managerList;
    private List<UserResponse> memberList;

    public GroupResponse() {
        groupName = "";
        managerList = new ArrayList<>();
        memberList = new ArrayList<>();
    }

    public GroupResponse(Integer groupId, String groupName, List<UserResponse> managerList, List<UserResponse> memberList) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.managerList = managerList;
        this.memberList = memberList;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<UserResponse> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<UserResponse> managerList) {
        this.managerList = managerList;
    }

    public List<UserResponse> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<UserResponse> memberList) {
        this.memberList = memberList;
    }
}
