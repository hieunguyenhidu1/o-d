public class ThreadTwo extends Thread{
    NonSynchronizedMethod nonSynchronizedMethod;

    public ThreadTwo(NonSynchronizedMethod nonSynchronizedMethod) {
        this.nonSynchronizedMethod = nonSynchronizedMethod;
    }

    @Override
    public void run() {
        nonSynchronizedMethod.printNumbers3();
    }
}
