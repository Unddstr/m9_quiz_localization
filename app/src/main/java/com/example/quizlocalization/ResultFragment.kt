package com.example.quizlocalization

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizlocalization.databinding.FragmentResultBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ObjectAnimator.ofArgb(
            binding.resultTitle,
            "textColor",
            R.color.violet,
            Color.BLACK
        ).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        (AnimatorInflater.loadAnimator(this.context, R.animator.custom_animation) as AnimatorSet).apply {
            setTarget(binding.result)
            start()
        }

        binding.result.text = "$param1/3"
        binding.backToStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_questionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}