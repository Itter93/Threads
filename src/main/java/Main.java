

public class Main {
    public static void main(String[] args) {
        ThreadGroup mainGroup = new ThreadGroup("main group");

        String[] treadsNames = new String[] {"1", "2", "3", "4"};

        System.out.println("Создаю потоки...");
        for (String name: treadsNames) {
            new MyThread(mainGroup, name).start();
        }
        try{
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Завершаю все потоки");
        mainGroup.interrupt();
    }
}
