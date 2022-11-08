import java.time.LocalDate;
import java.util.*;

public class TaskService {

    private Map<Integer, Task> tasks;

    public Map<Integer, Task> getTasks() {
        return tasks;
    }


    public TaskService(Map<Integer, Task> tasks) {
        this.tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void removeTask(int id) {
        tasks.remove(id);
    }

    public List<Task> getTasksByDate(LocalDate localDate) {
        List<Task> list = new ArrayList<>();
        for (Map.Entry<Integer, Task> integerTaskEntry : tasks.entrySet()) {
            Task task = integerTaskEntry.getValue();
            if (task.isAvailable(localDate)) {
                list.add(task);
            }
        }
        return list;
    }
}
