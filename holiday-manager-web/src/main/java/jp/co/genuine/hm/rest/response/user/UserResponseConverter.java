package jp.co.genuine.hm.rest.response.user;

import jp.co.genuine.hm.model.rule.RuleId;
import jp.co.genuine.hm.model.user.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserResponseConverter {
    public UserList convert(UserResponse[] userResponses) {
        List<User> users = new ArrayList<>();

        for(UserResponse userResponse : userResponses)
            users.add(convert(userResponse));

        return new UserList(users);
    }

    public UserList convert(List<UserResponse> userResponses) {
        List<User> users = new ArrayList<>();

        for(UserResponse userResponse : userResponses)
            users.add(convert(userResponse));

        return new UserList(users);
    }

    public User convert(UserResponse userResponse) {
        AccountId accountId = new AccountId(userResponse.getAccountId());
        UserId userId = new UserId(userResponse.getUserId());
        MailAddress mailAddress = new MailAddress(userResponse.getMailAddress());
        Password password = new Password(userResponse.getPassword());
        UserName userName = new UserName(userResponse.getUserName());
        UserStatus userStatus = UserStatus.valueOf(userResponse.getUserStatus());
        HireDate hireDate = new HireDate(userResponse.getHireDate());
        LeftoverHoliday leftoverHoliday = new LeftoverHoliday(BigDecimal.valueOf(userResponse.getLeftoverHoliday()));
        boolean admin = userResponse.isAdmin();
        RuleId ruleId = new RuleId(userResponse.getRuleId());

        return new User(accountId, userId, mailAddress, password, userName, userStatus, hireDate, leftoverHoliday, ruleId);
    }
}
