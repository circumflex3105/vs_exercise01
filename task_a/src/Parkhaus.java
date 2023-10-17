import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parkhaus {
    private Lock monitor = new ReentrantLock();
    private Condition parkhausNotFull = monitor.newCondition();
    private int capacity;
    private int currentCounter;
    public Parkhaus(int capacity) {
        this.capacity = capacity;
        currentCounter = 0;
    }

    public void park() {
        monitor.lock();
        try {
            while(currentCounter >= capacity) {
                parkhausNotFull.await();
            }
            currentCounter++;
            if(currentCounter < capacity) {
                parkhausNotFull.signal();
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            monitor.unlock();
        }
    }

    public void leave() {
        monitor.lock();
        currentCounter--;
        parkhausNotFull.signal();
        monitor.unlock();
    }
}
