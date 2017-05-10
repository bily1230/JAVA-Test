package thread;

import java.util.concurrent.Callable;

/**
 * Created by ning on 2017/4/25.
 */
public class CallThread implements Callable<String> {
    private int id;
    public CallThread(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    @Override
    public String call() throws Exception {
        return "ThreadCall"+id;
    }
}
