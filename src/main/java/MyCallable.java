import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private String name;
    private int msg_count = 4;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;

        while (count < msg_count) {
            Thread.sleep(2000);
            System.out.printf("Я %s. Всем привет!\n", name);
            count++;
        }

        return count;
    }
}
