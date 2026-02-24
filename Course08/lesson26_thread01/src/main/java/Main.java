import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //example01();
        unit7();
    }

    public static void unit7() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread: " + Thread.currentThread().getName() + ", ID: " + Thread.currentThread().threadId());
            }
        };

        System.out.println("Main Thread: " + Thread.currentThread().getName() + ", ID: " + Thread.currentThread().threadId());

        var t = new Thread(r);
        t.start();

        long pid = ProcessHandle.current().pid();
        System.out.println("Process ID:" + pid);


//        try {
//            System.out.print("Press any key to continue . . . ");
//            System.in.read();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void example01() {
        // This will execute the task in a new thread = asynchronously, in background.
        // no waiting time, all tasks will start immediately, and they will run in parallel.
        for (int i = 0; i < 100; i++) {
            // we need to create a final variable to use it inside the lambda expression,
            // because the variable i is changing in each iteration of the loop, and we want to capture the value of i at the moment of creating the task.
            int id = i; // means new variable id is created in each iteration of the loop, and it will have the value of i at that moment.
            Runnable task = () -> {
                // will be executed for each task independently.
                Main.backgroundTask(id);
            };
            // start() - will execute the task in a new thread = asynchronously, in background
            // start() - is like Machi gives order to Angie to do task.
            new Thread(task).start();
        }


        // This will execute the task in the main thread = synchronously, in foreground.
        // like in bank, my waiting time is up to 4 seconds.
        // minimum 1 second * 100 tasks = 100 seconds = almost 2 minutes (120 seconds).
//        for (int i = 0; i < 100; i++) {
//            Main.backgroundTask(i);
//        }
    }

    public static void backgroundTask(int taskNumber) {
        try {
            System.out.println("Hello from thread before sleep. TaskID: " + taskNumber + " Thread: " + Thread.currentThread().getName());
            Thread.sleep(1000 + (long) (Math.random() * 4000));
            System.out.println("Hello from thread after sleep. TaskID: " + taskNumber + " Thread: "  + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
