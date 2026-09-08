## 3.1

### 3.1.1

*List the possible actions in the executions of the program. Classify the actions as: variable access, syn-
chronization, or other. You may assign numbers to operations in the program to easily refer to them in this
exercise and the ones below.*

**Variable Access**
$$count = 0;$$
$$"count="+count$$
$$int temp = count;$$
$$count = temp + 1;$$

**Synchronization**
$$t1.start();$$
$$t2.start();$$
$$t1.join();$$
$$t2.join();$$

**Other**
$$CountingThread t1 = new CountingThread();$$
$$CountingThread t2 = new CountingThread();$$
$$System.out.println$$


### 3.1.2:

*Define the happens-before order set containing action pairs obtained from the program order rule. Is this set
the same for all possible program executions?*

$$
HB^m_{po} = {m(count) -> m(t1.start()) -> m(t2.start()) -> m(t1.join()) -> m(t2.join) -> m(print)}
$$

$$
HB^{t1}_{po} = {t1(1)  -> t1(2)}
$$

$$
HB^{t2}_{po} = {t2(1)  -> t2(2)}
$$

It is always the same  
### 3.1.3:

*Define the happens-before order set containing action pairs obtained from the thread start rule and the thread
termination rule. Is this set the same for all possible program executions?*

$$
HB_{start} = {m(t1.start()) -> t1(1),  m(t2.start()) -> t2(1)}
$$

$$
HB_{ter} = {t1(2) -> m(t1.join()),  t2(2)  -> m(t2.join()) }
$$

It is always the same.

### 3.1.4:
*Define the set of all possible synchronization orders for this program.*

$$
{m(t1.start()),  m(t2.start()),  m(t1.join()),  m(t2.join())}
$$

There is no other way to order them, as they have to be consistent with program order.

### 3.1.5:
*how, using the Java memory model, that this program contains data races.*

There does not exists a happens before relationsship between for example t1(1) -> t2(1) nor t2(1) -> t1(1), which results in it not being immune to data races. The variable is also non-volitile

### 3.1.6

### 3.2

### 3.2.1:

It is no correctly synchronized, as it contains data races. If we look at t1(3) it does a write access to list, meanwhile t2(5) is doing a read to the same list. The list is non-volitile which makes it conflicting. Only one method is synchronized, so t1 gets a lock, while t2 does not acquire that lock. This means there will be a data race.

### 3.2.2

INSERT CODE

we can now see that we are establishing a happens before relationsship when adding synchronized to find and lock the same object o. Having a lock around the list accesses ensures that either

$$
t_1(lock(o)) -> ... -> t_1(unlock(o)) -> t2(lock(o))
$$

or

$$
t_2(lock(o)) -> ... -> t_2(unlock(o)) -> t1(lock(o))
$$

This happens before relationsship eliminates the data race.

3.3
