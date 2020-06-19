package com.jayphone.practice.java.threadPool;

import java.io.Serializable;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 如果需要实现独占锁需要重写tryAcquire()和tryRelease()
 * 如果需要实现共享锁需要重写tryAcquireShared()和tryReleaseShared()
 * <p>
 * Created by JayPhone on 2020/6/18
 */
public class Mutex implements Serializable {

    //静态内部类，继承AQS
    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 独占式获取同步状态，试着获取，成功返回true，反之为false
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            //当状态为0的时候获取锁，CAS操作成功，则state状态为1
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 独占式释放同步状态，等待中的其他线程此时将有机会获取到同步状态
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            //释放锁，将同步状态置为0
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 共享式获取同步状态，返回值大于等于0，代表获取成功；反之获取失败
         *
         * @param arg
         * @return
         */
        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        /**
         * 共享式释放同步状态，成功为true，失败为false
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        /**
         * 是否在独占模式下被线程占用
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            //是否处于占用状态
            return getState() == 1;
        }
    }

    //同步对象完成一系列复杂的操作，我们仅需指向它即可
    private final Sync sync = new Sync();

    //加锁操作，代理到acquire（模板方法）上就行，acquire会调用我们重写的tryAcquire方法
    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
}
