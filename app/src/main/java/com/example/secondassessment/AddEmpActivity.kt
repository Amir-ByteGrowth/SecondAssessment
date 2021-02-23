package com.example.secondassessment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_emp.*

class AddEmpActivity : AppCompatActivity(), EmpAdaptor.ItemClickListner {


    private lateinit var database: DatabaseReference

    var empList = arrayListOf<EmpModel>()
    var adaptor: EmpAdaptor = EmpAdaptor(empList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_emp)

        database = Firebase.database.getReference("Users")


        etRecycler.setLayoutManager(LinearLayoutManager(this))
        etRecycler.adapter = adaptor


        imgBack.setOnClickListener(View.OnClickListener { finish() })

        btnSave.setOnClickListener(View.OnClickListener {
            createUser()
        })
        btnUpdate.setOnClickListener(View.OnClickListener {
            if (emp != null) {
                updateEmp()
            }
        })
        getAllEmp()
    }

    var TAG = "AddEmp"
    fun createUser() {
        val userId: String = database.push().getKey()!!
        var user = EmpModel(
            name = etEmpName.text.toString(),
            password = etPassword.text.toString(),
            email = etEmail.text.toString(),
            location = etLocation.text.toString(),
            lat = 0.00,
            lon = 0.0, type = "user", token = userId
        )

        database.child(userId).setValue(user, object : DatabaseReference.CompletionListener {
            override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                etEmpName.setText("")
                etEmail.setText("")
                etPassword.setText("")
                etLocation.setText("")

                Toast.makeText(applicationContext, "Record entered", Toast.LENGTH_SHORT).show()
            }

        });

    }


    fun delEmp() {
        database.child(emp!!.token).removeValue(object : DatabaseReference.CompletionListener {
            override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                Toast.makeText(applicationContext, "Data Removed", Toast.LENGTH_SHORT).show()
                emp = null
            }
        })
    }

    fun updateEmp() {

        emp!!.name = etEmpName.text.toString()
        emp!!.email = etEmail.text.toString()
        emp!!.password = etPassword.text.toString()
        emp!!.location = etLocation.text.toString()

        database.child(emp!!.token).setValue(emp, object : DatabaseReference.CompletionListener {
            override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                Toast.makeText(applicationContext, "Updated", Toast.LENGTH_SHORT).show()
                emp = null
                btnUpdate.visibility = View.GONE
                btnSave.visibility = View.VISIBLE
                etEmpName.setText("")
                etEmail.setText("")
                etPassword.setText("")
                etLocation.setText("")
            }

        })
    }

    fun getAllEmp() {

        var query: Query = database.orderByChild("type").equalTo("user")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
//                    Toast.makeText(
//                        applicationContext,
//                        snapshot.childrenCount.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()

                    empList.clear()
                    adaptor.clearData()
                    for (childSnapshot in snapshot.children.reversed()) {
                        var user = childSnapshot.getValue(EmpModel::class.java)
                        empList.add(user!!)

                    }
                    adaptor.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    var emp: EmpModel? = null

    override fun onItemClick(empModel: EmpModel) {
        emp = empModel
        btnUpdate.visibility = View.VISIBLE
        btnSave.visibility = View.GONE
        etEmpName.setText(empModel.name)
        etEmail.setText(empModel.email)
        etPassword.setText(empModel.password)
        etLocation.setText(empModel.location)

    }

    override fun delEmp(empModel: EmpModel) {
        emp = empModel
        delEmp()
    }

}