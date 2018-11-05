package AmazingJava.HighConcurrency.ReadWriteLock;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ReadWriteLock
 * @description
 * @date 2018/11/5 11:32
 */
public class ReadWriteLockImpl implements ReadWriteLock{

    private final Object MUTEX=new Object();

    private int writingWriters=0;
    private int waitingWriters=0;

    private int readingReaders=0;

    //是否点钱偏向写锁
    private boolean perferWriter;


    public ReadWriteLockImpl(){
        this(true);
    }

    public ReadWriteLockImpl(boolean perferWriter){
        this.perferWriter=perferWriter;
    }

    public Lock readLock(){
        return new ReadLock(this);
    }


    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    void incrementWritingWriters(){
        this.writingWriters++;
    }
    void incrementWaitingWriters(){
        this.waitingWriters++;
    }
    void incrementReadingReaders(){
        this.readingReaders++;
    }
    void decrementWritingWriters(){
        this.writingWriters--;
    }
    void decrementWaitingWriters(){
        this.waitingWriters--;
    }
    void decrementReadingReaders(){
        this.readingReaders--;
    }

    @Override
    public int getWritingWriters() {
        return writingWriters;
    }

    @Override
    public int getWaittingWriters() {
        return waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return readingReaders;
    }

    Object getMUTEX(){
        return this.MUTEX;
    }

    public boolean isPerferWriter() {
        return perferWriter;
    }

    public void setPerferWriter(boolean perferWriter) {
        this.perferWriter = perferWriter;
    }
}
