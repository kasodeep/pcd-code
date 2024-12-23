### Isolated Manager

* This class is responsilbe for handling mutual exclusion using locks.
* It used the objects `hasCode` to get the index for the corresponding lock.

### Runtime

* The Runtime class manages the execution of tasks in a parallel computing environment using a ForkJoinPool.
* It maintains a stack of tasks for each thread to track the currently executing tasks.
* It provides methods to submit tasks, resize the worker threads, and print runtime statistics.

### Base Task

#### CountedCompleter

* `CountedCompleter` is an abstract class in the Java Fork/Join framework that allows the creation of fork/join
  tasks where the completion of a task depends on the completion of other tasks.
* Unlike `RecursiveTask` and `RecursiveAction`, which complete when their compute methods return, CountedCompleter
  tasks complete when their pending counts reach `zero`.
* The two important methods are `tryComplete()` & `onCompletion(CountedCompleter<?> caller)` along with
  `addToPendingCount()`.

#### FinishTask

    * It consists of `AtomicLong` counter denoting the number of finish tasks.
    * It has a `runnable` object that need to be executed after submitting the task to Runtime.
    * It then executes the runnable object and calls the `awaitCompletion()` & `join()` method to return the result.

#### FutureTask

    * It is similar, but has a return type along with that a `finishScope`.
    * It uses `CompletableFuture` along with `Callable` to return the value.
    * The finish class `addToPendingCount()` is called in the compute method.

### PCDP

* The `finish()` method calls the submitTask of compute (if child) and then performs `awaitCompletion().`
* Teh `async()` method creates a new FutureTask and `fork()` it, if the `finish` scope exists.
    