package com.abdhilabs.home.ui

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.doOnTextChanged
import com.abdhilabs.coreandroid.abstraction.BaseActivityBinding
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.entity.ItemClickListener
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.coreandroid.utils.extension.gone
import com.abdhilabs.coreandroid.utils.extension.toast
import com.abdhilabs.coreandroid.utils.extension.visible
import com.abdhilabs.home.R
import com.abdhilabs.home.adapter.GameAdapter
import com.abdhilabs.home.databinding.ActivityHomeBinding
import com.abdhilabs.home.di.HomeInjector
import com.abdhilabs.navigation.DetailNavigator
import com.abdhilabs.navigation.FavoriteNavigator
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeActivity : BaseActivityBinding<ActivityHomeBinding>(), ItemClickListener {

    @Inject
    lateinit var vm: HomeViewModel

    private val adapterGame by lazy { GameAdapter() }

    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun setupInjector() = HomeInjector.of(this)

    override fun setupView(binding: ActivityHomeBinding) {
        vm.getGames()
        initSwipeRefresh()
        observeGames()
        adapterGame.setItemClickListener(this)
        binding.etSearch.doOnTextChanged { text, _, _, _ -> vm.getSearchGames(text.toString()) }
        binding.rvGames.adapter = adapterGame
    }

    private fun initSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.etSearch.setText("")
            vm.getGames()
        }
    }

    private fun observeGames() {
        vm.games.observe(this) {
            when (it) {
                is Result.Loading -> {
                    with(binding) {
                        swipeRefresh.isRefreshing = true
                        tvError.gone()
                    }
                }
                is Result.Success -> {
                    adapterGame.fillGames(it.data)
                    with(binding) {
                        swipeRefresh.isRefreshing = false
                        tvError.gone()
                    }
                }
                is Result.Error -> {
                    with(binding) {
                        swipeRefresh.isRefreshing = false
                        tvError.visible()
                        tvError.text = it.errorMessage
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_to_favorite -> itemFavoriteClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun itemFavoriteClicked() {
        try {
            moveToFavoriteActivity()
//            installFavoriteModule()
        } catch (e: Exception) {
            e.printStackTrace()
            toast("Module not found")
        }
    }

    private fun installFavoriteModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleFavorite = "favourite"
        if (splitInstallManager.installedModules.contains(moduleFavorite)) {
            moveToFavoriteActivity()
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleFavorite)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    toast("Success installing module")
                    moveToFavoriteActivity()
                }
                .addOnFailureListener {
                    toast("Error installing module, ${it.message}")
                }
        }
    }

    private fun moveToFavoriteActivity() {
        FavoriteNavigator
            .getFavoritePageIntent()
            .also(::startActivity)
    }

    override fun onItemClick(item: Game) {
        DetailNavigator
            .getDetailPageIntent(item.id)
            .also(::startActivity)
    }
}