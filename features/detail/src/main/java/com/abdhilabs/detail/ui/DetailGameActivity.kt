package com.abdhilabs.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.abdhilabs.coreandroid.abstraction.BaseActivityBinding
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.coreandroid.db.entity.toGame
import com.abdhilabs.coreandroid.utils.extension.gone
import com.abdhilabs.coreandroid.utils.extension.setImageFromUrl
import com.abdhilabs.coreandroid.utils.extension.toast
import com.abdhilabs.coreandroid.utils.extension.visible
import com.abdhilabs.coreandroid.utils.formatter.DateTimeFormatter
import com.abdhilabs.detail.R
import com.abdhilabs.detail.databinding.ActivityDetailGameBinding
import com.abdhilabs.detail.di.DetailInjector
import com.airbnb.deeplinkdispatch.DeepLink
import javax.inject.Inject

@DeepLink("gamelabs://detail/{id}")
class DetailGameActivity : BaseActivityBinding<ActivityDetailGameBinding>() {

    @Inject
    lateinit var vm: DetailGameViewModel

    private lateinit var game: Game

    private var menu: Menu? = null
    private var state: Boolean = false

    companion object {
        private const val ID_GAME = "id"
    }

    private var idGame: String = ""

    override val bindingInflater: (LayoutInflater) -> ActivityDetailGameBinding
        get() = ActivityDetailGameBinding::inflate

    override fun setupInjector() = DetailInjector.of(this)

    override fun setupView(binding: ActivityDetailGameBinding) {
        setToolbar()
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val params: Bundle? = intent.extras
            idGame = params?.getString(ID_GAME).toString()
        }
        vm.getIsGameFavorite(idGame.toInt())
        observeGameFavorite(idGame.toInt())
    }

    private fun setToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_detail)
    }

    private fun observeGameFavorite(idGame: Int) {
        vm.gameFavorite.observe(this) {
            state = it.isNotEmpty()
            setFavoriteState(state)
            if (it.isNotEmpty()) {
                game = it.first().toGame()
                setUI(it.first().toGame())
            } else {
                vm.getGame(idGame)
                observeGame()
            }
        }
    }

    private fun observeGame() {
        vm.game.observe(this) {
            when (it) {
                is Result.Loading -> binding.progressBar.visible()
                is Result.Success -> {
                    binding.progressBar.gone()
                    setUI(it.data)
                    game = it.data
                }
                is Result.Error -> {
                    binding.progressBar.gone()
                    toast(it.errorMessage.toString())
                }
            }
        }
    }

    private fun setUI(item: Game) {
        with(binding) {
            imgBanner.setImageFromUrl(item.backgroundImage)
            tvTitle.text = item.name
            tvRating.text = getString(R.string.text_rating, item.rating.toString())
            tvGenres.text = getString(R.string.text_genres, item.genres.joinToString(", "))
            tvReleaseDate.text = getString(
                R.string.text_release_date,
                DateTimeFormatter.getDateFromString(item.released)
            )
            tvDescription.text = getString(R.string.text_overview, item.description)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        setFavoriteState(state)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.item_favourite -> {
                when (state) {
                    true -> {
                        vm.deleteFromFavorite(game.id)
                        toast("Deleted from Favorite")
                    }
                    else -> {
                        vm.addToFavorite(game)
                        toast("Added to Favorite")
                    }
                }
                state = !state
                setFavoriteState(state)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.item_favourite)
        when (state) {
            true -> menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited)
            false -> menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }
}