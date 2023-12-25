package com.capstone.cuacatani.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.capstone.cuacatani.ViewModelFactory
import com.capstone.cuacatani.data.Result
import com.capstone.cuacatani.data.pref.UserModel
import com.capstone.cuacatani.databinding.ActivitySignupBinding
import com.capstone.cuacatani.ui.login.LoginActivity

class SignupActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            val signupIntent = Intent(this, LoginActivity::class.java)
            startActivity(signupIntent)
        }

        binding.btnSignup.setOnClickListener {
            val username = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passEditText.text.toString()

            if (password.length < 8) {
                binding.btnSignup.error = "Minimal harus 8 karakter"
            } else {
                loadingCorrect()
                viewModel.signupUser(username, email, password).observe(this) { output ->
                    when (output) {
                        is Result.Loading -> {
                            // Handle loading state if needed
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val response = output.data
                            Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                            sendToLogin(UserModel(email, password))
                        }
                        is Result.Error -> {
                            incorrectData()
                            Toast.makeText(this, "${output.error}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        playAnimation()
        supportActionBar?.hide()
    }

    private fun incorrectData(){
        binding.progressBar.visibility = View.GONE
        binding.nameEditText.isCursorVisible = true
        binding.emailEditText.isCursorVisible = true
        binding.passEditText.isCursorVisible = true
    }

    private fun loadingCorrect(){
        binding.progressBar.visibility = View.GONE
        binding.nameEditText.isCursorVisible = false
        binding.emailEditText.isCursorVisible = false
        binding.passEditText.isCursorVisible = false
    }

    private fun sendToLogin(data: UserModel) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Signup Successful")
        alertDialogBuilder.setMessage("Your account has been successfully created. Do you want to login now?")

        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setPositiveButton("Yes") { dialog, _ ->
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imgSignup, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val nameTextView =
            ObjectAnimator.ofFloat(binding.tvName, View.ALPHA, 1f).setDuration(100)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(100)
        val textAlready = ObjectAnimator.ofFloat(binding.already, View.ALPHA, 1f).setDuration(100)
        val textLogin = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1F).setDuration(100)

        val together = AnimatorSet().apply {
            playTogether(textAlready, textLogin)
        }

        AnimatorSet().apply {
            playSequentially(
                nameTextView,
                nameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                signup,
                together
            )
            startDelay = 100
        }.start()
    }
}