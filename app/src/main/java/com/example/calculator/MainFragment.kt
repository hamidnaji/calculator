package com.example.calculator

import Conversion
import Evaluation
import Sepration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil

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
                val length = expressionEditText.length()

                val removedRange =
                    stringBuilder.removeRange(
                        length - 1,
                        length
                    )
                expressionEditText.text = removedRange.toString()
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


            try {


                //to convert infix expression to postfix
                val sepration = Sepration()
                val sepratedList = sepration.getSepratedList(expressionEditText.text.toString())

                val conversion = Conversion(sepratedList)
                val postfixList = conversion.infixToPostfix()

                val evaluation = Evaluation(postfixList)
                val resultNumber = evaluation.postfixToNumber()

                resultText.text = resultNumber.toString()

            } catch (e: NoSuchElementException) {
                resultText.text = "Invalid Expression"

            }


        }








        return binding.root
    }

    private fun addOnClickListener(binding: FragmentMainBinding) {


        binding.btnZero.setOnClickListener {
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


        binding.btnDouble.setOnClickListener {
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

        val stringBuilder = StringBuilder(expressionEditText.text.toString())
        val button = view as Button

        val id = button.id

        if (id == R.id.btn_minus_operator)
            stringBuilder.append("-")
        else if (id == R.id.btn_double)
            stringBuilder.append(".")
        else
            stringBuilder.append(button.text.toString())


        expressionEditText.text = stringBuilder.toString()

    }


    private fun isOperator(char: Char): Boolean {


        return char == '+' || char == '-' || char == '*' || char == '/'


    }

    private fun isDigit(char: Char): Boolean {
        return char in '0'..'9'
    }
}