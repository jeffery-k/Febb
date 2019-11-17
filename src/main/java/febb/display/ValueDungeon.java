package febb.display;

//Why does this class exist you ask? Your guess is as good as mine
public class ValueDungeon<T> {
    private T value;

    public ValueDungeon() {
        this.value = null;
    }

    public synchronized T harvestValue() {
        T returnValue;
        try {
            this.wait();
            returnValue = value;
            this.value = null;
        } catch (InterruptedException exception) {
            return null;
        }
        return returnValue;
    }

    public synchronized void loadValue(T value) {
        this.value = value;
        this.notify();
    }
}
