package com.example.secondassessment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


//    private var mAuth: FirebaseAuth? = null

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        mAuth = FirebaseAuth.getInstance();
        database = Firebase.database.getReference("Users")
        if (database != null) {
            Toast.makeText(applicationContext, "Not Database null", Toast.LENGTH_SHORT).show()
        }


        btnLogin.setOnClickListener(View.OnClickListener {
//            loginOrSignUp()
            login()
        })
    }

    fun login() {

        var query: Query =
            database.orderByChild("email").equalTo(etEmail.text.toString()).limitToFirst(1)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(
                        applicationContext,
                        snapshot.childrenCount.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    for (childSnapshot in snapshot.children) {
                        var user = childSnapshot.getValue(EmpModel::class.java)
                        if (user != null) {
                            if (user.email.equals(
                                    etEmail.text.toString(),
                                    false
                                ) && user.password.equals(etPassword.text.toString(), false)
                            ) {
                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        AddEmpActivity::class.java
                                    )
                                )
                            }
                        }


                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

//        database.equalTo("ranaamir347@gmail.com","email").addListenerForSingleValueEvent(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Toast.makeText(applicationContext,snapshot.childrenCount.toString(),Toast.LENGTH_SHORT).show()
//                for (childSnapshot in snapshot.getChildren()) {
//                    var user = childSnapshot.getValue(EmpModel::class.java)
//                    if (user != null)
//                        Toast.makeText(
//                            applicationContext,
//                            user!!.name.toString(),
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })


    }

    var currentUser: FirebaseUser? = null
    override fun onStart() {
        super.onStart()
//        currentUser = mAuth!!.currentUser
//        mAuth!!.signOut()
//        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser == null) {
            Toast.makeText(applicationContext, "User Not Exist", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "   User Exist", Toast.LENGTH_SHORT).show()
        }
    }

    var TAG = "Firebase login"
//    fun loginOrSignUp() {
//
//        var email = etEmail.text.toString()
//        var password = etPassword.text.toString()
//
//        mAuth!!.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
//                override fun onComplete(task: Task<AuthResult>) {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithEmail:success")
//                        val user = mAuth!!.currentUser
//                        Toast.makeText(
//                            applicationContext,
//                            "Sign in successfully",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        user!!.uid
//
//                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
//                        finish()
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithEmail:failure", task.getException())
//                        Toast.makeText(
//                            applicationContext, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        updateUI(null)
//                    }
//                }
//            })
//
//    }


//if (currentUser == null) {
//
//    mAuth!!.createUserWithEmailAndPassword(
//        email,
//        password
//    ).addOnCompleteListener(object : OnCompleteListener<AuthResult> {
//        override fun onComplete(task: Task<AuthResult>) {
//            if (task.isSuccessful()) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "createUserWithEmail:success")
//                val user = mAuth!!.currentUser
//                updateUI(user)
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w("Main", "createUserWithEmail:failure", task.getException())
//                Toast.makeText(
//                    applicationContext, "Authentication failed.",
//                    Toast.LENGTH_SHORT
//                ).show()
//                updateUI(null)
//            }
//
//        }
//
//    })
//
//
//} else {

}