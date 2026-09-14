package exercises04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;


class BoundedBuffer<T> implements BoundedBufferInteface<T>{

    private final Semaphore lock = new Semaphore(1);
    private final Queue<T> q = new LinkedList();
    private final Semaphore emptySlots;
    private final Semaphore mealsReady = new Semaphore(0);


    public BoundedBuffer(int buffer){
        emptySlots = new Semaphore(buffer);
    }

    public T take() throws Exception{
        mealsReady.acquire();
        lock.acquire(); // if interrupted while waiting for lock, it might fail.
        T elem = null; 
        try {
            elem = q.poll();
        } catch (Exception e) {
            System.err.println(e);
        }
        finally{
            lock.release();
        }

        emptySlots.release();
        return elem;
    }

    public void insert(T elem) throws Exception{
        emptySlots.acquire();

        lock.acquire(); 
        try {
            q.add(elem);
        } catch (Exception e) {
            
            System.err.println("");
        }
        finally{
            lock.release();
        }
        mealsReady.release();
        
    }

    public static void main(String[] args) throws InterruptedException {
        new BoundedBuffer(2);
    }

}