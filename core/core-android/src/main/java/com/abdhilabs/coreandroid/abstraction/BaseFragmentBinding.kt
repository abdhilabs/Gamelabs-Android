package com.abdhilabs.coreandroid.abstraction

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragmentBinding<B: ViewBinding, V: ViewModel>: Fragment() {

    private var _binding: B? = null
    private val binding get() = _binding!!

    lateinit var vm: V

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B
    abstract fun getViewModelClass(): Class<V>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container,false)
        vm = ViewModelProvider(this, factory)[getViewModelClass()]
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(binding)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    abstract fun setupView(binding: B)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}