package com.abdhilabs.gamelabs.favourite.ui

import android.view.LayoutInflater
import android.view.MenuItem
import com.abdhilabs.coreandroid.abstraction.BaseActivityBinding
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.entity.ItemClickListener
import com.abdhilabs.coreandroid.utils.extension.gone
import com.abdhilabs.coreandroid.utils.extension.visible
import com.abdhilabs.gamelabs.favourite.R
import com.abdhilabs.gamelabs.favourite.adapter.GameAdapter
import com.abdhilabs.gamelabs.favourite.databinding.ActivityFavoriteBinding
import com.abdhilabs.gamelabs.favourite.di.FavoriteInjector
import com.abdhilabs.navigation.DetailNavigator
import com.airbnb.deeplinkdispatch.DeepLink
import javax.inject.Inject

@DeepLink("gamelabs://favorite")
class FavoriteActivity : BaseActivityBinding<ActivityFavoriteBinding>(), ItemClickListener {

    @Inject
    lateinit var vm: FavoriteViewModel

    private val adapterGameFavorite by lazy { GameAdapter() }

    override val bindingInflater: (LayoutInflater) -> ActivityFavoriteBinding
        get() = ActivityFavoriteBinding::inflate

    override fun setupInjector() = FavoriteInjector.of(this)

    override fun setupView(binding: ActivityFavoriteBinding) {
        setToolbar()
        vm.getGamesFavorite()
        observeGameFavorite()
        adapterGameFavorite.setItemClickListener(this)
        binding.rvGamesFavorite.adapter = adapterGameFavorite
    }

    private fun setToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.text_title_favorite)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeGameFavorite() {
        vm.games.observe(this) {
            if (it.isEmpty()) binding.tvErrorFavorite.visible()
            else {
                binding.tvErrorFavorite.gone()
                adapterGameFavorite.fillGames(it)
            }
        }
    }

    override fun onItemClick(item: Game) {
        DetailNavigator
            .getDetailPageIntent(item.id)
            .also(::startActivity)
    }
}