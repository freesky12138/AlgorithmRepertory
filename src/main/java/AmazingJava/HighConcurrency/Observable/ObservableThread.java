package AmazingJava.HighConcurrency.Observable;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.Observable
 * @description
 * @date 2018/10/30 17:33
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    public ObservableThread(Task<T> task){
        this(new TaskLifecycle.EmptyLifecycle<>(),task);
    }

    private Cycle cycle;

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        if (task==null)
            throw new IllegalArgumentException("task is not null");
        this.lifecycle=lifecycle;
        this.task=task;
    }

    @Override
    public void run() {
        this.update(Cycle.STARTED,null,null);
        try {
            this.update(Cycle.RUNNABLE,null,null);
            T result=this.task.call();
            this.update(Cycle.DONE,result,null);
        }catch (Exception e){
            this.update(Cycle.DONE,null,e);
        }
    }

    private void update(Cycle cycle,T result,Exception e){
        this.cycle=cycle;
        if(lifecycle==null)
            return;
        try {
            switch (cycle){
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNABLE:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(),result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(),e);
                    break;
            }
        }catch (Exception ex){
            if(cycle==Cycle.ERROR){
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
