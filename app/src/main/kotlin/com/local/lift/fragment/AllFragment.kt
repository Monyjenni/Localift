package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.local.lift.adapter.AllDataAdapter
import com.local.lift.api.State
import com.local.lift.viewModel.AllDataViewModel
import com.local.locallift.R
import com.local.locallift.databinding.FragmentAllBinding


class AllFragment : Fragment() {
    private val viewModel by viewModels<AllDataViewModel> ()
    private lateinit var binding:FragmentAllBinding
    private lateinit var allDataAdapter: AllDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding=FragmentAllBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allDataAdapter= AllDataAdapter()
        binding.recycleData.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter=allDataAdapter
        }
        viewModel.dataState.observe(viewLifecycleOwner){ dataState->
            when(dataState.state){
                State.SUCCESS->allDataAdapter.submitList(dataState.data)
                State.ERROR->Toast.makeText(context, "Error loading data. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.loadData()

    }

}