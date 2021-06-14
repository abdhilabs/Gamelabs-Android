package com.abdhilabs.coreandroid.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivityBinding<B : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> B

    @Suppress("UNCHECKED_CAST")
    protected val binding: B
        get() = _binding as B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setupInjector()
        setContentView(requireNotNull(_binding).root)
        setupView(binding)
    }

    abstract fun setupView(binding: B)

    abstract fun setupInjector()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}