import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DoctorAppointmentService {
    private DoctorAppointmentRepository doctorAppointmentRepository;
    private AbsentDateRepository absentDateRepository;
    private DoctorScheduleRepository doctorScheduleRepository;

    public DoctorAppointmentService(
        DoctorAppointmentRepository doctorAppointmentRepository,
        AbsentDateRepository absentDateRepository,
        DoctorScheduleRepository doctorScheduleRepository
    ) {
        this.doctorAppointmentRepository = doctorAppointmentRepository;
        this.absentDateRepository = absentDateRepository;
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    public List<AppointmentDateTime> getAvailableDatesByDoctorId(UUID doctorId) {
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
        List<AppointmentDateTime> resultRangeDates = getDoctorLocalDate(dateRange, absentDates);

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
        List<AppointmentDateTime> getAvailableDates = this.getAvailableDatesByDoctorId(doctorId);
        for(AppointmentDateTime appointmentDateTime : getAvailableDates) {
            if(appointmentDateTime.getDate().equals(date)) {
                if(!appointmentDateTime.getTimes().contains(time)) {
                    throw new IllegalArgumentException("The selected time is not available for booking.");
                } else {
                    // proceed to book the appointment
                    DoctorAppointment newAppointment = new DoctorAppointment(
                            UUID.randomUUID(),
                            doctorId,
                            userId,
                            LocalDateTime.of(date, time)
                    );
                    this.doctorAppointmentRepository.create(newAppointment);
                    System.out.println("Appointment booked successfully: " + newAppointment);
                    return;
                }
            }
        }
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

    private List<AppointmentDateTime> getDoctorLocalDate(List<LocalDate> dateRange, List<AbsentDate> absentDates) {
        List<AppointmentDateTime> resultRangeDates = new ArrayList<>();
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
            resultRangeDates.add(new AppointmentDateTime(date, isAvailable, reason));
        }
        return resultRangeDates;
    }

    /**
     * Mark available dates based on doctor's schedule and fill times property in AppointmentDateTime object.
     * because we need this property to show available times for a date.
     * mark available dates means to take into account existing appointments for specific date and time.
     */
    private List<AppointmentDateTime> markAvailableDatesBasedOnSchedule(UUID doctorId, List<AppointmentDateTime> dateList) {
        // For simplicity, we assume that if a date is not absent, it is available.
        // In a real-world scenario, we would check the doctor's working hours and existing appointments.

        // step 1: get doctor's schedule(available time Arraylist for particular date) from DoctorScheduleRepository.
        // and fill the times in AppointmentDateTime object.

        // get doctor's schedule for the day of week
        HashMap<DayOfWeek, DoctorSchedule> schedule = this.doctorScheduleRepository.findScheduleByDoctorId(doctorId);

        // we have 30 days already inside dateList.
        for(AppointmentDateTime appointmentDateTime : dateList) {
            // get day of week
            DayOfWeek dayOfWeek = appointmentDateTime.getDate().getDayOfWeek();
            // get doctor's schedule for current date - appointmentDateTime. can be null - DoctorSchedule, be careful!
            DoctorSchedule doctorSchedule = schedule.get(dayOfWeek);
            if (doctorSchedule != null) {
                List<LocalTime> workingHours = doctorSchedule.getSchedule();
                System.out.println("Doctor's working hours for " + dayOfWeek + ": " + workingHours);
                //System.exit(0); // temporary exit to check working hours

                // from these working hours we need to exclude already booked appointments for that date.
                List<DoctorAppointment> existingAppointments = this.doctorAppointmentRepository.findByDateAndDoctorId(doctorId, appointmentDateTime.getDate());
                System.out.println("Existing appointments for " + appointmentDateTime.getDate() + ": " + existingAppointments);
                for(DoctorAppointment appointment : existingAppointments) {
                    for (LocalTime time : workingHours) {
                        if (time.equals(appointment.getTime())) {
                            System.out.println("Removing booked time: " + time);
                            workingHours.remove(time);
                            break; // break inner loop to avoid ConcurrentModificationException
                        }
                    }
                }
                System.out.println("Available working hours after excluding existing appointments for " + appointmentDateTime.getDate() + ": " + workingHours);


                //System.exit(0);

                // fill times property in AppointmentDateTime object
                appointmentDateTime.setTimes(workingHours);
                // if no working hours left, mark as not available
                if (workingHours.isEmpty()) {
                    appointmentDateTime.setAvailable(false);
                    appointmentDateTime.setReason(FailedAppointmentType.REASON_TYPE_FULLY_BOOKED);
                }
            } else {
                // no schedule for this day, so no available times
                appointmentDateTime.setTimes(new ArrayList<>());
                appointmentDateTime.setAvailable(false);
                appointmentDateTime.setReason(FailedAppointmentType.REASON_TYPE_NOT_WORKING_HOURS);
            }

        }


        return dateList;
    }
}
