package holiday.manager.controller.group;

import holiday.manager.rest.request.group.*;
import holiday.manager.rest.response.group.GroupResponse;
import holiday.manager.service.group.GroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ApiOperation("グループ一覧")
    @GetMapping("/group")
    public ResponseEntity<List<GroupResponse>> getGroups() {
        List<GroupResponse> groupResponses = groupService.getGroups();
        return new ResponseEntity<List<GroupResponse>>(groupResponses, HttpStatus.OK);
    }

    @ApiOperation("グループ詳細")
    @GetMapping("/group/{group_id}")
    public ResponseEntity<GroupResponse> getGroup(@PathVariable("group_id") Integer groupId) {
        GroupResponse groupResponse = groupService.getGroup(groupId);
        return new ResponseEntity<GroupResponse>(groupResponse, HttpStatus.OK);
    }

    @ApiOperation("グループ登録")
    @PostMapping("/group")
    public ResponseEntity<Void> postGroup(@RequestBody @Valid PostGroupRequest request) {
        groupService.postGroup(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation("グループ更新")
    @PutMapping("/group/{group_id}")
    public ResponseEntity<Void> putGroup(@PathVariable("group_id") Integer groupId,
                                         @RequestBody @Valid PutGroupRequest request) {
        groupService.putGroup(groupId, request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation("グループ削除")
    @DeleteMapping("/group/{group_id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("group_id") Integer groupId) {
        groupService.deleteGroup(groupId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation("グループにマネージャ、メンバーを登録")
    @PostMapping("/group/members")
    public ResponseEntity<Void> postGroupMembers(@RequestBody @Valid PostGroupMembersRequest request) {
        groupService.postGroupMembers(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation("グループにマネージャを登録")
    @PostMapping("/group/manager")
    public ResponseEntity<Void> postGroupManager(@RequestBody @Valid PostGroupManagerRequest request) {
        groupService.postGroupManager(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation("グループからマネージャーを削除")
    @DeleteMapping("/group/manager")
    public ResponseEntity<Void> deleteGroupManager(@RequestBody @Valid DeleteGroupManagerRequest request) {
        groupService.deleteGroupManager(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation("グループにメンバーを登録")
    @PostMapping("/group/member")
    public ResponseEntity<Void> postGroupMember(@RequestBody @Valid PostGroupMemberRequest request) {
        groupService.postGroupMember(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation("グループからメンバーを削除")
    @DeleteMapping("/group/member")
    public ResponseEntity<Void> postGroupMember(@RequestBody @Valid DeleteGroupMemberRequest request) {
        groupService.deleteGroupMember(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
