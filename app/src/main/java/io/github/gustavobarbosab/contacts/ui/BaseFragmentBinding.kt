package io.github.gustavobarbosab.contacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragmentBinding<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T
    abstract val layoutResourceId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,layoutResourceId,container,false)
        onCreateView(savedInstanceState)
        return binding.root
    }


    abstract fun onCreateView(savedInstanceState: Bundle?)
}