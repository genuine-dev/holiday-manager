package holiday.manager.service.user;

import holiday.manager.domain.user.*;
import holiday.manager.domain.user.account.AccountId;
import holiday.manager.domain.user.alert.HolidayAlert;
import holiday.manager.rest.request.user.PostUserRequest;
import holiday.manager.rest.request.user.PutUserRequest;
import holiday.manager.rest.request.user.parameter.UserQueries;
import holiday.manager.rest.request.user.parameter.UserSorts;
import holiday.manager.rest.response.user.UserResponse;
import holiday.manager.rest.response.user.UserResponseConverter;
import holiday.manager.service.validation.ValidateService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserFactory userFactory;

    private final ValidateService validateService;

    private final UserResponseConverter userResponseConverter;

    public UserService(UserRepository userRepository, UserFactory userFactory, ValidateService validateService, UserResponseConverter userResponseConverter) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.validateService = validateService;
        this.userResponseConverter = userResponseConverter;
    }

    public List<UserResponse> getUser(UserSorts sorts, UserQueries queries) {
        UserList userList = userRepository.list(sorts, queries);
        return userResponseConverter.convert(userList);
    }

    public void postUser(PostUserRequest request) {
        User user = userFactory.create(request);
        validateService.validate(user);
        userRepository.insertUser(user);
        userRepository.insertAccount(user);
        userRepository.insertRule(user);
    }

    public void putUser(UserId userId, PutUserRequest request) {
        User user = userFactory.create(userId, request);
        validateService.validate(user);
        userRepository.updateUser(user);
        updateAccount(user);
    }

    private void updateAccount(User user) {
        if (user.isEmptyPassword()) {
            return;
        }
        userRepository.updateAccount(user);
    }

    public void deleteUser(@Valid UserId userId) {
        userRepository.deleteUser(userId);
    }

    public UserResponse findUser(UserId userId) {
        User user = userRepository.findBy(userId);
        return userResponseConverter.convert(user);
    }

    public Boolean existsAccountId(AccountId accountId) {
        return userRepository.existsAccountId(accountId);
    }

    public Map<String, String> getUserStatus() {
        Map<String, String> userStatusMap = new HashMap<>();

        for (UserStatus userStatus : UserStatus.values())
            userStatusMap.put(userStatus.name(), userStatus.getLabel());

        return userStatusMap;
    }

    public List<Integer> findManagementUserIds(Integer userId) {
        return userRepository.findManagementUserIds(userId);
    }

    public HolidayAlert findHolidayAlert(UserId userId) {
        return userRepository.findHolidayAlert(userId);
    }

    //TODO:このロジックでいい？
    public holiday.manager.domain.model.user.User applicantByUserId(holiday.manager.domain.model.user.UserId userId) {
        holiday.manager.domain.model.user.UserId applicantUserId = userRepository.findApplicantUserIdByUserId(userId);
        return new holiday.manager.domain.model.user.User(applicantUserId, new ArrayList<holiday.manager.domain.model.user.User>());
    }

    //TODO:このロジックでいい？
    public holiday.manager.domain.model.user.User approverByUserId(holiday.manager.domain.model.user.UserId userId) {
        List<holiday.manager.domain.model.user.UserId> managedMemberUserIds = userRepository.findManagedMembersByUserId(userId);

        List<holiday.manager.domain.model.user.User> managedMembers = new ArrayList<holiday.manager.domain.model.user.User>();
        for (holiday.manager.domain.model.user.UserId managedMemberUserId : managedMemberUserIds)
            managedMembers.add(new holiday.manager.domain.model.user.User(managedMemberUserId, new ArrayList<holiday.manager.domain.model.user.User>()));

        return new holiday.manager.domain.model.user.User(userId, managedMembers);
    }
}
