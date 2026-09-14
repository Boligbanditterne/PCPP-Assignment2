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
the same for all possible program executions.*

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
*Use a lock to eliminate the data races in the program. Explicitly list the changes in program order and
happens-before order compared to what you answered in the previous parts of the exercise. You do not need
to explicitly write the new set of synchronization orders, but you must argue whether the possible executions
in the new set of synchronization orders gives rise to more than one happens-before order set.*

**Program order**
$$
HB^m_{po} = {m(count) -> m(t1.start()) -> m(t2.start()) -> m(t1.join()) -> m(t2.join) -> m(print)}
$$

$$
HB^{t1}_{po} = {t1(lock) -> t1(1)  -> t1(2) -> t1(unlock)}
$$

$$
HB^{t2}_{po} = {t2(lock) -> t2(1)  -> t2(2) -> t2(unlock)}
$$

**Happens-before order**
$$
HB_{start} = {m(t1.start()) -> t1(lock),  m(t2.start()) -> t2(lock)}
$$

$$
HB_{ter} = {t1(lock) -> m(t1.join()),  t2(lock)  -> m(t2.join()) }
$$

We should have two synchronization orders now, one for if t1 accuires the lock first, and one for if t2 does.

### 3.1.7
*Show, using the Java memory model, that your updated program (3.1.6) does not contain data races.*

$$SO_1 = \ldots,\ t_1(lock),\ t_1(1),\ t_1(2),\ t_1(unlock),\ t_2(lock),\ t_2(1),\ t_2(2),\ t_2(unlock),\ \ldots\ $$
 
$$SO_2 = \ldots,\ t_2(lock),\ t_2(1),\ t_2(2),\ t_2(unlock),\ t_1(lock),\ t_1(1),\ t_1(2),\ t_1(unlock),\ \ldots\$$

$$HB_{lock}​={t1​(unlock)→t2​(lock)}$$

$$HB_{lock}={t2​(unlock)→t1(lock)}$$


### 3.1.8

*Consider again the program without the lock, but defining count as volatile. Are all executions of
this version of the program data race free? If you answer yes, use the Java memory model to show that the
program does not contain data races. If you answer no, use the Java memory model to show the program
contains data races.*

As established on the earlier version of code there does not exist a happens before relation between the reads and writes to the variable. Therefore making it volatile will not fix the race conditions, as it can still exists between the writes.


### 3.2

### 3.2.1:

*Show, using the Java memory model, that the program is not correctly synchronized.*

It is no correctly synchronized, as it contains data races. If we look at t1(3) it does a write access to list, meanwhile t2(5) is doing a read to the same list. The list is non-volitile which makes it conflicting. Only one method is synchronized, so t1 gets a lock, while t2 does not acquire that lock. This means there can be a data race.

### 3.2.2

*Add the modifier synchronized to the definition of the method find(...) in the class StringSet.
Show, using the Java memory model, whether the program is correctly synchronized. The notes for 3.2.1
also apply in this exercise.*

*See TestSTringSet.java*


we can now see that we are establishing a happens before relationsship when adding synchronized to find and lock the same object o. Having a lock around the list accesses ensures that either

$$
t_1(lock()) -> ... -> t_1(unlock()) -> t2(lock())
$$

or

$$
t_2(lock()) -> ... -> t_2(unlock()) -> t1(lock())
$$

This happens before relationsship eliminates the data race.

## 3.3

### 3.3.1
*Last week we saw that (a similar version of) this program can loop forever, i.e., there exist executions where
the operation while(x==0) is executed forever by thread t1. What pair of actions must be ordered by
happens-before to prevent these infinite executions*

This must happen at some point to avoid infinite looping

$$
m(6) -> t1(2)
$$

#### 3.3.2
*Show, using the Java memory model, that the pair of actions you identified in 3.3.1 are not ordered by
happens-before in all executions of the program.*

There does not exist a happens before relation between t1(2) and m(6) which leads to the infinite loop.

$$
HB^m_{po} =   m(1) -> m(t1.start()) -> m(6) -> m(7)
$$

$$
HB^{t1}_{po} = {t1(2)  -> t1(3) -> t1(4)}
$$
