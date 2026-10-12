package signal;

public class RedSignal implements TrafficSignalState{
    @Override
    public void action(TrafficSignal signal) {
        signal.setSignalState(new GreenSignal());
    }
}
