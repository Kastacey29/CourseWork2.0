import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Repeatable {
    private String name;
    private String description;
    private TypeOfTask typeOfTask;
    private LocalDate localDate;
    private static int counter;
    private int id;

    public Task(String name, String description, TypeOfTask typeOfTask, LocalDate localDate) throws NullArgumentException {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new NullArgumentException();
        } else {
            this.name = name;
        }
        ;
        if (description == null || description.isEmpty() || name.isBlank()) {
            throw new NullArgumentException();
        } else {

            this.description = description;
        }
        this.typeOfTask = typeOfTask;
        this.localDate = localDate;
        this.id = counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public void setTypeOfTask(TypeOfTask typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean isAvailable(LocalDate localDate) {
        return localDate.isEqual(getLocalDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id && name.equals(task.name) && description.equals(task.description) && typeOfTask == task.typeOfTask && localDate.equals(task.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, typeOfTask, localDate, id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", typeOfTask=" + typeOfTask +
                ", localDate=" + localDate +
                ", id=" + id +
                '}';
    }
}
