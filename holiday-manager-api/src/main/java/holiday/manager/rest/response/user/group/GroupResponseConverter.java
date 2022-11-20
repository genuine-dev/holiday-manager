package holiday.manager.rest.response.user.group;

import holiday.manager.domain.user.group.Group;
import holiday.manager.domain.user.group.GroupList;
import holiday.manager.rest.response.user.UserResponse;
import holiday.manager.rest.response.user.UserResponseConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupResponseConverter {
    private final UserResponseConverter userResponseConverter;

    public GroupResponseConverter(UserResponseConverter userResponseConverter) {
        this.userResponseConverter = userResponseConverter;
    }

    public List<GroupResponse> convert(GroupList groupList) {
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (Group group : groupList.getGroups())
            groupResponses.add(convert(group));

        return groupResponses;
    }

    public GroupResponse convert(Group group) {
        Integer groupId = group.getGroupId().getValue();
        String groupName = group.getGroupName().getValue();
        List<UserResponse> managerList = userResponseConverter.convert(group.getManagerList());
        List<UserResponse> memberList = userResponseConverter.convert(group.getMemberList());

        return new GroupResponse(groupId, groupName, managerList, memberList);
    }
}
