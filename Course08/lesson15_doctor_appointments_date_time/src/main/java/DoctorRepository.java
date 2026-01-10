import java.time.DayOfWeek;
import java.util.*;

public class DoctorRepository {

    private final List<Doctor> entities = new ArrayList<>();
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final AbsentDateRepository absentDateRepository;

    public DoctorRepository(
            DoctorAppointmentRepository doctorAppointmentRepository,
            DoctorScheduleRepository doctorScheduleRepository,
            AbsentDateRepository absentDateRepository
    ) {
        this.doctorAppointmentRepository = doctorAppointmentRepository;
        this.doctorScheduleRepository = doctorScheduleRepository;
        this.absentDateRepository = absentDateRepository;
        this.populate();
    }

    public List<Doctor> findAll() {
        return this.entities;
    }


    private void populate() {

//        Map<DayOfWeek, DoctorSchedule> schedule1 = new HashMap<>();
//        schedule1.put(DayOfWeek.MONDAY, new DoctorSchedule(
//            java.time.LocalTime.of(8, 0),
//            java.time.LocalTime.of(20, 0),
//            java.time.LocalTime.of(13, 0),
//            java.time.LocalTime.of(14, 0)
//        ));
//        List<DoctorAppointment> appointments1 = new ArrayList<>();
//        this.entities.add(
//            new Doctor(
//                Doctor.UUID_1,
//                "Andreas",
//                "Pantazis",
//                30,
//                schedule1,
//                appointments1
//            )
//        );
//        Map<DayOfWeek, DoctorSchedule> schedule2 = new HashMap<>();
//        schedule1.put(DayOfWeek.MONDAY, new DoctorSchedule(
//                java.time.LocalTime.of(8, 0),
//                java.time.LocalTime.of(20, 0),
//                java.time.LocalTime.of(13, 0),
//                java.time.LocalTime.of(14, 0)
//        ));
//        List<DoctorAppointment> appointments2 = new ArrayList<>();
//        this.entities.add(
//                new Doctor(
//                        Doctor.UUID_2,
//                        "Andros",
//                        "Charalambous",
//                        25,
//                        schedule2,
//                        appointments2
//                )
//        );
        // one line style without extra variables: schedule3, appointments3
//        this.entities.add(
//                new Doctor(
//                        Doctor.UUID_3,
//                        "Natasa",
//                        "Charalambous",
//                        28,
//                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_3),
//                        // refactor to get record from repository
////                        Map.ofEntries(
////                            Map.entry(
////                                    DayOfWeek.MONDAY,
////                                    new DoctorSchedule(
////                                    java.time.LocalTime.of(8, 0),
////                                    java.time.LocalTime.of(20, 0),
////                                    java.time.LocalTime.of(13, 0),
////                                    java.time.LocalTime.of(14, 0)
////                            )),
////                            Map.entry(
////                                    DayOfWeek.TUESDAY,
////                                    new DoctorSchedule(
////                                            java.time.LocalTime.of(8, 0),
////                                            java.time.LocalTime.of(20, 0),
////                                            java.time.LocalTime.of(13, 0),
////                                            java.time.LocalTime.of(14, 0)
////                                    ))
////                        ),
//                        // populate appointments from repository - to minimize amount of hardcoded data
//                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_3)
//                        // refactor to get record from repository
////                        new ArrayList<>(List.of(
////                                new DoctorAppointment(DoctorAppointment.UUID_1, Doctor.UUID_3, User.UUID_1, LocalDateTime.of(2026, 1, 10, 15, 0))
////                        ))
//                )
//        );
        //this.entities.add(new Doctor(Doctor.UUID_3, "Natasa", "Charalambous", 28));

        this.entities.add(
                new Doctor(
                        Doctor.UUID_1,
                        "Andreas",
                        "Pantazis",
                        65,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_1),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_1),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_1)
                )
        );
        this.entities.add(
                new Doctor(
                        Doctor.UUID_2,
                        "Andros",
                        "Charalambous",
                        60,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_2),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_2),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_2)
                )
        );
        this.entities.add(
                new Doctor(
                        Doctor.UUID_3,
                        "Natasa",
                        "Charalambous",
                        43,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_3),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_3),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_3)
                )
        );

        //this.entities.add(new Doctor(Doctor.UUID_4, "George", "Ioannides", 35));
        this.entities.add(
                new Doctor(
                        Doctor.UUID_4,
                        "George",
                        "Charalambous",
                        60,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_4),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_4),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_4)
                )
        );
        // fix
        //this.entities.add(new Doctor(Doctor.UUID_5, "George", "Pashas", 22));
        this.entities.add(
                new Doctor(
                        Doctor.UUID_5,
                        "George",
                        "Pashas",
                        52,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_5),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_5),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_5)
                )
        );
        //this.entities.add(new Doctor(Doctor.UUID_6, "Tatiana", "Florenzou", 27));
        this.entities.add(
                new Doctor(
                        Doctor.UUID_6,
                        "Tatiana",
                        "Florenzou",
                        52,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_6),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_6),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_6)
                )
        );

        //this.entities.add(new Doctor(Doctor.UUID_7, "Andreas", "Ioannou", 33));
        this.entities.add(
                new Doctor(
                        Doctor.UUID_7,
                        "Andreas",
                        "Ioannou",
                        65,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_7),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_7),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_7)
                )
        );

        //this.entities.add(new Doctor(Doctor.UUID_8, "Stasinos", "Theodorou", 34));
        this.entities.add(
                new Doctor(
                        Doctor.UUID_8,
                        "Stasinos",
                        "Theodorou",
                        41,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_8),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_8),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_8)
                )
        );

        //this.entities.add(new Doctor(Doctor.UUID_9, "Christina", "Stefanou", 29));
        this.entities.add(
                new Doctor(
                        Doctor.UUID_9,
                        "Christina",
                        "Stefanou",
                        30,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_9),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_9),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_9)
                )
        );

        //this.entities.add(new Doctor(Doctor.UUID_10, "Tatiana", "Polycarpou", 26));
        this.entities.add(
                new Doctor(
                        Doctor.UUID_10,
                        "Tatiana",
                        "Polycarpou",
                        50,
                        this.doctorScheduleRepository.findScheduleByDoctorId(Doctor.UUID_10),
                        this.doctorAppointmentRepository.findByDoctorId(Doctor.UUID_10),
                        this.absentDateRepository.findByDoctorId(Doctor.UUID_10)
                )
        );
    }

    public Doctor findById(UUID id) {
        //throw new UnsupportedOperationException("Not implemented yet");
        for(Doctor e : this.entities) {
            if(e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }
}
