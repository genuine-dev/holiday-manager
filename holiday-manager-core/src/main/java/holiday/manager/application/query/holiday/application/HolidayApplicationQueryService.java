package holiday.manager.application.query.holiday.application;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
}
