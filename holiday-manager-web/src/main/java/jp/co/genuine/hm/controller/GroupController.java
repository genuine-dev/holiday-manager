package jp.co.genuine.hm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.genuine.hm.model.group.Group;
import jp.co.genuine.hm.model.group.GroupId;
import jp.co.genuine.hm.model.group.GroupList;
import jp.co.genuine.hm.model.group.GroupMember;
import jp.co.genuine.hm.model.group.GroupMemberList;
import jp.co.genuine.hm.model.group.PostGroupRequest;
import jp.co.genuine.hm.model.group.PutGroupRequest;
import jp.co.genuine.hm.model.user.LoginUser;
import jp.co.genuine.hm.model.user.User;
import jp.co.genuine.hm.service.user.GroupService;
import jp.co.genuine.hm.service.user.GroupServiceImpl;
import jp.co.genuine.hm.service.user.UserService;
import jp.co.genuine.hm.service.user.UserServiceImpl;

@Controller
public class GroupController {
	ObjectMapper mapper = new ObjectMapper();
	CloseableHttpClient client = HttpClients.createDefault();
	GroupService groupService = new GroupServiceImpl();
	UserService userService = new UserServiceImpl();

	@RequestMapping(value="/group/list", method=RequestMethod.GET)
	public String userList(Model model) throws Exception {
		checkAdmin();

		GroupList result = groupService.getGroupList();
		model.addAttribute("groupList", result.getGroups());
		return "group_list";
	}

	@RequestMapping(value="/group/details/{groupId}", method=RequestMethod.GET)
	public String groupDetails(@PathVariable("groupId") Integer groupId, Model model) throws Exception {
		checkAdmin();

		GroupId groupIdParam = new GroupId();
		groupIdParam.setValue(groupId);
		Group result = groupService.getGroup(groupIdParam);
		model.addAttribute("group", result);

		List<User> managerList = result.getManagerList().getUserList();
		List<User> memberList = result.getMemberList().getUserList();
		List<GroupMember> groupMemberList = new ArrayList<GroupMember>();
		managerList.forEach(manager -> {
			GroupMember member = new GroupMember();
			member.setUserName(manager.getUserName().getValue());
			member.setManager(true);
			groupMemberList.add(member);
		});
		memberList.forEach(manager -> {
			GroupMember member = new GroupMember();
			member.setUserName(manager.getUserName().getValue());
			member.setManager(true);
			groupMemberList.add(member);
		});

		model.addAttribute("groupMemberList", groupMemberList);

		return "group_details";
	}

	@RequestMapping(value="/group/update/{groupId}", method=RequestMethod.GET)
	public String groupUpdate(@PathVariable("groupId") Integer groupId, @ModelAttribute("viewGroupMemberList")GroupMemberList viewGroupMemberList, Model model) throws Exception {
		checkAdmin();

		GroupId groupIdParam = new GroupId();
		groupIdParam.setValue(groupId);
		Group result = groupService.getGroup(groupIdParam);
		model.addAttribute("group", result);

		List<User> allUserList = userService.getUserList().getUserList();
		List<User> managerList = result.getManagerList().getUserList();
		List<User> memberList = result.getMemberList().getUserList();
		List<GroupMember> groupMemberList = new ArrayList<GroupMember>();
		allUserList.forEach(user ->{
			GroupMember groupMember = new GroupMember();
			groupMember.setUserName(user.getUserName().getValue());
			boolean managerFlg = false;
			for(User manager : managerList) {
				if(manager.getUserName().getValue().equals(user.getUserName().getValue())) {
					managerFlg = true;
				}
			}
			boolean memberFlg = false;
			for(User member : memberList) {
				if(member.getUserName().getValue().equals(user.getUserName().getValue())) {
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

		return "group_update";
	}
	@RequestMapping(value="/group/update/complete/{groupId}", method=RequestMethod.POST)
	public String groupUpdateComplete(@PathVariable("groupId") Integer groupId, @ModelAttribute("viewGroupMemberList")GroupMemberList viewGroupMemberList, Model model) throws Exception {
		checkAdmin();

		PutGroupRequest groupNameRequest = new PutGroupRequest(viewGroupMemberList.getGroupName());
		GroupId groupIdParam = new GroupId();
		groupIdParam.setValue(groupId);
		CloseableHttpResponse response = groupService.putGroup(groupNameRequest, groupIdParam);
		if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			model.addAttribute("isError", true);
		} else {
			model.addAttribute("isError", false);
		}

		return "group_register_complete";
	}

	@RequestMapping(value="/group/register", method=RequestMethod.GET)
	public String groupRegister(@ModelAttribute("postGroupRequest") PostGroupRequest postGroupRequest, Model model) throws Exception {
		checkAdmin();

		return "group_register";
	}

	@RequestMapping(value="/group/register/complete", method=RequestMethod.POST)
	public String groupRegisterComplete(@ModelAttribute("postGroupRequest") PostGroupRequest postGroupRequest, Model model) throws Exception {
		checkAdmin();

		CloseableHttpResponse response = groupService.postGroup(postGroupRequest);
		if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			model.addAttribute("isError", true);
		} else {
			model.addAttribute("isError", false);
		}

		return "group_register_complete";
	}

	@RequestMapping(value="/group/delete/{groupId}", method=RequestMethod.GET)
	public String groupDelete(@PathVariable("groupId") Integer groupId, Model model) {
		checkAdmin();

		return "group_delete_complete"
;	}

	public void checkAdmin() {
		LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!user.getAccount().isAdminFlg()) {
			throw new AccessDeniedException("dont ADMIN.");
		}
	}
}
