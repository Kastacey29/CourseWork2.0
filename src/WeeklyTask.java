import java.time.LocalDate;

public class WeeklyTask extends Task {

    public WeeklyTask(String name, String description, TypeOfTask typeOfTask, LocalDate localDate) throws NullArgumentException {
        super(name, description, typeOfTask, localDate);
    }

    @Override
    public boolean isAvailable(LocalDate localDate) {
        LocalDate startDate = getLocalDate();
        while (startDate.isAfter(localDate)) {
            if (startDate.isEqual(localDate)) {
                return true;
            }
            startDate = startDate.plusWeeks(1);
        }
        return false;
    }
}
