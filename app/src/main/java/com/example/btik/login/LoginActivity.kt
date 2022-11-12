package com.example.btik.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import com.example.btik.R
import com.example.btik.dashboard.DashboardActivity
import com.example.btik.databinding.ActivityLoginBinding
import com.example.btik.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.toRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.loginBtn.setOnClickListener{
            startActivity(Intent(this, DashboardActivity ::class.java))
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()

            //validasi empty email
            if (email.isEmpty()){
                binding.loginEmail.error = "Email Must fill"
                binding.loginPassword.requestFocus()
                return@setOnClickListener
            }

            //validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.loginEmail.error = "Email Not Valid"
                binding.loginEmail.requestFocus()
                return@setOnClickListener
            }

            //validasi password
            if (password.isEmpty()){
                binding.loginPassword.error = "Password Must fill"
                binding.loginPassword.requestFocus()
                return@setOnClickListener
            }

            //validasi panjang password
            if (password.length < 8){
                binding.loginPassword.error = "Password at least 8 Character"
                binding.loginPassword.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)

        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity :: class.java))
                }
                else{
                    Toast.makeText(this,"${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}