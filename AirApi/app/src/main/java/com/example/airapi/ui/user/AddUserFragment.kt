package com.example.airapi.ui.user

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider

import com.example.airapi.R
import androidx.lifecycle.ViewModelProviders
import com.example.airapi.DAO.ModelDao
import com.example.airapi.DAO.Repository
import com.example.airapi.models.User
import kotlinx.android.synthetic.main.fragment_user_add.*

class AddUserFragment : Fragment(){
    private lateinit var addUserViewModel: AddUserViewModel
    private lateinit var addUserViewModelFactory: AddUserViewModelFactory
    private lateinit var database: Repository



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_user_add, container, false)


        //addUserViewModelFactory = AddUserViewModelFactory(database)
        addUserViewModel = ViewModelProviders.of(this, addUserViewModelFactory).get(AddUserViewModel::class.java)

        val button: Button = root.findViewById(R.id.button_add)
        val editName: EditText = root.findViewById(R.id.edit_name)
        val editSurname: EditText = root.findViewById(R.id.edit_surname)
        val editEmail: EditText = root.findViewById(R.id.edit_email)
        val editPassword: EditText = root.findViewById(R.id.edit_firstPassword)
//        addUserViewModel.insertUser(User(1,"Bartek", "Poskart", "bartek.poskart@gmail.com", "bartek"))


        button.setOnClickListener {

            val user = User(
                name = editName.text.toString(),
                surname = editSurname.text.toString(),
                email = editEmail.text.toString(),
                password = editPassword.text.toString()
            )
            addUserViewModel.insertUser(user)
            editName.setText("")
            editSurname.setText("")
            editEmail.setText("")
            editPassword.setText("")
            edit_secondPassword.setText("")
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
