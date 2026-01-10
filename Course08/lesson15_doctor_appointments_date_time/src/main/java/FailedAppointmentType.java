public class FailedAppointmentType {
    public static final String REASON_TYPE_PERSONAL = "personal";
    public static final String REASON_TYPE_SICK = "sick";
    public static final String REASON_TYPE_VOCATION = "vocation";
    public static final String REASON_TYPE_PUBLIC_HOLIDAY = "public_holiday";
    public static final String REASON_TYPE_BIRTHDAY = "birthday";
    // can be for days - dropdown list. Hours dropdown list we will not show at all.
    public static final String REASON_TYPE_FULLY_BOOKED = "fully_booked";
    // used for list of hours. Java LocalTime list of available hours.
    public static final String REASON_TYPE_NOT_WORKING_HOURS = "not_working_hours";
}
