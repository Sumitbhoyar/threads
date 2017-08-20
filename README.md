**Process** is an instance of a computer program that is being executed. That means process can not share the same memory space.
 
**Thread** is an independent path of execution. That means thread can share the same memory space.
 
**Multithreading** is a Java feature that allows concurrent execution of two or more parts of a program for maximum utilization of CPU.

**Concurrency:** A condition that exists when at least two threads are making progress. A more generalized form of parallelism that can include time-slicing as a form of virtual parallelism.

**Parallelism:** A condition that arises when at least two threads are executing simultaneously.

**concurrency vs. parallelism**

- Concurrency is when two tasks can start, run, and complete in overlapping time periods. Parallelism is when tasks literally run at the same time, eg. on a multi-core processor.

- Concurrency is the composition of independently executing processes, while parallelism is the simultaneous execution of (possibly related) computations.

- An application is concurrent – but not parallel, means that it processes more than one task at the same time, but no two tasks are executing at same time instant.

- An application is parallel – but not concurrent, means that it processes multiple sub-tasks of a task in multi-core CPU at same time.

- An application is neither parallel – nor concurrent, means that it processes all tasks one at a time, sequentially.

- An application is both parallel – and concurrent, means that it processes multiple tasks concurrently in multi-core CPU at same time .

**Why multithreading**
- Keep a Process Responsive
- Keep a Processor Busy (Better resource utilization.)
- Keep Multiple Processors Busy (Better resource utilization.)
- Simplify Coding of Cooperating Tasks

**Concurrency Models**
- **Parallel Workers**: In the parallel worker concurrency model a delegator distributes the incoming jobs to different workers. Each worker completes the full job. The workers work in parallel, running in different threads, and possibly on different CPUs.

> The advantage of the parallel worker concurrency model is that it is easy to understand. To increase the parallelization of the application you just add more workers.

> Disadvantages: Shared State Can Get Complex. Job Ordering is Nondeterministic

- **Assembly Line / Reactive systems / Event driven systems**

> The workers are organized like workers at an assembly line in a factory. Each worker only performs a part of the full job. When that part is finished the worker forwards the job to the next worker.

> Each worker is running in its own thread, and shares no state with other workers. This is also sometimes referred to as a shared nothing concurrency model.

- **Same-threaded Systems**

> Same-threading is a concurrency model where a single-threaded systems are scaled out to N single-threaded systems. The result is N single-threaded systems running in parallel.

> A same-threaded system is not a pure single-threaded system, because it contains of multiple threads. But - each of the threads run like a single-threaded system.

> Same-threaded basically means that data processing stays within the same thread, and that no threads in a same-threaded system share data concurrently.

**Critical section**: Concurrent accesses to shared resources can lead to unexpected or erroneous behavior, so parts of the program where the shared resource is accessed are protected. This protected section is the critical section or critical region. It cannot be executed by more than one process.

**Race condition**: A **race condition** occurs when two or more threads can access shared data and they try to change it at the same time. Because the thread scheduling algorithm can swap between threads at any time, you don't know the order in which the threads will attempt to access the shared data. Therefore, the result of the change in data is dependent on the thread scheduling algorithm, i.e. both threads are "racing" to access/change the data.

> To prevent race conditions from occurring you must make sure that the critical section is executed as an atomic instruction. That means that once a single thread is executing it, no other threads can execute it until the first thread has left the critical section.

> Race conditions can be avoided by proper thread synchronization in critical sections. Thread synchronization can be achieved using a synchronized block of Java code.

**Mutual exclusion** is a property of concurrency control, which is instituted for the purpose of preventing race conditions; it is the requirement that one thread of execution never enter its critical section at the same time that another concurrent thread of execution enters its own critical section.

**A Monitor** is an object designed to be accessed from multiple threads. The member functions or methods of a monitor object will enforce mutual exclusion, so only one thread may be performing any action on the object at a given time. If one thread is currently executing a member function of the object then any other thread that tries to call a member function of that object will have to wait until the first has finished.

