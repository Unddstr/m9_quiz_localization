package com.example.quizlocalization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizlocalization.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("param1", getAnswers())
            }
            getAnswers()
            findNavController().navigate(
                R.id.action_questionFragment_to_resultFragment,
                args = bundle
            )
        }

        binding.questionRadioGroup1.alpha = 0f
        binding.questionRadioGroup1.animate().apply {
            duration = 1000
            alpha(1f)
        }.start()

        binding.questionRadioGroup2.alpha = 0f
        binding.questionRadioGroup2.animate().apply {
            duration = 2000
            alpha(1f)
        }.start()

        binding.questionRadioGroup3.alpha = 0f
        binding.questionRadioGroup3.animate().apply {
            duration = 3000
            alpha(1f)
        }.start()

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_questionFragment_to_welcomeFragment)
        }
    }

    private fun getAnswers(): String {
        var correctAnswersCount = 0

        if (binding.questionRadioGroup1.checkedRadioButtonId == binding.answer1RadioButton2.id) correctAnswersCount++
        if (binding.questionRadioGroup2.checkedRadioButtonId == binding.answer2RadioButton1.id) correctAnswersCount++
        if (binding.questionRadioGroup3.checkedRadioButtonId == binding.answer3RadioButton2.id) correctAnswersCount++

        return correctAnswersCount.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}