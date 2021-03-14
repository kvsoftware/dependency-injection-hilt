package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.content.Context
import android.content.Intent
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

    override fun getViewModel(): BaseViewModel? = null

    override fun getViewBinding(): ActivityBaseToolbarBinding =
        ActivityBaseToolbarBinding.inflate(layoutInflater)

    override fun initializeView() {
        getArgument()?.let {
            setupToolbar(title = it.countryDataModel.country, showBackButton = true)
            replaceFragment(R.id.fragment_container, CountryDetailFragment.newInstance(it))
        }
    }

    override fun initializeObserver() {
    }

    private fun getArgument(): CountryDetailArgument? {
        return intent?.getParcelableExtra(ARGUMENT) as CountryDetailArgument?
    }

}