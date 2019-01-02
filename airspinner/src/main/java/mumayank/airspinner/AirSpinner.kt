package mumayank.airspinner


import android.app.Activity
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import mumayank.airspinner.lib.R
import mumayank.com.airdialog.AirDialog

class AirSpinner {

    interface Callback {
        fun onSelected(selectedOptionIndex: Int)
    }

    companion object {

        fun init(
            activity: Activity,
            parentLayoutViewGroup: ViewGroup,
            title: String,
            charSequenceArray: Array<CharSequence>,
            callback: Callback
        ) {
            val airOutlineSpinnerView = LayoutInflater.from(activity).inflate(R.layout.air_outline_spinner, parentLayoutViewGroup, true)

            val textInputLayout = airOutlineSpinnerView.findViewById<TextInputLayout>(R.id.textInputLayout)
            textInputLayout.hint = title

            val textInputEditText = airOutlineSpinnerView.findViewById(R.id.textInputEditText) as TextInputEditText
            textInputEditText.setText(charSequenceArray.get(0).toString())
            textInputEditText.setOnClickListener {
                val currentlySelectedIndex = charSequenceArray.indexOf(textInputEditText.text.toString())
                AirDialog(activity, title).showOptions(
                    charSequenceArray,
                    onOptionsCallback = object: AirDialog.ShowOptionsCallback {
                        override fun onCancelled() {
                            // do nothing
                        }

                        override fun onOptionSelected(selectedOptionIndex: Int) {
                            textInputEditText.setText(charSequenceArray.get(selectedOptionIndex).toString())
                            callback.onSelected(selectedOptionIndex)
                        }

                    },
                    defaultSelectedOptionIndex = currentlySelectedIndex
                )

            }
        }

    }

}