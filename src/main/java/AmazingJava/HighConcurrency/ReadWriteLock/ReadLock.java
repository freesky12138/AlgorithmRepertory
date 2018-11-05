package AmazingJava.HighConcurrency.ReadWriteLock;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ReadWriteLock
 * @description
 * @date 2018/11/5 11:38
 */
public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            while (readWriteLock.getWritingWriters() > 0 ||
                    (readWriteLock.isPerferWriter() && readWriteLock.getWaittingWriters() > 0)){
                readWriteLock.getMUTEX().wait();
            }

            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()){
            readWriteLock.decrementReadingReaders();
            readWriteLock.setPerferWriter(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
