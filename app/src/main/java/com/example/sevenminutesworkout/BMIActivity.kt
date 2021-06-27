package com.example.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_b_m_i.*
import kotlinx.android.synthetic.main.activity_finish.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    private val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

        setSupportActionBar(toolbar_bmi)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_bmi.setNavigationOnClickListener { onBackPressed() }

        btn_calculate.setOnClickListener {
            if(currentVisibleView == METRIC_UNITS_VIEW) {
                if (validateMetricUnits()) {
                    val height: Float = et_metric_height.text.toString().toFloat() / 100
                    val weight: Float = et_metric_weight.text.toString().toFloat()

                    val bmi = weight / (height * height)
                    displayBMIResult(bmi)
                }
                else {
                    Toast.makeText(
                            this,
                            "Please enter a valid number",
                            Toast.LENGTH_LONG
                    ).show()
                }
            }
            else {
                if(validateUSUnits()) {
                    val usWeight: Float = et_us_weight.text.toString().toFloat()
                    val usFeet: String = et_us_feet.text.toString()
                    val usInch: String = et_us_inch.text.toString()

                    val heightValue = usInch.toFloat() + usFeet.toFloat() * 12

                    val bmi = 703 * (usWeight / (heightValue * heightValue))

                    displayBMIResult(bmi)
                }
                else {
                    Toast.makeText(
                            this,
                            "Please enter a valid number",
                            Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        makeVisibleMetricUnitsView()

        rg_units.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.metric) {
                makeVisibleMetricUnitsView()
            }
            else {
                makeVisibleUSUnitsView()
            }
        }
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        til_metric_weight.visibility = View.VISIBLE
        til_metric_height.visibility = View.VISIBLE

        et_metric_height.text?.clear()
        et_metric_weight.text?.clear()

        til_us_weight.visibility = View.GONE
        US_units_height.visibility = View.GONE

        displayBMI.visibility = View.GONE
    }

    private fun makeVisibleUSUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        til_metric_weight.visibility = View.GONE
        til_metric_height.visibility = View.GONE

        et_us_feet.text?.clear()
        et_us_inch.text?.clear()
        et_us_weight.text?.clear()

        til_us_weight.visibility = View.VISIBLE
        US_units_height.visibility = View.VISIBLE

        displayBMI.visibility = View.GONE
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String;
        val bmiDescription: String;

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        displayBMI.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        bmi_value.text = bmiValue
        bmi_type.text = bmiLabel
        bmi_desc.text = bmiDescription

    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (et_metric_weight.text.toString().isEmpty()) {
            isValid = false
        }
        else if(et_metric_height.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true

        when {
            et_us_feet.text.toString().isEmpty() -> {
                isValid = false
            }
            et_us_inch.text.toString().isEmpty() -> {
                isValid = false
            }
            et_us_weight.text.toString().isEmpty() -> {
                isValid = false
            }
        }

        return isValid
    }
}