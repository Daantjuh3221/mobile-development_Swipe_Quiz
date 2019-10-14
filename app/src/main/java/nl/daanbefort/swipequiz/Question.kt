package nl.daanbefort.swipequiz

import androidx.recyclerview.widget.ItemTouchHelper

data class Question(
    var question: String
){
    companion object {
        val QUESTIONS = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grants 12 ECTS.",
            "A Unit in Kotlin corresponds to a void in Java.",
            "In Kotlin 'when' replaces the 'switch' operator in Java."
        )

        val ANSWERS = arrayOf(
            ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT,
            ItemTouchHelper.LEFT,
            ItemTouchHelper.LEFT
        )
    }
}
