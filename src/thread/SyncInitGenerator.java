package thread;

/**
 * Created by ning on 2017/5/1.
 */
public abstract class SyncInitGenerator {
    private volatile boolean cancaled  = false;
    public abstract int next();

    public boolean isCancaled() {
        return cancaled;
    }

    public void setCancaled(boolean cancaled) {
        this.cancaled = cancaled;
    }

    public void cancel(){
        cancaled = true;
    }
}