**A Semaphore** is a lower-level object. You might well use a semaphore to implement a monitor. A semaphore essentially is just a counter. When the counter is positive, if a thread tries to acquire the semaphore then it is allowed, and the counter is decremented. When a thread is done then it releases the semaphore, and increments the counter.

> If the counter is already zero when a thread tries to acquire the semaphore then it has to wait until another thread releases the semaphore. If multiple threads are waiting when a thread releases a semaphore then one of them gets it. The thread that releases a semaphore need not be the same thread that acquired it.

**Monitor vs Semaphore**
> A monitor is like a telephone booth. Only one person can enter at a time. They lock the door to prevent anyone else coming in, call someone, and then unlock it when they leave.

> A semaphore is like a bike hire place. They have a certain number of bikes. If you try and hire a bike and they have one free then you can take it, otherwise you must wait. When someone returns their bike then someone else can take it. If you have a bike then you can give it to someone else to return --- the bike hire place doesn't care who returns it, as long as they get their bike back.

**Thread safe**: Code that is safe to call by multiple threads simultaneously is called thread safe. If a piece of code is thread safe, then it contains no race conditions. 

> All premitive data type variables are always thread safe.

> If local objects doesn't escape the method, even through reference, then they are thread safe, else they are not.

> Instance variable are not thread safe.

**Immutable Objects**

> Immutable Objects are thread safe. Immutable objects could not be modified once created.

> To make object immutable, provide getters and no setters for primitive and immutable data. The getters of objects should return the copy and not actual object referene.

**Java memory model**

> The Java memory model specifies how the Java virtual machine works with the computer's memory (RAM).
> The Java memory model specifies how and when different threads can see values written to shared variables by other threads, and how to synchronize access to shared variables when necessary.

> The Java memory model used internally in the JVM divides memory between thread stacks and the heap. 
> Each thread running in the Java virtual machine has its own thread stack. The thread stack contains information about what methods the thread has called to reach the current point of execution.

> The thread stack also contains all local variables for each method being executed (all methods on the call stack). A thread can only access it's own thread stack. Local variables created by a thread are invisible to all other threads than the thread who created it. Even if two threads are executing the exact same code, the two threads will still create the local variables of that code in each their own thread stack. Thus, each thread has its own version of each local variable.

> Static class variables are also stored on the heap along with the class definition.

**Hardware Memory Architecture**

```
 -------        ------------------         -----------------------
| RAM   |  --> | CPU Cache Memory |  -->  |CPU1  ---------------  |
 -------        ------------------        |     | CPU Registers | |
                                 \        |      ---------------  |
                                           -----------------------
                                   \->   -----------------------
                                        |CPU2  ---------------  |
                                        |     | CPU Registers | |
                                        |      ---------------  |
                                         -----------------------
```
										 
**Issue: Race condition**

Imagine if thread A reads the variable count of a shared object into its CPU cache. Imagine too, that thread B does the same, but into a different CPU cache. Now thread A adds one to count, and thread B does the same. Now var1 has been incremented two times, once in each CPU cache.
If these increments had been carried out sequentially, the variable count would be been incremented twice and had the original value + 2 written back to main memory.
However, the two increments have been carried out concurrently without proper synchronization. Regardless of which of thread A and B that writes its updated version of count back to main memory, the updated value will only be 1 higher than the original value, despite the two increments.
To solve this problem you can use a Java synchronized block. A synchronized block guarantees that only one thread can enter a given critical section of the code at any given time. Synchronized blocks also guarantee that all variables accessed inside the synchronized block will be read in from main memory, and when the thread exits the synchronized block, all updated variables will be flushed back to main memory again, regardless of whether the variable is declared volatile or not.


**Issue: Visibility of shared object**

If two or more threads are sharing an object, without the proper use of either volatile declarations or synchronization, updates to the shared object made by one thread may not be visible to other threads.






