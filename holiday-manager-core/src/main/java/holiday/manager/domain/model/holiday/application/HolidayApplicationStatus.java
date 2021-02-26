package holiday.manager.domain.model.holiday.application;

public enum HolidayApplicationStatus {
	/** 申請中 */
	APPLYING,
	/** 承認 */
	APPROVED,
	/** 却下 */
	REJECTED,
	/** 取り消し */
	CANCELED,
	/** 処理済み */
	PROCESSED,
	/** 処理失敗 */
	PROCESS_FAILED
}
