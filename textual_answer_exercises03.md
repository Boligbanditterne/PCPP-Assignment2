## 3.1

### 3.1.1

**Variable Access**
line 1, 3, 4, 12

**Synchronization**
line 6, 7, 9, 10

**Other**
12


### 3.1.2

1. count = 0;

2. CountingThread t1 = new CountingThread();

3. CountingThread t2 = new CountingThread();

4.  ┌──> t1.start(); ──> t1.join() ──┐
    ┤                                ├──> 
    └──> t2.start(); ──> t2.join() ──┘

5. System.out.println("count="+count);

### 3.1.3
