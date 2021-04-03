package com.applab.app_luckynumber

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class UITest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )
    @Test
    fun oneToNine() {
        for(i in 1..9) {
            // 清除 inputNumber 欄位資料
            onView(ViewMatchers.withId(R.id.inputNumber)).perform(clearText())
            // 在 inputNumber 放置指定數值
            onView(ViewMatchers.withId(R.id.inputNumber)).perform(
                ViewActions.typeText(i.toString())
            )
            //按下 Submit button
            onView(ViewMatchers.withId(R.id.submitButton)).perform(click())
            // 按下 Dizlog 上的確定
            try {
                onView(withText(R.string.ok))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
                    .perform(click())
            }catch (e : Exception){
                onView(withText(R.string.exit))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
                    .perform(click())
                break
            }

            //停1秒鐘
            Thread.sleep(1000)
        }
    }
}