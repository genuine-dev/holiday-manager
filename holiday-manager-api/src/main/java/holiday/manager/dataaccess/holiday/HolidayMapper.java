package holiday.manager.dataaccess.holiday;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import holiday.manager.domain.model.user.UserId;

@Mapper
public interface HolidayMapper {

	public UserId findApplicantUserIdByUserId(@Param("userId") UserId userId);

	public List<UserId> findManagedMembersByUserId(@Param("userId") UserId userId);

}
