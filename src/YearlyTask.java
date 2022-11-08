import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(String name, String description, TypeOfTask typeOfTask, LocalDate localDate) throws NullArgumentException {
        super(name, description, typeOfTask, localDate);
    }

    @Override
    public boolean isAvailable(LocalDate localDate) {
        LocalDate startDate = getLocalDate();
        while (startDate.isAfter(localDate)) {
            if (startDate.isEqual(localDate)) {
                return true;
            }
            startDate = startDate.plusYears(1);
        }
        return false;
    }
}
