package jp.co.genuine.hm.controller.group;

import jp.co.genuine.hm.model.group.*;
import jp.co.genuine.hm.model.user.LoginUser;
import jp.co.genuine.hm.model.user.User;
import jp.co.genuine.hm.service.group.GroupService;
import jp.co.genuine.hm.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("group")
public class GroupController {
	public GroupController(GroupService groupService, UserService userService) {
		this.groupService = groupService;
		this.userService = userService;
	}

	private final GroupService groupService;
	private final UserService userService;

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String userList(Model model) throws Exception {
		checkAdmin();
		GroupList result = groupService.getGroupList();
		model.addAttribute("groupList", result.getGroups());
		return "group/list/group_list";
	}

	@RequestMapping(value="details/{groupId}", method=RequestMethod.GET)
	public String groupDetails(@PathVariable("groupId") Integer groupId, Model model) {
		checkAdmin();
		Group group = groupService.getGroup(new GroupId(groupId));
		model.addAttribute("group", group);
		List<GroupMember> groupMemberList = groupMemberList(group);
		model.addAttribute("groupMemberList", groupMemberList);
		return "group/detail/group_detail";
	}

	@RequestMapping(value="update/{groupId}", method=RequestMethod.GET)
	public String groupUpdate(@PathVariable("groupId") Integer groupId, @ModelAttribute("viewGroupMemberList")GroupMemberList viewGroupMemberList, Model model) {
		checkAdmin();

		Group group = groupService.getGroup(new GroupId(groupId));
		model.addAttribute("group", group);

		refreshViewGroupMemberList(groupId, viewGroupMemberList, model, group);
		return "group/update/group_update";
	}

	@RequestMapping(value="update/complete/{groupId}", method=RequestMethod.POST)
	public String groupUpdateComplete(@PathVariable("groupId") Integer groupId, @ModelAttribute("viewGroupMemberList")GroupMemberList viewGroupMemberList, Model model) {
		checkAdmin();

		PutGroupRequest groupNameRequest = new PutGroupRequest(viewGroupMemberList.getGroupName());
		ResponseEntity<Void> putGroupResponse = groupService.putGroup(groupNameRequest, new GroupId(groupId));
		checkResponseError(model, putGroupResponse);

		ResponseEntity<Void> postGroupResponse = groupService.postGroupMembers(new GroupId(groupId), viewGroupMemberList);
		checkResponseError(model, postGroupResponse);

		return "group/update/group_update_complete";
	}

	@RequestMapping(value="register", method=RequestMethod.GET)
	public String groupRegister(@ModelAttribute("postGroupRequest") PostGroupRequest postGroupRequest, Model model) {
		checkAdmin();

		return "group/register/group_register";
	}

	@RequestMapping(value="register/complete", method=RequestMethod.POST)
	public String groupRegisterComplete(@ModelAttribute("postGroupRequest") PostGroupRequest postGroupRequest, Model model) {
		checkAdmin();

		ResponseEntity<Void> response = groupService.postGroup(postGroupRequest);
		checkResponseError(model, response);

		return "group/register/group_register_complete";
	}

	@RequestMapping(value="delete/{groupId}", method=RequestMethod.GET)
	public String groupDelete(@PathVariable("groupId") Integer groupId, Model model) {
		checkAdmin();

		ResponseEntity<Void> response = groupService.deleteGroup(new GroupId(groupId));
		checkResponseError(model, response);

		return "group/delete/group_delete_complete";
	}

	public void checkAdmin() {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!user.getAccount().isAdminFlg()) {
			throw new AccessDeniedException("dont ADMIN.");
		}
	}

	private void checkResponseError(Model model, ResponseEntity<Void> response) {
		if(response.getStatusCode() != HttpStatus.OK) {
			model.addAttribute("isError", true);
			return;
		}

		model.addAttribute("isError", false);
	}

	private List<GroupMember> groupMemberList(Group result) {
		List<User> managerList = result.getManagerList().getUserList();
		List<User> memberList = result.getMemberList().getUserList();
		List<GroupMember> groupMemberList = new ArrayList<GroupMember>();
		managerList.forEach(manager -> {
			GroupMember groupMember = new GroupMember();
			groupMember.setUserName(manager.getUserName().getValue());
			groupMember.setManager(true);
			groupMemberList.add(groupMember);
		});
		memberList.forEach(member -> {
			GroupMember gtoupMember = new GroupMember();
			gtoupMember.setUserName(member.getUserName().getValue());
			gtoupMember.setMember(true);
			groupMemberList.add(gtoupMember);
		});
		return groupMemberList;
	}

	private void refreshViewGroupMemberList(Integer groupId, GroupMemberList viewGroupMemberList, Model model, Group result) {
		List<User> allUserList = userService.getUserList().getUserList();
		List<User> managerList = result.getManagerList().getUserList();
		List<User> memberList = result.getMemberList().getUserList();
		List<GroupMember> groupMemberList = new ArrayList<GroupMember>();
		allUserList.forEach(user ->{
			GroupMember groupMember = new GroupMember();
			groupMember.setUserId(user.getUserId().getValue());
			groupMember.setUserName(user.getUserName().getValue());
			boolean managerFlg = false;
			for(User manager : managerList) {
				if(manager.getUserId().getValue().equals(user.getUserId().getValue())) {
					managerFlg = true;
				}
			}
			boolean memberFlg = false;
			for(User member : memberList) {
				if(member.getUserId().getValue().equals(user.getUserId().getValue())) {
					memberFlg = true;
				}
			}
			groupMember.setManager(managerFlg);
			groupMember.setMember(memberFlg);

			groupMemberList.add(groupMember);
		});

		viewGroupMemberList.setGroupMemberList(groupMemberList);
		viewGroupMemberList.setGroupName(result.getGroupName().getValue());
		viewGroupMemberList.setGroupId(groupId);
		model.addAttribute("viewGroupMemberList", viewGroupMemberList);
	}
}
