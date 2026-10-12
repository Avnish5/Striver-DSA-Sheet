package signal;

public class TrafficSignal {

    public TrafficSignalState signalState;

    public TrafficSignal() {
        this.signalState = new RedSignal();
    }

    public void setSignalState(TrafficSignalState signalState) {
        this.signalState = signalState;
    }

    public void action() {
        signalState.action(this);
    }
}
