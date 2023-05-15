package br.com.lira.rickandmorty.core.toolkit

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class OneShotLiveData<T : Any> : MutableLiveData<T>() {

    private val pendingNotification = AtomicBoolean(false)
    private val observersCount = AtomicInteger(0)
    private val notifyCount = AtomicInteger(0)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        observersCount.getAndIncrement()
        super.observe(
            owner,
            Observer { data ->
                val hasPendingNotification: Boolean = (notifyCount.decrementAndGet() > 0)
                if (pendingNotification.getAndSet(hasPendingNotification)) {
                    observer.onChanged(data)
                }
            }
        )
    }

    @MainThread
    override fun setValue(@Nullable value: T?) {
        pendingNotification.set(true)
        notifyCount.set(observersCount.get())
        super.setValue(value)
    }

    override fun getValue(): T? {
        throw UnsupportedOperationException("Should be used only 'observe' method for get values")
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        super.removeObserver(observer)
        observersCount.getAndDecrement()
    }
}
