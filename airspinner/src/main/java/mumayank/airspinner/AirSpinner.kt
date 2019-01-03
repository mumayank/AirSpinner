package mumayank.airspinner


import android.app.Activity
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import mumayank.airspinner.lib.R
import mumayank.com.airdialog.AirDialog

class AirSpinner(
    activity: Activity,
    parentLayoutViewGroup: ViewGroup,
    title: String,
    charSequenceArray: Array<CharSequence>,
    callback: Callback
) {

    interface Callback {
        fun onSelected(selectedOptionIndex: Int)
    }

    private var textInputLayout: TextInputLayout? = null
    private var textInputEditText: TextInputEditText? = null

    init {
        val airOutlineSpinnerView = LayoutInflater.from(activity).inflate(R.layout.air_outline_spinner, parentLayoutViewGroup, true)

        textInputLayout = airOutlineSpinnerView.findViewById(R.id.textInputLayout)
        textInputLayout?.hint = title

        textInputEditText = airOutlineSpinnerView.findViewById(R.id.textInputEditText)
        textInputEditText?.setText(charSequenceArray.get(0).toString())
        textInputEditText?.setOnClickListener {
            val currentlySelectedIndex = charSequenceArray.indexOf(textInputEditText?.text.toString())
            AirDialog(activity, title).showOptions(
                charSequenceArray,
                onOptionsCallback = object: AirDialog.ShowOptionsCallback {
                    override fun onCancelled() {
                        // do nothing
                    }

                    override fun onOptionSelected(selectedOptionIndex: Int) {
                        textInputEditText?.setText(charSequenceArray.get(selectedOptionIndex).toString())
                        callback.onSelected(selectedOptionIndex)
                    }

                },
                defaultSelectedOptionIndex = currentlySelectedIndex
            )

        }
    }

    fun getText(): String {
        return textInputEditText?.text.toString()
    }
}