package AmazingJava.HighConcurrency.ReadWriteLock;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ReadWriteLock
 * @description
 * @date 2018/11/5 11:38
 */
public class WriteLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                readWriteLock.incrementWaitingWriters();
                while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                this.readWriteLock.decrementWaitingWriters();
            }
            this.readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()){
            readWriteLock.decrementWritingWriters();
            readWriteLock.setPerferWriter(false);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
