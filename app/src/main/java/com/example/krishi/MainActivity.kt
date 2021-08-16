package com.example.krishi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest


private const val CAMERA_PERMISSION_CODE = 1
private const val CAMERA_REQUEST_CODE = 2
class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)



        loadData()

        submit_button.setOnClickListener {

            saveData()

        }




        image_button.setOnClickListener {
            if(ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                )== PackageManager.PERMISSION_GRANTED
            ){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
            else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }

        fetch_button.setOnClickListener {

            val intent = Intent(this,RecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        val tvName = sharedPreferences.getString("NAME","")
        name.text = tvName

        val tvEmail = sharedPreferences.getString("EMAIL","")
        email.text = tvEmail

    }

    private fun saveData() {
        val xname = text_name.text.toString()
        val xemail = text_email.text.toString()

        if (xemail.matches(emailPattern.toRegex())) {
            Toast.makeText(applicationContext, "Data Saved",
                Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Invalid email address",
                Toast.LENGTH_SHORT).show()
        }

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("NAME",xname)
        editor.putString("EMAIL",xemail)
        editor.apply()
    }

}