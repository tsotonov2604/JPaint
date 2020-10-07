package model.interfaces;

public interface IObservable {
    void addObserver(IObserver observer);

    void notifyUpdate();

    void clearObserver();
}
