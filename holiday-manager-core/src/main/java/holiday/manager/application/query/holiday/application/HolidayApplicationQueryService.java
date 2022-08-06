package holiday.manager.application.query.holiday.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.application.HolidayType;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import holiday.manager.application.query.holiday.application.dto.HolidayApplicationDto;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationQueryRepository;

@Component
public class HolidayApplicationQueryService {
	@Autowired
	private HolidayApplicationQueryRepository queryRepository;

	/**
	 * 休暇申請一覧を取得する
	 * @return 休暇申請
	 */
	public List<HolidayApplicationDto> findAll() {

		return StreamSupport.stream(queryRepository.findAll().spliterator(), false)
				.map(entity -> {
					HolidayApplicationDto dto = new HolidayApplicationDto();
					dto.setId(entity.getId());
					dto.setKind(entity.getKind());
					dto.setType(entity.getType());
					dto.setStatus(entity.getStatus());
					dto.setDate(entity.getDate());
					dto.setAplicantId(entity.getAplicantId());
					dto.setApproverId(entity.getApproverId());
					dto.setApplyDate(entity.getCreatedAt());
					dto.setApproveDate(entity.getUpdatedAt());
					return dto;
				})
				.collect(Collectors.toList());

	}

	/**
	 * 休暇申請一覧を取得する
	 * @param applicantId 申請者
	 * @return 休暇申請
	 */
	public List<HolidayApplication> findByAplicantId(UserId applicantId) {

		List<HolidayApplicationEntity> holidayApplicationEntities = queryRepository.findByAplicantId(applicantId.getValue());

		return convert(holidayApplicationEntities);
	}

	/**
	 * 休暇申請一覧を取得する
	 * @param status 休暇申請ステータス
	 * @return 休暇申請
	 */
	public List<HolidayApplication> findByStatus(HolidayApplicationStatus status) {

		List<HolidayApplicationEntity> holidayApplicationEntities = queryRepository.findByStatus(status.name());

		return convert(holidayApplicationEntities);
	}

	/**
	 * 休暇申請一覧を取得する
	 * @param applicantId 申請者
	 * @param status 休暇申請ステータス
	 * @return 休暇申請
	 */
	public List<HolidayApplication> findByAplicantIdAndStatus(UserId applicantId, HolidayApplicationStatus status) {

		List<HolidayApplicationEntity> holidayApplicationEntities = queryRepository.findByAplicantIdAndStatus(applicantId.getValue(), status.name());

		return convert(holidayApplicationEntities);
	}

	private List<HolidayApplication> convert(List<HolidayApplicationEntity> holidayApplicationEntities) {

		List<HolidayApplication> holidayApplications = new ArrayList<HolidayApplication>();

		for (HolidayApplicationEntity entity : holidayApplicationEntities) {
			addHolidayApplication(holidayApplications, entity);
		}

		return holidayApplications;
	}

	private void addHolidayApplication(List<HolidayApplication> holidayApplications, HolidayApplicationEntity entity) {

		HolidayApplicationId holidayApplicationId = new HolidayApplicationId(entity.getId());
		KindOfHoliday kindOfHoliday = KindOfHoliday.valueOf(entity.getKind());
		HolidayType holidayType = HolidayType.valueOf(entity.getType());
		Date date = entity.getDate();
		HolidayApplicationStatus holidayApplicationStatus = HolidayApplicationStatus.valueOf(entity.getStatus());
		UserId applicant = new UserId(entity.getAplicantId());
		UserId approverId = new UserId(entity.getApproverId());

		HolidayApplication holidayApplication = new HolidayApplication(holidayApplicationId, kindOfHoliday, holidayType,
				date, holidayApplicationStatus, applicant, approverId);

		holidayApplications.add(holidayApplication);
	}
}
