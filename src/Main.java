import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Task> tasks = new HashMap<>();
        TaskService taskService = new TaskService(tasks);

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask(taskService, scanner);
                            System.out.println("Задача добавлена!");
                            System.out.println(taskService.getTasks());
                            break;
                        case 2:
                            removeTask(taskService, scanner, taskService.getTasks());
                            System.out.println("Задача удалена!");
                            System.out.println(taskService.getTasks());
                            break;
                        case 3:
                            getTasksByDate(taskService, scanner, taskService.getTasks());
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");

                }

            }
        }
    }

    private static void addTask(TaskService taskService, Scanner scanner) {
        System.out.println("Введите название задачи:");
        String name = scanner.next();
        scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();
        System.out.println("Введите дату задачи: ");
        String date = scanner.nextLine();
        LocalDate taskDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.println("Введите тип задачи: 1. Личная или 2. Рабочая");
        int type = scanner.nextInt();
        TypeOfTask taskType = type == 1 ? TypeOfTask.PERSONAL : TypeOfTask.WORK;
        System.out.println("Введите повторяемость задачи: 0 - не повторяется;");
        System.out.println("1 - ежедневно;");
        System.out.println("2 - еженедельно;");
        System.out.println("3 - ежемесячно;");
        System.out.println("4 - ежегодно.");
        int typeTask = scanner.nextInt();
        try {
            switch (typeTask) {
                case 0:
                    taskService.addTask(new Task(name, description, taskType, taskDate));
                    break;
                case 1:
                    taskService.addTask(new DailyTask(name, description, taskType, taskDate));
                    break;
                case 2:
                    taskService.addTask(new WeeklyTask(name, description, taskType, taskDate));
                    break;
                case 3:
                    taskService.addTask(new MonthlyTask(name, description, taskType, taskDate));
                    break;
                case 4:
                    taskService.addTask(new YearlyTask(name, description, taskType, taskDate));
                    break;
                default:
                    throw new RuntimeException("Введите другой тип проверяемости: ");
            }
        } catch (NullArgumentException e) {
            System.out.println("Заполните поле!");
        }
    }


    private static void removeTask(TaskService taskService, Scanner scanner, Map<Integer, Task> tasks) {
        System.out.println("Выберите задачу для удаления:");
        for (Map.Entry<Integer, Task> task : tasks.entrySet()) {
            System.out.println(task.getKey() + "   " + task.getValue().getName());
        }
        int id = scanner.nextInt();
        taskService.removeTask(id);
    }

    private static void getTasksByDate(TaskService taskService, Scanner scanner, Map<Integer, Task> tasks) {
        System.out.println("Введите дату задачи: ");
        scanner.nextLine();
        String date = scanner.nextLine();
        LocalDate taskDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Collection<Task> allTasksByDate = taskService.getTasksByDate(taskDate);
        for (Task task : allTasksByDate) {
            System.out.println(task);
        }
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("0. Выход");

    }
}