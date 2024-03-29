package com.example.realtimedatabase

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("USERS")

        bt_submit.setOnClickListener { saveData() }
        bt_show.setOnClickListener {
            val i = Intent(this, ShowActivity::class.java)
        }
    }

    private fun saveData() {

        val name = et_nama.text.toString()
        val email = et_email.text.toString()

        val userid = ref.push().key.toString()
        val user = Users(userid, name, email)

        ref.child(userid).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Sucsess", Toast.LENGTH_SHORT).show()
            et_nama.setText("")
            et_email.setText("")

            val i = Intent(this, ShowActivity ::
            class.java)
            startActivity(i)

        }
    }
}
