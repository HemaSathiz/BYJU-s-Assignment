package com.sample.headlinesbyjusassignment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.sample.headlinesbyjusassignment.ui.headlines.HeadlinesActivity
import org.hamcrest.Matchers
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

class MainActivityInstrumentedTest {

    @Rule @JvmField
    val activityRule = ActivityTestRule(HeadlinesActivity::class.java)

    @Test
    fun testSampleRecyclerVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.rvHeadlines))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.getActivity().getWindow().getDecorView())
                )
            )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testRecyclerViewData() {
        if (getRecyclerViewCount() > 0) {
            Espresso.onView(ViewMatchers.withId(R.id.rvHeadlines)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        }
    }

    private fun getRecyclerViewCount(): Int {
        val recyclerView: RecyclerView =
            activityRule.getActivity().findViewById(R.id.rvHeadlines)
        return recyclerView.adapter!!.itemCount
    }
}
