package com.capstone.cuacatani.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.cuacatani.ViewModelFactory
import com.capstone.cuacatani.data.Result
import com.capstone.cuacatani.data.pref.UserModel
import com.capstone.cuacatani.databinding.ActivityLoginBinding
import com.capstone.cuacatani.response.LoginResult
import com.capstone.cuacatani.ui.main.MainActivity
import com.capstone.cuacatani.ui.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCreate.setOnClickListener {
            val signupIntent = Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }
//        isAlreadyLogin(this)

        val btnLogin = binding.btnLogin
        val email = binding.emailEditText
        val password = binding.passEditText

        if (Build.VERSION.SDK_INT >= 33) {
            val data = intent.getParcelableExtra("extra_email_username", UserModel::class.java)
            if (data != null) {
                loginUser(data.email.toString(), data.password.toString())
            }
        } else {
            val data = intent.getParcelableExtra<UserModel>("extra_email_username")
            if (data != null) {
                loginUser(data.email.toString(), data.password.toString())
            }
        }

        btnLogin.setOnClickListener {
            if (password.text?.isEmpty() == true) {
                password.error = "Please fill it right"
            }

            if (email.text?.isEmpty() == true) {
                email.error = "Please fill it right"
            }
            if (password.error == null && email.error == null) {
                loginUser(email.text.toString(), password.text.toString())
            }
        }

        playAnimation()
        supportActionBar?.hide()
    }

    private fun isAlreadyLogin(context: Context) {
        viewModel.getSession(context).observe(this) { token ->
            if (token != null) {
                if (token.name.toString().isNotBlank() && token.email.toString().isNotBlank()) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun loginUser(email: String, password: String){
        viewModel.loginUser(email, password).observe(this){ output ->
            if (output != null){
                when (output){
                    is Result.Loading ->{
                        showLoading()
                    }
                    is Result.Success ->{
                        binding.progressBar.visibility = View.GONE
                        val info = output.data
                        if (info.error == false) {
                            viewModel.saveSession(info.loginResult, this)
                            val mainActivity = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(mainActivity)
                            finish()
                        }
                    }
                    is Result.Error ->{
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, output.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.emailEditText.isCursorVisible = false
        binding.passEditText.isCursorVisible = false
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imgLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val emailTextView =
            ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(100)
        val textAsk = ObjectAnimator.ofFloat(binding.tvAcc, View.ALPHA, 1F).setDuration(100)
        val textSignup = ObjectAnimator.ofFloat(binding.tvCreate, View.ALPHA, 1F).setDuration(100)

        val together = AnimatorSet().apply {
            playTogether(textAsk, textSignup)
        }

        AnimatorSet().apply {
            playSequentially(
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login,
                together
            )
            startDelay = 100
        }.start()
    }

}