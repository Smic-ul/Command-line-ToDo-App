public class Main {
    public static void main(String[] args) {
        if(args.length > 2){
            System.out.println("Too many arguments!");
            return;
        }

        TodoList list = new TodoList();
        switch (args[0]) {
            case "-l" -> {
                System.out.println("List of tasks:\n");
                System.out.println(list.getTodoListAsString());
            }
            case "-a" -> {
                String task = args[1];
                if(list.addRecord(task)) {
                    System.out.println("Task added successfully");
                } else {
                    System.out.println("Task with the given name already exists");
                }
            }
            case "-r" -> {
                String task = args[1];
                if (list.deleteRecord(task)) {
                    System.out.println("Task deleted successfully");
                } else {
                    System.out.println("Task with the given name doesn't exists");
                }
            }
            case "-c" -> {
                String task = args[1];
                if (list.completeTask(task)) {
                    System.out.println("Task marked as completed");
                } else {
                    System.out.println("Task with the given name doesn't exists");
                }
            }

            default -> System.out.println("No valid option!");
        }
    }
}
