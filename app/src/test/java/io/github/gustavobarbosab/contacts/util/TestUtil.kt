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
    val value = LiveDataTestUtil.getLiveDataValue(liveData)
    assertEquals(value.getContentIfNotHandled(), taskId)
}

fun assertSnackbarMessage(snackbarLiveData: LiveData<Event<Int>>, messageId: Int) {
    val value: Event<Int> = LiveDataTestUtil.getLiveDataValue(snackbarLiveData)
    assertEquals(value.getContentIfNotHandled(), messageId)
}

fun <T : View> RecyclerView.getChildView(index: Int, viewId: Int): T? = this.getChildAt(index).findViewById(viewId)
