package com.example.btik.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.btik.R
import com.example.btik.databinding.ActivityRegisterBinding
import com.example.btik.login.LoginActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.toLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity ::class.java ))
        }

        binding.regisBtn.setOnClickListener {
            val email = binding.regisEmail.text.toString()
            val password = binding.regisPassword.text.toString()
            var phone = binding.phoneNumber.text.toString()

            //validasi empty email
            if (email.isEmpty()){
                binding.regisEmail.error = "Email Must fill"
                binding.regisEmail.requestFocus()
                return@setOnClickListener
            }

            //validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.regisEmail.error = "Email Not Valid"
                binding.regisEmail.requestFocus()
                return@setOnClickListener
            }

            //validasi password
            if (password.isEmpty()){
                binding.regisPassword.error = "Password Must fill"
                binding.regisPassword.requestFocus()
                return@setOnClickListener
            }

            //validasi panjang password
            if (password.length < 8){
                binding.regisPassword.error = "Password at least 8 Character"
                binding.regisPassword.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)
        }
    }


    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
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