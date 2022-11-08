import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {

    public DailyTask(String name, String description, TypeOfTask typeOfTask, LocalDate localDate) throws NullArgumentException {
        super(name, description, typeOfTask, localDate);
    }

    @Override
    public boolean isAvailable(LocalDate localDate) {
        LocalDate startDate = getLocalDate();
        while (startDate.isAfter(localDate)) {
            if (startDate.isEqual(localDate)) {
                return true;
            }
            startDate = startDate.plusDays(1);
        }
        return false;
    }
}
