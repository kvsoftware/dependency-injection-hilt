package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.kvsoftware.dependencyinjectionhilt.R
import com.kvsoftware.dependencyinjectionhilt.databinding.ActivityBaseToolbarBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseActivity
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailActivity : BaseActivity<ActivityBaseToolbarBinding>() {

    companion object {
        private const val ARGUMENT = "argument"

        fun start(context: Context, argument: CountryDetailArgument) {
            val intent = Intent(context, CountryDetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra(ARGUMENT, argument)
            ActivityCompat.startActivity(context, intent, null)
        }
    }

    private val viewModel: CountryDetailActivityViewModel by viewModels()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getViewBinding(): ActivityBaseToolbarBinding =
        ActivityBaseToolbarBinding.inflate(layoutInflater)

    override fun initializeView() {
        getArgument()?.let { arguments ->
            setupToolbar(
                title = arguments.countryDataModel.country,
                showBackButton = true,
                option2ClickListener = { viewModel.favorite(arguments.countryDataModel) }
            )
            replaceFragment(R.id.fragment_container, CountryDetailFragment.newInstance(arguments))
            viewModel.getFavoriteStatus(arguments.countryDataModel)
        }
    }

    override fun initializeObserver() {
        viewModel.isFavorited.observe(this, {
            getOptionMenu2()?.setImageResource(if (it) R.drawable.ic_favorite else R.drawable.ic_unfavorite)
        })
    }

    private fun getArgument(): CountryDetailArgument? =
        intent?.getParcelableExtra(ARGUMENT) as CountryDetailArgument?

}