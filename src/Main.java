public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("哈哈哈哈哈哈哈我是线程");
        },"zkw").start();
    }
}
