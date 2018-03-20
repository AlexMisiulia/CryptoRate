package best.lang.cryptorates.helper

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher


fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
    checkNotNull(itemMatcher)
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}

fun <T> forEachItem(id: Int, items: Collection<T>, matcher: (T) -> Matcher<View>) {
    items.forEachIndexed {index, item ->
        onView(withId(id))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(atPosition(index, matcher(item))))

    }
}