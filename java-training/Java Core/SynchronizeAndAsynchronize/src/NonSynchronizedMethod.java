public class NonSynchronizedMethod {
    public void printNumbers() {
        System.out.println("Starting to print Numbers for " + Thread.currentThread().getName());

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }

        System.out.println("Completed printing Numbers for " + Thread.currentThread().getName());
    }

    public synchronized void printNumbers2() {
        System.out.println("Starting to print Numbers for " + Thread.currentThread().getName());

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }

        System.out.println("Completed printing Numbers for " + Thread.currentThread().getName());
    }


    //blok sync
    public void printNumbers3() {

        System.out.println("Starting to print Numbers for " + Thread.currentThread().getName());

        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }

        System.out.println("Completed printing Numbers for " + Thread.currentThread().getName());
    }
}
