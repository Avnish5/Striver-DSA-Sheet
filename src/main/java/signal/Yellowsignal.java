package signal;

public class Yellowsignal implements TrafficSignalState{
    @Override
    public void action(TrafficSignal signal) {
        signal.setSignalState(new RedSignal());
    }
}
