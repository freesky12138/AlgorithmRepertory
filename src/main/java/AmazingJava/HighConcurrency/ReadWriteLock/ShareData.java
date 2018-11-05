package AmazingJava.HighConcurrency.ReadWriteLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ReadWriteLock
 * @description
 * @date 2018/11/5 14:34
 */
public class ShareData {
    private final List<Character> characters = new ArrayList<>();
    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            characters.add(i, 'c');
        }
    }

    public char[] read() throws InterruptedException {
        try {
            readLock.lock();

            char[] newBuffer=new char[length];
            for (int i = 0; i < length; i++) {
                newBuffer[i]=characters.get(i);
            }
            slowly();
            return newBuffer;
        } finally {
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException{
        try {
            writeLock.lock();
            for (int i = 0; i < length; i++) {
                this.characters.add(i,c);
            }
            slowly();
        }finally {
            writeLock.unlock();
        }
    }

    private void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
