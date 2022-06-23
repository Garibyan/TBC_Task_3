package com.garibyan.armen.tbctask_3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.garibyan.armen.tbctask_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().length == 1 && s.toString().startsWith("0")) {
                    s.clear()
                }
            }
        })

        binding.btnSave.setOnClickListener {
            when (false) {
                !isAllFilled() ->
                    Toast.makeText(this, R.string.isEmptyFilds, Toast.LENGTH_SHORT).show()
                isEmailValid() -> {
                    Toast.makeText(this, R.string.invalidEmail, Toast.LENGTH_SHORT).show()
                    binding.edtEmail.error
                }
                isUserNameValid() -> {
                    Toast.makeText(this, R.string.invalidUserName, Toast.LENGTH_SHORT).show()
                    binding.edtUserName.error
                }
                isAgeValid() -> {
                    Toast.makeText(this, R.string.invalidAge, Toast.LENGTH_SHORT).show()
                    binding.edtAge.error
                }
                isPhoneNumberValid() -> {
                    Toast.makeText(this, R.string.invalidPhoneNumber, Toast.LENGTH_SHORT).show()
                    binding.edtPhoneNumber.error
                }
                else -> Toast.makeText(this, R.string.validInput, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnClear.setOnLongClickListener {
            cleareAll()
            true
        }
    }

    private fun isEmailValid(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text).matches()

    private fun isAllFilled(): Boolean = with(binding) {
        return@with edtAge.text.toString().equals("") ||
                edtEmail.text.toString().equals("") ||
                edtFirstName.text.toString().equals("") ||
                edtLastName.text.toString().equals("") ||
                edtUserName.text.toString().equals("") ||
                edtPhoneNumber.text.toString().equals("")
    }

    private fun isUserNameValid(): Boolean =
        binding.edtUserName.text.toString().length >= 10

    private fun isPhoneNumberValid(): Boolean =
        android.util.Patterns.PHONE.matcher(binding.edtPhoneNumber.text).matches()

    // mtel da dadebit rixcvze shemowmeba ar gamiketebia, radgan xml-shi inputType number maqvs mititebuli
    // nulsac ver chawer, line 19-27
    private fun isAgeValid(): Boolean = binding.edtAge.text.toString().toInt() < 150

    private fun cleareAll() {
        binding.edtEmail.setText("")
        binding.edtUserName.setText("")
        binding.edtFirstName.setText("")
        binding.edtLastName.setText("")
        binding.edtAge.setText("")
        binding.edtPhoneNumber.setText("")
        Toast.makeText(this, R.string.allCleared, Toast.LENGTH_SHORT).show()
    }
}