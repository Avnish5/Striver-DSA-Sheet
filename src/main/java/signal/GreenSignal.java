package signal;

public class GreenSignal implements TrafficSignalState{
    @Override
    public void action(TrafficSignal signal) {
        signal.setSignalState(new Yellowsignal());
    }
}
