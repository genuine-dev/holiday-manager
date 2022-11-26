package jp.co.genuine.hm.rest.response.group;

import jp.co.genuine.hm.model.group.Group;
import jp.co.genuine.hm.model.group.GroupId;
import jp.co.genuine.hm.model.group.GroupList;
import jp.co.genuine.hm.model.group.GroupName;
import jp.co.genuine.hm.model.user.UserList;
import jp.co.genuine.hm.rest.response.user.UserResponseConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupResponseConverter {
    private final UserResponseConverter userResponseConverter;

    public GroupResponseConverter(UserResponseConverter userResponseConverter) {
        this.userResponseConverter = userResponseConverter;
    }

    public GroupList convert(GroupResponse[] groupResponses) {
        List<Group> groups = new ArrayList<>();

        for (GroupResponse groupResponse : groupResponses)
            groups.add(convert(groupResponse));

        return new GroupList(groups);
    }

    public Group convert(GroupResponse groupResponse) {
        GroupId groupId = new GroupId(groupResponse.getGroupId());
        GroupName groupName = new GroupName(groupResponse.getGroupName());
        UserList managerList = userResponseConverter.convert(groupResponse.getManagerList());
        UserList memberList = userResponseConverter.convert(groupResponse.getMemberList());

        return new Group(groupId, groupName, managerList, memberList);
    }
}
