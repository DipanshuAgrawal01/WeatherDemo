package com.example.weatherdemo.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.example.weatherdemo.BR

abstract class BaseDialogFragment<VM : ViewModel, Binding : ViewDataBinding> : DialogFragment() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected abstract val viewModel: ViewModel

    protected lateinit var binding: Binding
        private set

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding =
            DataBindingUtil.inflate(requireActivity().layoutInflater, getLayoutResId(), null, false)
        return AlertDialog.Builder(requireActivity()).setView(binding.root).create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.apply {
        setVariable(BR.viewmodel, viewModel)
        lifecycleOwner = viewLifecycleOwner
    }.root
}
