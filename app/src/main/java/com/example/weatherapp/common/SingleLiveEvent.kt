package com.example.weatherapp.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

// use only for navigation, snack bar, toast or loading progress bar purpose.
class SingleLiveEvent<T>() : MutableLiveData<T>() {

    constructor(value: T) : this() {
        setValue(value)
    }

    private val canPost = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        //Need to remove all observers before observing
        //As SingleLiveEvent should only have one observer and only latest observer should be observed
        if (hasObservers()) {
            removeObservers(owner)
        }
        super.observe(owner) {
            if (canPost.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        canPost.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }
}