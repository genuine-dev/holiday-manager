package holiday.manager.service.group;

import holiday.manager.domain.group.*;
import holiday.manager.domain.user.UserId;
import holiday.manager.rest.request.group.*;
import holiday.manager.rest.response.group.GroupResponse;
import holiday.manager.rest.response.group.GroupResponseConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupResponseConverter groupResponseConverter;

    public GroupService(GroupRepository groupRepository, GroupResponseConverter groupResponseConverter) {
        this.groupRepository = groupRepository;
        this.groupResponseConverter = groupResponseConverter;
    }

    public Group findGroup(GroupId groupId) {
        return groupRepository.findGroup(groupId);
    }

    public void postGroup(PostGroupRequest request) {
        GroupId groupId = groupRepository.nextGroupId();
        GroupName groupName = new GroupName(request.getGroupName());

        groupRepository.insertGroup(groupId, groupName);
    }

    public void putGroup(Integer groupId, PutGroupRequest request) {
        GroupName groupName = new GroupName(request.getGroupName());

        groupRepository.updateGroup(new GroupId(groupId), groupName);
    }

    public void postGroupMembers(PostGroupMembersRequest request) {
        GroupId groupId = new GroupId(request.getGroupId());

        groupRepository.deleteMembers(groupId);
        groupRepository.deleteManagers(groupId);
        for (GroupMember groupMember : request.getGroupMemberList()) {
            if (groupMember.isManager())
                groupRepository.insertManager(new UserId(groupMember.getUserId()), groupId);
            if (groupMember.isMember())
                groupRepository.insertMember(new UserId(groupMember.getUserId()), groupId);
        }
    }

    public void postGroupManager(PostGroupManagerRequest request) {
        UserId userId = new UserId(request.getUserId());
        GroupId groupId = new GroupId(request.getGroupId());

        groupRepository.insertManager(userId, groupId);
    }

    public void postGroupMember(PostGroupMemberRequest request) {
        UserId userId = new UserId(request.getUserId());
        GroupId groupId = new GroupId(request.getGroupId());

        groupRepository.insertMember(userId, groupId);
    }

    public void deleteGroup(Integer groupId) {
        groupRepository.deleteGroup(new GroupId(groupId));
    }

    public void deleteGroupManager(DeleteGroupManagerRequest request) {
        UserId userId = new UserId(request.getUserId());
        GroupId groupId = new GroupId(request.getGroupId());

        groupRepository.deleteManager(userId, groupId);
    }

    public void deleteGroupMember(DeleteGroupMemberRequest request) {
        UserId userId = new UserId(request.getUserId());
        GroupId groupId = new GroupId(request.getGroupId());

        groupRepository.deleteMember(userId, groupId);
    }

    public GroupResponse getGroup(Integer groupId) {
        Group group = groupRepository.getGroup(new GroupId(groupId));
        return groupResponseConverter.convert(group);
    }

    public List<GroupResponse> getGroups() {
        GroupList groupList = groupRepository.getGroup();
        return groupResponseConverter.convert(groupList);
    }
}
