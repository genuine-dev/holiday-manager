package holiday.manager.application.query.holiday.application;

import holiday.manager.application.query.holiday.application.dto.HolidayApplicationDto;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationEntity;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
					return convertToDto(entity);
				})
				.collect(Collectors.toList());

	}

	/**
	 * 休暇申請一覧を取得する
	 * @param applicantId 申請者
	 * @return 休暇申請
	 */
	public List<HolidayApplicationDto> findByAplicantId(UserId applicantId) {

		return StreamSupport.stream(queryRepository.findByAplicantId(applicantId.getValue()).spliterator(), false)
				.map(entity -> {
					return convertToDto(entity);
				})
				.collect(Collectors.toList());
	}

	/**
	 * 休暇申請一覧を取得する
	 * @param status 休暇申請ステータス
	 * @return 休暇申請
	 */
	public List<HolidayApplicationDto> findByStatus(HolidayApplicationStatus status) {

		return StreamSupport.stream(queryRepository.findByStatus(status.name()).spliterator(), false)
				.map(entity -> {
					return convertToDto(entity);
				})
				.collect(Collectors.toList());
	}

	/**
	 * 休暇申請一覧を取得する
	 * @param applicantId 申請者
	 * @param status 休暇申請ステータス
	 * @return 休暇申請
	 */
	public List<HolidayApplicationDto> findByAplicantIdAndStatus(UserId applicantId, HolidayApplicationStatus status) {

		return StreamSupport.stream(queryRepository.findByAplicantIdAndStatus(applicantId.getValue(), status.name()).spliterator(), false)
				.map(entity -> {
					return convertToDto(entity);
				})
				.collect(Collectors.toList());
	}

	/**
	 * 休暇申請を取得する
	 * @param holidayApplicationId 休暇申請ID
	 * @return 休暇申請
	 */
	public HolidayApplicationDto findById(HolidayApplicationId holidayApplicationId) {
		Optional<HolidayApplicationEntity> entity = queryRepository.findById(holidayApplicationId.getValue());
		return convertToDto(entity.get());
	}

	private static HolidayApplicationDto convertToDto(HolidayApplicationEntity entity) {
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
	}
}
