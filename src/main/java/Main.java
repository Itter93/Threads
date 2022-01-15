import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        String[] treadsNames = new String[]{"1", "2", "3", "4"};
        List<MyCallable> myCallables = new ArrayList<>();
        System.out.println("Создаю потоки...");

        for (String name : treadsNames) {
            myCallables.add(new MyCallable("Поток " + name));
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Integer>> futures = new ArrayList<>();

        for (MyCallable myCallable : myCallables) {
            futures.add(threadPool.submit(myCallable));
        }
        int count = 0;
        for (Future<Integer> future : futures) {
            try {
                count += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Потоки выполнились %s раз\n", count);
        System.out.println();


        int result = 0;
        try {
            result = threadPool.invokeAny(myCallables);
            System.out.println("Результат самой быстрой задачи " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Завершаю все потоки...");
        threadPool.shutdown();


    }
}
