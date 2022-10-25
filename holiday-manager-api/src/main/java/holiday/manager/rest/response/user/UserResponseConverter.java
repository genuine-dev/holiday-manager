package holiday.manager.rest.response.user;

import holiday.manager.domain.user.User;
import holiday.manager.domain.user.UserList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserResponseConverter {
    public List<UserResponse> convert(UserList userList) {
        List<UserResponse> userResponses = new ArrayList<>();

        for(User user : userList.getUserList())
            userResponses.add(convert(user));

        return userResponses;
    }

    public UserResponse convert(User user) {
        String accountId = user.getAccountId().getValue();
        Integer userId = user.getUserId().getValue();
        String mailAddress = user.getMailAddress().getValue();
        String password = user.getPassword().getValue();
        String userName = user.getUserName().getValue();
        String userStatus = user.getUserStatus().name();
        Date hireDate = user.getHireDate().getValue();
        Double leftoverHoliday = user.getLeftoverHoliday().getValue();
        boolean admin = user.isAdmin();
        Integer ruleId = user.getRuleId().getValue();

        return new UserResponse(accountId, userId, mailAddress, password, userName, userStatus, hireDate, leftoverHoliday, admin, ruleId);
    }
}
