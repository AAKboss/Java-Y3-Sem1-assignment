class Task {
    private String taskName;
    private int taskId;
    private int taskWage;

    // Constructor
    public Task(String taskName, int taskId, int taskWage) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.taskWage = taskWage;
    }

    // Method to display
    public void displayTaskDetails() {
        System.out.println("Task Name: " + taskName);
        System.out.println("Task ID: " + taskId);
        System.out.println("Task Wage: " + taskWage);
    }

    // Method to display with additional information
    public void displayTaskDetailsWithInfo() {
        displayTaskDetails(); // Reuse the basic display
        System.out.println("Additional Information: This task is important.");
    }
}

public class NewFile {
    public static void main(String[] args) {
        // Create Task objects
        Task task1 = new Task("Coding", 1, 420);
        Task task2 = new Task("Testing", 2, 69);

        // Create threads
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(2500);
                System.out.println("Thread 1 priority: " + Thread.currentThread().getPriority());
                System.out.println("Thread 1 executing...");
                task1.displayTaskDetails();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 status: " + Thread.currentThread().getState());
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Thread 2 priority: " + Thread.currentThread().getPriority());
                System.out.println("Thread 2 executing...");
                task2.displayTaskDetailsWithInfo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2 status: " + Thread.currentThread().getState());
        });

        // Start the threads
        thread1.setName("WorkerThread1");
        thread2.setName("WorkerThread2");

        thread1.setPriority(Thread.MIN_PRIORITY); // Set minimum priority (1)
        thread2.setPriority(Thread.MAX_PRIORITY); // Set maximum priority (10)

        thread1.start();
        thread2.start();

        // Wait for threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display final status of threads
        System.out.println(thread1.getName() + " - Final status: " + thread1.getState());
        System.out.println(thread2.getName() + " - Final status: " + thread2.getState());
    }
}
