package nl.daanbefort.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_view.*
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        Question.QUESTIONS.iterator().forEach {
            questions.add(Question(it))
        }
        questionAdapter.notifyDataSetChanged();
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemPosition = viewHolder.adapterPosition;
                val answer = Question.ANSWERS[itemPosition]
                if(direction == answer){
                    showShortSnackbar("Correct! The answer was: " + checkFalseOrTrue(answer))
                } else {
                    showShortSnackbar("Incorrect! The answer was: " + checkFalseOrTrue(answer))
                }
                println(viewHolder.adapterPosition)
                questionAdapter.notifyItemChanged(itemPosition)
            }
        }
        return ItemTouchHelper(callback)
    }

    fun checkFalseOrTrue(direction: Int): String{
        return if (direction == ItemTouchHelper.LEFT) {
            "true"
        } else {
            "false"
        }
    }

    fun showShortSnackbar(text: String){
        Snackbar.make(rvQuestions, text, Snackbar.LENGTH_SHORT).show()
    }
}
