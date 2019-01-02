package mumayank.airspinner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val charSequence: Array<CharSequence> = arrayOf("One", "Two", "Three", "Four")

        AirSpinner.init(
            this,
            findViewById(R.id.parentLayout) as ViewGroup,
            "SHOW",
            charSequence,
            object : AirSpinner.Callback {
                override fun onSelected(selectedOptionIndex: Int) {
                    // do something
                }
            }
        )

    }
}
