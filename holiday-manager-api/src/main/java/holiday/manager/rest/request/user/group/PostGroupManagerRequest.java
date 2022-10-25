package holiday.manager.rest.request.user.group;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class PostGroupManagerRequest {
    @NotNull
    @ApiModelProperty(example = "5", required = true)
    private Integer userId;

    @NotNull
    @ApiModelProperty(example = "2", required = true)
    private Integer groupId;

    public PostGroupManagerRequest(Integer userId, Integer groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

}
