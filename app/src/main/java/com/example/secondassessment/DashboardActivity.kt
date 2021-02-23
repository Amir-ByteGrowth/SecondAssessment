package com.example.secondassessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add_emp.*
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        mAuth = FirebaseAuth.getInstance();

        imgLogout.setOnClickListener(View.OnClickListener {
            onDestroy()
        })
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
           startActivity(Intent(applicationContext,AddEmpActivity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mAuth != null) {
            mAuth!!.signOut()
        }
    }


}