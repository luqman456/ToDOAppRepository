package com.multisync.todoapplication.fragment.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.multisync.todoapplication.R
import com.multisync.todoapplication.data.models.Priority
import com.multisync.todoapplication.data.models.ToDoData
import com.multisync.todoapplication.data.viewmodel.ToDoViewModel
import com.multisync.todoapplication.fragment.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        view.priorities_spinner.onItemSelectedListener = sharedViewModel.listener
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val title = title_et.text.toString()
        val description = description_et.text.toString()
        val priority = priorities_spinner.selectedItem.toString()
        val validation = sharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val newData = ToDoData(0, title, sharedViewModel.parsePriority(priority), description)
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

}