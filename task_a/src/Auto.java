import java.util.Random;

public class Auto implements Runnable {
    private String licensePlate;
    private Parkhaus parkhaus;
    private boolean isParked;
    public Auto(String licensePlate, Parkhaus parkhaus) {
        this.licensePlate = licensePlate;
        this.parkhaus = parkhaus;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Thread.sleep(new Random().nextInt(10000));
                if(isParked) {
                    parkhaus.leave();
                } else {
                    parkhaus.park();
                }
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
