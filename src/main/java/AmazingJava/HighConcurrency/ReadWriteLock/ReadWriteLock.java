package AmazingJava.HighConcurrency.ReadWriteLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ReadWriteLock
 * @description
 * @date 2018/11/5 11:26
 */
public interface ReadWriteLock {

    Lock readLock();
    Lock writeLock();

    //获取多少线程在执行写操作
    int getWritingWriters();

    //获取多少线程正在等写操作
    int getWaittingWriters();

    //获取多少线程在执行读操作
    int getReadingReaders();

    //工厂方法，创建ReadWriteLock
    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }
}
