package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val bundle = this.arguments
        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()
        println(generate(min, max).toString())


        backButton?.setOnClickListener {
            val firstFragment: FirstFragment = FirstFragment.newInstance(result?.text?.toString()?.toInt() ?: 0)
            val bundle = Bundle()
            bundle.putInt("PREVIOUS_RESULT", result?.text?.toString()?.toInt() ?: 0)
            firstFragment.arguments = bundle
            this.activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, firstFragment)?.addToBackStack("fragment")?.commit()

            // TODO: implement back
        }
    }

    private fun generate(min: Int, max: Int): Int {
        if(max >= min) {
            // TODO: generate random number
            return Random.nextInt(min, max + 1)
        }
        else{
            return Random.nextInt(max, min + 1)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.getInt(MIN_VALUE_KEY)
            args.getInt(MAX_VALUE_KEY)

            fragment.arguments = args

            // TODO: implement adding arguments

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}