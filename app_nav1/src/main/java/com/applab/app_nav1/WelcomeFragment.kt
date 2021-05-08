package com.applab.app_nav1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment :Fragment(R.layout.fragment_welcome) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", arguments.toString())

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text_view_username.text=arguments?.getString("username")
        super.onViewCreated(view, savedInstanceState)
        home_button.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()

            findNavController().navigate(action)
        }
    }
}