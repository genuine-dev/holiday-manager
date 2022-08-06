package holiday.manager.application.service.holiday.application;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplication;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.application.HolidayApplicationRepository;
import holiday.manager.domain.model.holiday.application.HolidayApplicationStatus;
import holiday.manager.domain.model.holiday.application.HolidayType;
import holiday.manager.domain.model.user.User;
import holiday.manager.domain.model.user.UserId;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationEntity;
import holiday.manager.port.adapter.persistence.repository.holiday.application.query.HolidayApplicationQueryRepository;

@Service
public class HolidayApplicationService {
	@Autowired
	private HolidayApplicationRepository repository;
	@Autowired
	private HolidayApplicationQueryRepository queryRepository;

	/**
	 * 休暇申請を申請する
	 * @param kindOfHoliday 休暇の種別
	 * @param holidayType　休暇のタイプ
	 * @param date 休暇取得日
	 * @param applicant 申請者
	 * @return 休暇申請
	 */
	public HolidayApplication apply(KindOfHoliday kindOfHoliday, HolidayType holidayType, Date date, User applicant) {
		HolidayApplication application = new HolidayApplication(kindOfHoliday, holidayType, date, applicant.getId());
		repository.save(application);

		return application;
	}

	/**
	 * 休暇申請を承認する
	 * @param id 休暇申請ID
	 * @param approver 承認者
	 * @return 休暇申請
	 */
	public HolidayApplication approve(HolidayApplicationId id, User approver) {
		HolidayApplication application = repository.findById(id);
		application.approve(approver);
		repository.save(application);

		return application;
	}

	/**
	 * 休暇申請を却下する
	 * @param id 休暇申請ID
	 * @param approver 承認者
	 * @return 休暇申請
	 */
	public HolidayApplication reject(HolidayApplicationId id, User approver) {
		HolidayApplication application = repository.findById(id);
		application.reject(approver);
		repository.save(application);

		return application;
	}

	/**
	 * 休暇申請を取り消す
	 * @param id 休暇申請ID
	 * @param applecant 申請者
	 * @return 休暇申請
	 */
	public HolidayApplication cancel(HolidayApplicationId id, User applecant) {
		HolidayApplication application = repository.findById(id);
		application.cancel(applecant);

		repository.save(application);

		return application;

	}

	/**
	 * 休暇申請を処理済みにする
	 * @param id 休暇申請ID
	 * @return 休暇申請
	 */
	public HolidayApplication process(HolidayApplicationId id) {
		HolidayApplication application = repository.findById(id);
		application.process();

		repository.save(application);

		return application;

	}

	/**
	 * 休暇申請を処理失敗にする
	 * @param id 休暇申請ID
	 * @return 休暇申請
	 */
	public HolidayApplication processFail(HolidayApplicationId id) {
		HolidayApplication application = repository.findById(id);
		application.processFail();

		repository.save(application);

		return application;

	}

	public List<HolidayApplication> findByAplicantId(UserId applicantId) {

		List<HolidayApplicationEntity> holidayApplicationEntities = queryRepository.findByAplicantId(applicantId.getValue());

		return convert(holidayApplicationEntities);
	}

	public List<HolidayApplication> findByStatus(HolidayApplicationStatus status) {

		List<HolidayApplicationEntity> holidayApplicationEntities = queryRepository.findByStatus(status.name());

		return convert(holidayApplicationEntities);
	}

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
