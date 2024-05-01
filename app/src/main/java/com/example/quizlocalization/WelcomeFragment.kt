package com.example.quizlocalization

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizlocalization.databinding.FragmentWelcomeBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    private val calendar: Calendar = Calendar.getInstance()

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("dd-MM-yy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_questionFragment)
        }

        binding.birthdayButton.setOnClickListener {
            val constraints = CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .build()

            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraints)
                .setTitleText(resources.getString(R.string.set_birthday_title))
                .build()

            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
                Snackbar.make(
                    binding.birthdayButton,
                    dateFormat.format(calendar.time),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            dateDialog.show(parentFragmentManager, "DatePicker")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}