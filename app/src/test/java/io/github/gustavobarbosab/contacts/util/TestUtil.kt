package io.github.gustavobarbosab.contacts.util

import android.view.View
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.contacts.utils.Event
import org.junit.Assert.assertEquals

fun assertLiveDataEventTriggered(
    liveData: LiveData<Event<String>>,
    taskId: String
) {
    val value = LiveDataTestUtil.getValue(liveData)
    assertEquals(value.getContentIfNotHandled(), taskId)
}

fun assertSnackbarMessage(snackbarLiveData: LiveData<Event<Int>>, messageId: Int) {
    val value: Event<Int> = LiveDataTestUtil.getValue(snackbarLiveData)
    assertEquals(value.getContentIfNotHandled(), messageId)
}

fun <T : View> RecyclerView.getChildView(index: Int, viewId: Int) = this.getChildAt(index).findViewById<T>(viewId)
