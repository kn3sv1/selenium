import java.time.DayOfWeek;
import java.util.List;

public class DailyRoutineService {
    private DailyRoutineRepository dailyRoutineRepository;

    public DailyRoutineService(DailyRoutineRepository dailyRoutineRepository) {
        this.dailyRoutineRepository = dailyRoutineRepository;
    }

    public List<DailyRoutine> getDailyRoutines(DayOfWeek day) {
        return this.dailyRoutineRepository.findTasksByDay(day);
    }

    public void getAmountOfTimes(List<DailyRoutine> dailyRoutines) {
        this.dailyRoutineRepository.calculateTimes(dailyRoutines);
    }
}
