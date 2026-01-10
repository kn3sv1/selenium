import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorAppointmentService {
    private DoctorAppointmentRepository doctorAppointmentRepository;
    private AbsentDateRepository absentDateRepository;

    public DoctorAppointmentService(
        DoctorAppointmentRepository doctorAppointmentRepository,
        AbsentDateRepository absentDateRepository
    ) {
        this.doctorAppointmentRepository = doctorAppointmentRepository;
        this.absentDateRepository = absentDateRepository;
    }

    public List<DoctorLocalDate> getAvailableDatesByDoctorId(UUID doctorId) {
        //throw new  UnsupportedOperationException("Not implemented yet");
        // dates should be from today to next 30 days.
        // check doctor's schedule and absent dates to find available dates.

        // step 1:get list of dates from today to next 30 days
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(30);
        List<LocalDate> dateRange = generateDateRange(today, endDate);
        //System.out.println("Generated date range: " + dateRange);

        // step 2: get doctor's absent dates from repository
        List<AbsentDate> absentDates = this.absentDateRepository.findByDoctorId(doctorId);
        System.out.println("Doctor's absent dates: " + absentDates);

        // step 3: filter out absent dates from dateRange
        List<DoctorLocalDate> resultRangeDates = getDoctorLocalDate(dateRange, absentDates);

        // step 4: check doctor's schedule to mark available dates
        // For simplicity, we assume that if a date is not absent, it is available.
        // In a real-world scenario, we would check the doctor's working hours and existing appointments.
        resultRangeDates = markAvailableDatesBasedOnSchedule(doctorId, resultRangeDates);

        // step 5: return all dates with availability info

        return resultRangeDates;
    }

    public List<LocalTime> getAvailableTimesByDoctorIdAndDate(UUID doctorId, LocalDate date) {
        throw new  UnsupportedOperationException("Not implemented yet");
    }

    public void bookAppointment(UUID doctorId, UUID userId, LocalDate date, LocalTime time) {
        //if impossible throw exception
    }

    public void cancelAppointment(UUID doctorId, UUID userId, LocalDate date, LocalTime time) {
        //if impossible throw exception
    }

    public void rescheduleAppointment(UUID doctorId, UUID userId, LocalDate oldDate, LocalTime oldTime, LocalDate newDate, LocalTime newTime) {
        //if impossible throw exception
    }
    private List<LocalDate> generateDateRange(LocalDate startDate, LocalDate endDate) {
        //throw new UnsupportedOperationException("Not implemented yet");
        return startDate.datesUntil(endDate.plusDays(1)).toList();
    }

    private List<DoctorLocalDate> getDoctorLocalDate(List<LocalDate> dateRange, List<AbsentDate> absentDates) {
        List<DoctorLocalDate> resultRangeDates = new ArrayList<>();
        for(LocalDate date : dateRange) {
            // flag to check if date is absent
            AbsentDate absent = null;
            for(AbsentDate absentDate : absentDates) {
                if (absentDate.getAbsentDate().equals(date)) {
                    absent = absentDate;
                    break;
                }
            }
            Boolean isAvailable = absent == null;
            // reason = null better but more work to check needed.
            String reason = absent != null ? absent.getReason() : "";
            resultRangeDates.add(new DoctorLocalDate(date, isAvailable, reason));
        }
        return resultRangeDates;
    }

    private List<DoctorLocalDate> markAvailableDatesBasedOnSchedule(UUID doctorId, List<DoctorLocalDate> dateList) {
        // For simplicity, we assume that if a date is not absent, it is available.
        // In a real-world scenario, we would check the doctor's working hours and existing appointments.



        return dateList;
    }
}
