package jp.co.genuine.hm.api.domain.holiday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.application.HolidayType;
import holiday.manager.domain.model.user.User;
import holiday.manager.domain.model.user.UserId;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayCancelRequest;
import jp.co.genuine.hm.api.domain.request.holiday.DeleteHolidayRejectRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayApplyRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PostHolidayGrantRequest;
import jp.co.genuine.hm.api.domain.request.holiday.PutHolidayApproveRequest;

@Component
public class HolidayFactory {
	@Autowired
	private HolidayRepository holidayRepository;

	public HolidayApplyDTO create(PostHolidayApplyRequest request) throws ParseException {
		User user = applicantByUserId(new UserId(Integer.parseInt(request.getApplicantId())));
		KindOfHoliday kindOfHoliday = KindOfHoliday.valueOf(request.getKindOfHoliday());
		HolidayType holidayType = HolidayType.valueOf(request.getHolidayType());
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDate());
		return new HolidayApplyDTO(user, kindOfHoliday, holidayType, date);
	}

	public HolidayApproveDTO create(PutHolidayApproveRequest request) {
		HolidayApplicationId applicationId = new HolidayApplicationId(request.getHolidayApplicationId());
		User user = approverByUserId(new UserId(Integer.parseInt(request.getApproverId())));
		return new HolidayApproveDTO(applicationId, user);
	}

	public HolidayRejectDTO create(DeleteHolidayRejectRequest request) {
		HolidayApplicationId applicationId = new HolidayApplicationId(request.getHolidayApplicationId());;
		User user = approverByUserId(new UserId(Integer.parseInt(request.getApproverId())));;
		return new HolidayRejectDTO(applicationId, user);
	}

	public HolidayCancelDTO create(DeleteHolidayCancelRequest request) {
		HolidayApplicationId applicationId = new HolidayApplicationId(request.getHolidayApplicationId());;
		User user = approverByUserId(new UserId(Integer.parseInt(request.getApplicantId())));;
		return new HolidayCancelDTO(applicationId, user);
	}

	public HolidayGrantDTO create(PostHolidayGrantRequest request) throws ParseException {
		UserId userId = new UserId(Integer.parseInt(request.getUserId()));
		KindOfHoliday kindOfHoliday = KindOfHoliday.valueOf(request.getKindOfHoliday());
		double days = Double.parseDouble(request.getDays());
		Date grantedDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getGrantedDate());
		Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getExpirationDate());
		return new HolidayGrantDTO(userId, kindOfHoliday, days, grantedDate, expirationDate);
	}

	public User applicantByUserId(UserId userId) {
		UserId applicantUserId = holidayRepository.findApplicantUserIdByUserId(userId);
		return new User(applicantUserId, new ArrayList<User>());
	}

	private User approverByUserId(UserId userId) {
		List<UserId> managedMemberUserIds = holidayRepository.findManagedMembersByUserId(userId);
		List<User> managedMembers = new ArrayList<User>();
		for(UserId managedMemberUserId : managedMemberUserIds) {
			managedMembers.add(new User(managedMemberUserId, new ArrayList<User>()));
		}
		return new User(userId, managedMembers);
	}

}
