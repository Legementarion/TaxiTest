package com.lego.taxitest.ui.wallet

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.lego.taxitest.R
import com.lego.taxitest.databinding.ActivityWalletBinding


class WalletActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWalletBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        navController = findNavController(R.id.nav_host_fragment_content_wallet)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_wallet) {
                binding.toolbarTitle.text = getString(R.string.wallet_label_toolbar)
            } else {
                binding.toolbarTitle.text = getString(R.string.deal_label_toolbar)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (navController.currentDestination?.id == R.id.nav_wallet) {
                finish()
            } else {
                navController.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}