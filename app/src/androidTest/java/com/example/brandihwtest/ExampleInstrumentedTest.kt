package com.example.brandihwtest

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.brandihwtest.dialog.ImageDetailPopup
import com.example.brandihwtest.repository.RetrofitRepository
import com.example.brandihwtest.repository.pojo.ImageData

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.brandihwtest", appContext.packageName)
    }

    @Test
    /**
     * 재조회 테스트
     * */
    fun requestTest()
    {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val retrofitRepository = RetrofitRepository()
        retrofitRepository.getImageLists("축구")
    }

    @Test
    /**
     * List Paging  시 추가 데이터 요청 테스트
     * */
    fun requestAddPageTest()
    {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val retrofitRepository = RetrofitRepository()
        retrofitRepository.addImageLists("축구", 2)
    }
}