package com.sample.headlinesbyjusassignment.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DateFormatterTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testDateFormatter() {
        assertEquals("2021-06-09", DateFormatter.dateFormatter("2021-06-09T05:00:00Z"))
    }
}
