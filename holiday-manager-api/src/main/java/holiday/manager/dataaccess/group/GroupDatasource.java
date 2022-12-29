package holiday.manager.dataaccess.group;

import holiday.manager.dataaccess.user.UserMapper;
import holiday.manager.domain.group.*;
import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserId;
import holiday.manager.domain.user.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDatasource implements GroupRepository {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Group findGroup(GroupId groupId) {
        List<User> managers = userMapper.listManager(groupId);
        List<User> members = userMapper.listMember(groupId);
        GroupName groupName = groupMapper.findGroupName(groupId);
        return new Group(groupId, groupName, new UserList(managers), new UserList(members));
    }

    @Override
    public void insertGroup(GroupId groupId, GroupName groupName) {
        groupMapper.insertGroup(groupId, groupName);
    }

    @Override
    public void updateGroup(GroupId groupId, GroupName groupName) {
        groupMapper.updateGroup(groupId, groupName);
    }

    @Override
    public GroupId nextGroupId() {
        return groupMapper.nextGroupId();
    }

    @Override
    public void deleteManager(UserId userId, GroupId groupId) {
        groupMapper.deleteManager(userId, groupId);
    }

    @Override
    public void deleteMember(UserId userId, GroupId groupId) {
        groupMapper.deleteMember(userId, groupId);
    }

    @Override
    public void deleteGroup(GroupId groupId) {
        groupMapper.deleteGroup(groupId);
    }

    @Override
    public Group getGroup(GroupId groupId) {
        return groupMapper.findGroupById(groupId);
    }

    @Override
    public GroupList getGroup() {
        List<Group> groups = groupMapper.findGroups();
        return new GroupList(groups);
    }

    @Override
    public void insertManager(UserId userId, GroupId groupId) {
        groupMapper.insertManager(userId, groupId);
    }

    @Override
    public void insertMember(UserId userId, GroupId groupId) {
        groupMapper.insertMember(userId, groupId);
    }

    @Override
    public void deleteMembers(GroupId groupId) {
        groupMapper.deleteMembers(groupId);
    }

    @Override
    public void deleteManagers(GroupId groupId) {
        groupMapper.deleteManagers(groupId);
    }

}
