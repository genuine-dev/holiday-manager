package holiday.manager.application.service.holiday.holiday;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.holiday.HolidayList;
import holiday.manager.domain.model.holiday.holiday.HolidayListId;
import holiday.manager.domain.model.holiday.holiday.HolidayListRepository;
import holiday.manager.domain.model.user.UserId;

@Service
public class HolidayListService {
	@Autowired
	private HolidayListRepository repository;

	/**
	 * 休暇リストを作成する
	 * @param owner 休暇リストの所有者
	 * @return 休暇リスト
	 */
	public HolidayList createHolidayList(UserId owner) {
		HolidayList holidayList = new HolidayList(owner);
		repository.save(holidayList);
		return holidayList;
	}

	/**
	 * 休暇を付与する
	 * @param owner ユーザーID
	 * @param kind 休暇種別
	 * @param days 日数
	 * @param grantedDate 休暇付与日
	 * @param expirationDate 休暇使用期限
	 * @return 休暇リスト
	 */
	public HolidayList grantHoliday(UserId owner, KindOfHoliday kind, double days, Date grantedDate,
			Date expirationDate) {
		HolidayList holidayList = repository.findById(new HolidayListId(owner.getValue().toString()));
		holidayList.grant(kind, days, grantedDate, expirationDate);
		repository.save(holidayList);
		return holidayList;

	}

	/**
	 * 休暇を取得する
	 * @param owner ユーザーID
	 * @param kind 休暇種別
	 * @param days 日数
	 * @param date 休暇取得日
	 * @param applicationId 休暇申請ID
	 * @return 休暇リスト
	 */
	public HolidayList takeHoliday(UserId owner, KindOfHoliday kind, double days, Date date,
			HolidayApplicationId applicationId) {
		HolidayList holidayList = repository.findById(new HolidayListId(owner.getValue().toString()));
		holidayList.take(kind, days, date, applicationId);
		repository.save(holidayList);
		return holidayList;
	}

	/**
	 *  休暇リスト取得
	 * @param owner ユーザーID
	 * @return 休暇リスト
	 */
	public HolidayList findHolidayList(UserId owner) {
		return  repository.findById(new HolidayListId(owner.getValue().toString()));
	}
}
