import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.text
import com.example.a21pc19_jan10.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var fromEditText: EditText
    private lateinit var toEditText: EditText
    private lateinit var differenceButton: Button
    private lateinit var differenceTextView: TextView
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fromEditText = findViewById(R.id.fromEditText)
        toEditText = findViewById(R.id.toEditText)
        differenceButton = findViewById(R.id.differenceButton)
        differenceTextView = findViewById(R.id.differenceTextView)

        fromEditText.setOnClickListener {
            showDatePickerDialog(fromEditText)
        }

        toEditText.setOnClickListener {
            showDatePickerDialog(toEditText)
        }

        differenceButton.setOnClickListener {
            calculateDateDifference()
        }
    }

    private fun showDatePickerDialog(editText: EditText) {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                updateEditText(editText)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateEditText(editText: EditText) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        editText.setText(dateFormat.format(calendar.time))
    }

    private fun calculateDateDifference() {
        val fromDateString = fromEditText.text.toString()
        val toDateString = toEditText.text.toString()

        if (fromDateString.isEmpty() || toDateString.isEmpty()) {
            differenceTextView.text = "Please select both dates"
            return
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            val fromDate = dateFormat.parse(fromDateString)
            val toDate = dateFormat.parse(toDateString)

            if (fromDate != null && toDate != null) {
                val differenceMillis = toDate.time - fromDate.time
                val differenceDays = TimeUnit.DAYS.convert(differenceMillis, TimeUnit.MILLISECONDS)
                differenceTextView.text = "Difference: $differenceDays days"
            } else {
                differenceTextView.text = "Error parsing dates"
            }
        } catch (e: Exception) {
            differenceTextView.text = "Invalid date format"
            e.printStackTrace()
        }
    }
}