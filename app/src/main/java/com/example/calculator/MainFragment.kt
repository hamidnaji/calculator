package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.calculator.calculation.converting.Conversion
import com.example.calculator.calculation.evalution.Execution
import com.example.calculator.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters


    private lateinit var resultText: TextView
    private lateinit var expressionEditText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        resultText = binding.textViewResult
        expressionEditText = binding.textViewExpression


        addOnClickListener(binding)//add buttons and operators to text view

        //removes one character of expression
        binding.btnRemove.setOnClickListener {
            //condition to avoid exception
            if (expressionEditText.text.toString() != "") {
                val stringBuilder = StringBuilder(expressionEditText.text.toString())
                val removeRange =
                    stringBuilder.removeRange(expressionEditText.length() - 1, expressionEditText.length())
                expressionEditText.text = removeRange.toString()
            }
        }


        //clear expression
        binding.btnClear.setOnClickListener {
            expressionEditText.text = ""
            resultText.text = ""
        }



        binding.btnEquationOperator.setOnClickListener {
            if (expressionEditText.text.toString() == "")
                return@setOnClickListener


                //to convert infix expression to postfix
                val conversion = Conversion()
                val postfix = conversion.infixToPostfix(expressionEditText.text.toString())

                //to convert postfix expression to number
                val execution = Execution()
                val resultNumber = execution.evaluate(postfix)


                val validationResult = validateExpression(expressionEditText.text.toString())


                if (!validationResult)
                    resultText.text = "Invalid expression"
                else if (resultNumber.contains("Invalid"))
                    resultText.text = "Invalid expression"
                else
                    resultText.text = resultNumber
            
        }








        return binding.root
    }

    private fun addOnClickListener(binding: FragmentMainBinding) {


        binding.btnZero.setOnClickListener{
            getTextOfButton(it)
        }
        binding.btnOne.setOnClickListener {
            getTextOfButton(it)
        }

        binding.btnTwo.setOnClickListener {
            getTextOfButton(it)
        }

        binding.btnThree.setOnClickListener {
            getTextOfButton(it)
        }

        binding.btnFour.setOnClickListener {
            getTextOfButton(it)
        }


        binding.btnFive.setOnClickListener {
            getTextOfButton(it)
        }


        binding.btnSix.setOnClickListener {
            getTextOfButton(it)
        }


        binding.btnSeven.setOnClickListener {
            getTextOfButton(it)
        }
        binding.btnEight.setOnClickListener {
            getTextOfButton(it)
        }

        binding.btnNine.setOnClickListener {
            getTextOfButton(it)
        }


        binding.btnPlusOperator.setOnClickListener {
            getTextOfButton(it)
        }
        binding.btnMinusOperator.setOnClickListener {
            getTextOfButton(it)
        }
        binding.btnDivideOperator.setOnClickListener {
            getTextOfButton(it)
        }
        binding.btnMultiplyOperator.setOnClickListener {
            getTextOfButton(it)
        }
        binding.btnOpenPar.setOnClickListener {
            getTextOfButton(it)
        }

        binding.btnClosePar.setOnClickListener {
            getTextOfButton(it)
        }
    }

    private fun getTextOfButton(view: View?) {


        val text = expressionEditText.text
        val button = view as Button
        expressionEditText.text = text.toString() + button.text.toString()
    }


//    returns false if expression has error otherwise returns true
    private fun validateExpression(exp: String): Boolean {

        val len = exp.length
        var result1 = true // to avoid two repeated operator --> 12++
        var result2 = true // to avoid coming operator at the end of expression --> 1+
        var result3 = true // to avoid numbers more than one digit

        for (i in 0..len - 2)
            if (isOperator(exp[i]) && isOperator(exp[i + 1]))
                result1 = false


        if (isOperator(exp[len - 1]))
            result2 = false

        for (i in 0..len - 2)
            if (isDigit(exp[i]) && isDigit(exp[i + 1]))
                result3 = false

        if (!result3)
            Toast.makeText(context, "just one digit numberes", Toast.LENGTH_SHORT).show()



        return result1 && result2 && result3

    }

    private fun isOperator(char: Char): Boolean {


        return char == '+' || char == '-' || char == '*' || char == '/'


    }

    private fun isDigit(char: Char): Boolean {
        return char in '0'..'9'
    }
}