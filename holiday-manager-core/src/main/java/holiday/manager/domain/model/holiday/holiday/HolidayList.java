package holiday.manager.domain.model.holiday.holiday;

import holiday.manager.domain.model.AggregateRoot;
import holiday.manager.domain.model.DomainEvent;
import holiday.manager.domain.model.holiday.KindOfHoliday;
import holiday.manager.domain.model.holiday.application.HolidayApplicationId;
import holiday.manager.domain.model.holiday.holiday.event.HolidayGranted;
import holiday.manager.domain.model.holiday.holiday.event.HolidayListCreated;
import holiday.manager.domain.model.holiday.holiday.event.HolidayTook;
import holiday.manager.domain.model.holiday.holiday.event.TakeHolidayCanceled;
import holiday.manager.domain.model.user.UserId;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 休暇リスト
 */
public class HolidayList extends AggregateRoot {
    private HolidayListId id;
    private List<Holiday> holidays;
    private List<Absence> absences;
    private UserId owner;
    private List<DomainEvent> histories;

    public HolidayList(List<DomainEvent> eventStream, int streamVersion) {
        super(eventStream, streamVersion);
    }

    public HolidayList(UserId owner) {
        HolidayListId id = new HolidayListId(owner.getValue().toString());
        apply(new HolidayListCreated(id, owner));
    }

    /**
     * 休暇付与
     *
     * @param kind
     * @param days
     * @param grantedDate
     * @param expirationDate
     * @return
     */
    public HolidayList grant(KindOfHoliday kind, double days, Date grantedDate, Date expirationDate) {
        HolidayGranted event = new HolidayGranted(id, kind, days, grantedDate, expirationDate, owner);
        apply(event);

        return this;
    }

    /**
     * 休暇取得
     *
     * @param kind
     * @param days
     * @param date
     * @param applicationId
     * @return
     */
    public HolidayList take(KindOfHoliday kind, double days, Date date, HolidayApplicationId applicationId) {
        if (kind != KindOfHoliday.ABSENCE && getDays(kind, date) < days) {
            throw new IllegalStateException("There are not enough holidays.");
        }
        HolidayTook event = new HolidayTook(id, kind, date, days, applicationId, owner);
        apply(event);

        return this;
    }

    public HolidayList cancelTakeHoliday(String eventId) {
        TakeHolidayCanceled event = new TakeHolidayCanceled(id, eventId);
        apply(event);

        return this;
    }

    protected void on(HolidayListCreated event) {
        this.id = event.getId();
        this.holidays = new ArrayList<>();
        this.absences = new ArrayList<>();
        this.owner = event.getOwner();
        if(histories == null){
            histories = new ArrayList<>();
        }
        histories.add(event);
    }

    protected void on(HolidayGranted event) {
        Holiday holiday = new Holiday(new HolidayId(), event.getKind(), event.getDays(), event.getGrantedDate(),
                event.getExpirationDate(), event.getOwner());
        this.holidays.add(holiday);
        if(histories == null){
            histories = new ArrayList<>();
        }
        histories.add(event);
    }

    protected void on(HolidayTook event) {
        if(histories == null){
            histories = new ArrayList<>();
        }
        histories.add(event);
        if (event.getKind() == KindOfHoliday.ABSENCE) {
            Absence absence = new Absence(event.getDays(), event.getDate(), owner);
            absences.add(absence);
            return;
        }
        List<Holiday> list = holidays.stream()
                .filter(holiday -> holiday.getKind() == event.getKind())
                .filter(holiday -> holiday.getDays() > 0)
                .filter(holiday -> holiday.getExpirationDate().after(event.getDate()))
                .sorted(Comparator.comparing(Holiday::getExpirationDate))
                .collect(Collectors.toList());

        double days = event.getDays();
        for (Holiday holiday : list) {
            if (days == 0) {
                return;
            }
            days = holiday.take(days);
        }
    }

    protected void on(TakeHolidayCanceled event) {
        //キャンセルされたイベントを除いて再生
        Set<String> targetEventIds = histories.stream()
                .filter(e -> e instanceof TakeHolidayCanceled)
                .map(e -> ((TakeHolidayCanceled) e).getTargetEventId())
                .collect(Collectors.toSet());
        targetEventIds.add(event.getTargetEventId());
        List<DomainEvent> eventStream = histories.stream()
                .filter(e -> !(e instanceof HolidayTook) || !targetEventIds.contains(((HolidayTook) e).getEventId()))
                .collect(Collectors.toList());
        HolidayList holidayList = new HolidayList(eventStream, 0);

        this.holidays.clear();
        this.absences.clear();
        this.holidays.addAll(holidayList.holidays);
        this.absences.addAll(holidayList.absences);
        if(histories == null){
            histories = new ArrayList<>();
        }
        histories.add(event);
    }

    public HolidayListId getId() {
        return id;
    }

    public UserId getOwner() {
        return owner;
    }

    /**
     * 現在の休暇
     *
     * @return
     */
    public List<Holiday> getHolidays() {
        return Collections.unmodifiableList(holidays);
    }

    /**
     * 欠勤
     *
     * @return
     */
    public List<Absence> getAbsences() {
        return Collections.unmodifiableList(absences);
    }

    /**
     * 休暇残日数
     *
     * @param kind
     * @param date
     * @return
     */
    public double getDays(KindOfHoliday kind, Date date) {
        if (holidays == null)
            return 0;
        return holidays.stream()
                .filter(holiday -> holiday.getKind() == kind)
                .filter(holiday -> holiday.getExpirationDate().after(date))
                .mapToDouble(holiday -> holiday.getDays())
                .sum();
    }

    /**
     * 休暇付与履歴
     *
     * @return
     */
    public List<HolidayGranted> grantHistory() {
        return histories.stream()
                .filter(event -> event instanceof HolidayGranted)
                .map(event -> (HolidayGranted) event)
                .collect(Collectors.toList());
    }

    /**
     * 休暇取得履歴
     *
     * @return
     */
    public List<HolidayTook> takeHistory() {
        return histories.stream()
                .filter(event -> event instanceof HolidayTook)
                .map(event -> (HolidayTook) event)
                .collect(Collectors.toList());
    }
}
