public class ThreadOne extends Thread{
    NonSynchronizedMethod nonSynchronizedMethod;

    public ThreadOne(NonSynchronizedMethod nonSynchronizedMethod) {
        this.nonSynchronizedMethod = nonSynchronizedMethod;
    }

    @Override
    public void run() {
        nonSynchronizedMethod.printNumbers3();
    }
}
