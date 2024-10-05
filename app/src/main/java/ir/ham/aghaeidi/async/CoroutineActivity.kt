package ir.ham.aghaeidi.async

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ir.ham.aghaeidi.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.util.concurrent.TimeUnit

class CoroutineActivity : AppCompatActivity() {
    private val TAG = CoroutineActivity::class.simpleName
    private lateinit var binding: ActivityCoroutineBinding

    private val counterFlow = MutableSharedFlow<Int>()
    private val stateFlow2 = MutableStateFlow<Int>(0)


//    private val leakyRunnable = Runnable {
//        repeat(100){
//            TimeUnit.SECONDS.sleep(1)
//            Log.i(TAG, "leakyRunnable : $it")
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Thread(leakyRunnable).start()
//        simpleLaunch()
//        simpleAsyncKotlinCoroutine()
//        exceptionInLaunchKotlinCoroutine()

//        simpleLaunchRxKotlin()
//        simpleAsyncRxKotlin()
//        zipRequestsRxRxKotlin()
//        mergeRequestsRxRxKotlin()
//        launchWhen()
//        flatMapKotlinCoroutine()
//        sampleFlow()
//        counterFlow()
//        producesFasterThanFlowNormal()
//        producesFasterThanSharedFlow()

        mergeOperator()
        zipOperator()


//        flatMapConcat()
//        flatMapMerge()
//        flatMapLatest()
    }

    private fun mergeOperator(){
        // FLOW
        lifecycleScope.launch {
            val flow1 = flowOf(1, 2, 3)
//            val flow1 = flow {
//                repeat(3){
//                    delay(500)
//                    emit(it)
//                }
//            }
            val flow2 = flowOf(4, 5, 6)
            val mergedFlow = merge(flow1, flow2)
            mergedFlow.collect { Log.i(TAG, "mergeOperator KOTLIN: $it") }

            val observable1 = Observable.just(1, 2, 3)
            val observable2 = Observable.just("A", "B", "C")
            val mergedObservable = Observable.merge(observable1, observable2)
            mergedObservable.subscribe { Log.i(TAG, "mergeOperator RxKotlin: $it") }
        }
    }

    private fun zipOperator(){
        lifecycleScope.launch {
//            val flow1 = flowOf(1, 2, 3)
            val flow1 = flow {
                repeat(3){
                    delay(1000)
                    emit(it)
                }
            }
            val flow2 = flowOf("A", "B", "C")
            val zippedFlow = flow1.zip(flow2) { num, char -> "$num $char" }

            zippedFlow.collect { Log.i(TAG, "zipOperator: KOTLIN: $it") }

            val observable1 = Observable.just(1, 2, 3).concatMap { Observable.just(it).delay(1,TimeUnit.SECONDS) }
            val observable2 = Observable.just("A", "B", "C")
            val zippedObservable = Observable.zip(observable1, observable2) { num, char -> "$num$char" }
            zippedObservable.subscribe { Log.i(TAG, "zipOperator: RxKotlin: $it") }

        }
    }

    @SuppressLint("CheckResult")
    private fun flatMapConcat(){
        lifecycleScope.launch {
            val numbers = flowOf(1, 2, 3)
            numbers.flatMapConcat { number ->
                flow {
                    emit("$number: A")
                    delay(100) // Simulate async work
                    emit("$number: B")
                }
            }.flatMapConcat {
                flow {
                    emit("$it: C")
                    delay(100) // Simulate async work
                    emit("$it: D")
                }
            }

                .collect { Log.i(TAG, "flatMapConcat FLOW: $it") }

        val source = Observable.just(1, 2, 3)
        source.concatMap { number ->
            Observable.just("$number: A", "$number: B").delay(100, TimeUnit.MILLISECONDS)
        }
            .subscribe { Log.i(TAG, "flatMapConcat: RxKotlin: $it") }

        }
    }

    @SuppressLint("CheckResult")
    private fun flatMapMerge(){
        lifecycleScope.launch {
            val numbers = flowOf(1, 2, 3)
            numbers.flatMapMerge { number ->
                flow {
                    emit("$number: A")
                    delay(100) // Simulate async work
                    emit("$number: B")
                }
            }.flatMapMerge {
                flow {
                    emit("$it: C")
                    delay(100) // Simulate async work
                    emit("$it: D")
                }
            }
                .collect { Log.i(TAG, "flatMapMerge FLOW: $it") }

        val source = Observable.just(1, 2, 3)
        source.flatMap { number ->
            Observable.just("$number: A", "$number: B").delay(100, TimeUnit.MILLISECONDS)
        }.subscribe { Log.i(TAG, "flatMapMerge: RxKotlin: $it") }
        }

    }


    @SuppressLint("CheckResult")
    private fun flatMapLatest(){
        lifecycleScope.launch {
            val numbers = flowOf(1, 2, 3)
            numbers.flatMapLatest { number ->
                flow {
                    emit("$number: A")
                    delay(100) // Simulate async work
                    emit("$number: B")
                }
            }.collect{ Log.i(TAG, "flatMapLatest: FLOW: $it") }

        val source = Observable.just(1, 2, 3).concatMap { number ->
            Observable.just(number).delay(50, TimeUnit.MILLISECONDS)
        }
        source.switchMap { number ->
            Observable.just("$number: A", "$number: B").delay(100, TimeUnit.MILLISECONDS)
        }.subscribe { Log.i(TAG, "flatMapLatest: RxKotlin: $it") }
        }

    }




    private fun producesFasterThanFlowNormal() {
        lifecycleScope.launch {
            flow {
                repeat(Int.MAX_VALUE) {
                    Log.i(TAG, "producesFasterThanFlow: PRODUCER before: $it")
                    delay(3000)
                    Log.i(TAG, "producesFasterThanFlow: PRODUCER after: $it")
                    emit(it)
                }
            }.collect {
                Log.i(TAG, "producesFasterThanFlow: COLLECT before $it")
                delay(4000)
                Log.i(TAG, "producesFasterThanFlow: COLLECT after $it")
            }
        }
    }

    private fun producesFasterThanSharedFlow() {
        val sharedFlow = MutableSharedFlow<Int>()

        lifecycleScope.launch {
            repeat(Int.MAX_VALUE) {
                Log.i(TAG, "producesFasterThanSharedFlow:PRODUCER before delay: $it")
                delay(300)
                Log.i(TAG, "producesFasterThanSharedFlow: PRODUCER after delay: $it going to emit")
                sharedFlow.emit(it)
                Log.i(TAG, "producesFasterThanSharedFlow: PRODUCER after emit: $it")
            }
        }

        lifecycleScope.launch {
            sharedFlow
                .buffer(64)
                .collect {
                    Log.i(TAG, "producesFasterThanSharedFlow: COLLECT before delay: $it")
                    delay(8000)
                    Log.i(TAG, "producesFasterThanSharedFlow: COLLECT after delay: $it")
                }
        }
    }

    private fun producesFasterThanFlowConflated() {
        lifecycleScope.launch {
            flow {
                repeat(Int.MAX_VALUE) {
                    delay(3000)
                    Log.i(TAG, "producesFasterThanFlow: emit: $it")
                    emit(it)
                }
            }
                .conflate()
                .collect {
                    delay(4000)
                    Log.i(TAG, "producesFasterThanFlow: $it")
                }
        }
    }

    private fun flatMapKotlinCoroutine() {
        lifecycleScope.launch {
            val fastFlow = flow {
                for (i in 1..5) {
                    delay(100)  // Delay between emissions (fast producer)
                    Log.i(TAG, "flatMapKotlinCoroutine: emit: $i")
                    emit(i)
                }
            }

            fastFlow
                .collect { value ->
                    delay(300)  // Simulate a slow collector
                    Log.i(TAG, "flatMapKotlinCoroutine: collect: $value")
                }
        }
    }

    private val stateFlow = MutableStateFlow(0)

    private fun simpleLaunchCoroutine() {
        lifecycleScope.launch {
            launch {
                Log.i(TAG, "simpleLaunch: launch 1")
            }
            launch {
                Log.i(TAG, "simpleLaunch: launch 2")
            }
        }
    }

    private fun sampleFlow() {
        CoroutineScope(Dispatchers.Default).launch {
            flow<Int> { emit(0) }
                .map {
                    Log.i(TAG, "sampleFlow: first map: name: ${Thread.currentThread().name}")
                    it * 10
                }
                .flowOn(Dispatchers.Main)
                .map {
                    Log.i(TAG, "sampleFlow: second map name: ${Thread.currentThread().name}")
                    it.toString()
                }
                .collect {
                    Log.i(TAG, "sampleFlow: collect thred name: ${Thread.currentThread().name}")
                }
        }
    }

    private fun simpleAsyncKotlinCoroutine() {
        lifecycleScope.launch {
            Log.i(TAG, "simpleAsync: first")
            val differ0 = async {
                delay(1000)
                Log.i(TAG, "simpleAsync: async 1")
            }
            val differ1 = async {
                delay(2000)
                Log.i(TAG, "simpleAsync: async 2")
            }

            awaitAll(differ0, differ1)
            Log.i(TAG, "simpleAsync: end await all")
        }
    }

    private fun exceptionInLaunchKotlinCoroutine() {
        lifecycleScope.launch {
            try {
                async {
                    delay(100)
                    throw Exception("Exception is thrown by launch 1")
                }.await()
            } catch (e: Exception) {
                Log.e(TAG, "exceptionInLaunch: exception", e)
            }
        }
    }

    private fun simpleLaunchRxKotlin() {
        // Create a Completable for the first task
        val task1 = Completable.fromAction {
            Log.i(TAG, "simpleLaunchRxKotlin: launch 1")
        }

        // Create a Completable for the second task
        val task2 = Completable.fromAction {
            TimeUnit.SECONDS.sleep(1)
            Log.i(TAG, "simpleLaunchRxKotlin: launch 2")
        }

        // Chain the tasks together and manage threading
        val disposable = task1.subscribeOn(Schedulers.io()) // Run task1 on I/O thread
            .doOnComplete {
                Log.i(TAG, "simpleLaunchRxKotlin: launch 1 completed")
            }.andThen(task2) // After task1 completes, run task2
            .subscribeOn(Schedulers.io()) // Ensure task2 runs on I/O thread
            .doOnComplete {
                Log.i(TAG, "simpleLaunchRxKotlin: launch 2 completed")
            }.subscribeBy(onComplete = { /* All tasks completed successfully */ },
                onError = { error -> Log.e(TAG, "Error: ${error.message}") } // Handle errors
            )
    }

    private fun simpleAsyncRxKotlin() {
        Log.i(TAG, "simpleAsyncRxKotlin: ")
        val differ0 = Single.fromCallable {
            // Simulate work
            TimeUnit.SECONDS.sleep(1) // Delay of 1000ms
            Log.i(TAG, "simpleAsyncRxKotlin: async 1")
            "Result from async 1"
        }.subscribeOn(Schedulers.io()) // Run on I/O thread

        val differ1 = Single.fromCallable {
            // Simulate work
            TimeUnit.SECONDS.sleep(2) // Delay of 2000ms
            Log.i(TAG, "simpleAsyncRxKotlin: async 2")
            "Result from async 2"
        }.subscribeOn(Schedulers.io()) // Run on I/O thread

        // Simulating a delay before awaiting the results
        val disposable = Single.zip(differ0, differ1) { result1, result2 ->
            // This block will execute once both Singles complete
            Log.i(TAG, "simpleAsyncRxKotlin: end await all with results: $result1, $result2")
        }.subscribeBy(onSuccess = { /* This block will not execute for Single.zip */ },
            onError = { error -> Log.e(TAG, "Error: ${error.message}") })
    }

    private fun zipRequestsRxRxKotlin() {
        val first = WebService.firstStreamCoroutine()
        val second = WebService.secondStreamCoroutine()
        val third = WebService.thirdStreamCoroutine()
        val mergedResult =
            Single.zip(first, second, third) { resultFirst, resultSecond, resultThird ->
                "[ $resultFirst , $resultSecond , $resultThird ]"
            }
        val disposable = mergedResult.subscribeBy(
            onSuccess = { result -> Log.i(TAG, "Merged result using zip: $result") },
            onError = { error -> Log.e(TAG, "Error using zip: ${error.message}") }
        )
    }

    private fun mergeRequestsRxRxKotlin() {
        val first = WebService.firstStreamCoroutine().toObservable()
        val second = WebService.secondStreamCoroutine().toObservable()
        val third = WebService.thirdStreamCoroutine().toObservable()
        val mergedSingle = Observable.merge(first, second, third)
//        val disposable = mergedSingle.subscribeBy(
//            onNext = { result -> Log.i(TAG, "Merged result using zip: $result") },
//            onError = { error -> Log.e(TAG, "Error using zip: ${error.message}") }
//        )


        val observable1 = Observable.just(0).delay(1,TimeUnit.SECONDS   )
        val observable2 = Observable.just("hello")
        Observable.concat(observable1,observable2).subscribe {
            Log.i(TAG, "mergeRequestsRxRxKotlin: $it")
        }

    }


}

object WebService {
    private val TAG = WebService::class.simpleName
    fun firstStreamCoroutine(): Single<String> {
        return Single.timer(2, TimeUnit.SECONDS)
            .map { "Result from async 1" }
            .subscribeOn(Schedulers.io())
    }

    fun secondStreamCoroutine(): Single<String> {
        return Single.timer(1, TimeUnit.SECONDS)
            .map { "Result from async 2" }
            .subscribeOn(Schedulers.io())
    }

    fun thirdStreamCoroutine(): Single<String> {
        return Single.timer(1, TimeUnit.SECONDS)
            .map { "Result from async 3" }
            .subscribeOn(Schedulers.io())
    }
}