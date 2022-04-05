package com.multisync.todoapplication.fragment.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.multisync.todoapplication.R

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val veiw = inflater.inflate(R.layout.fragment_list, container, false)
        val floatingBtn = veiw?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val listLayout = veiw?.findViewById<ConstraintLayout>(R.id.listLayout)
        listLayout?.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        // Set Menu
        setHasOptionsMenu(true)

        return veiw
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}