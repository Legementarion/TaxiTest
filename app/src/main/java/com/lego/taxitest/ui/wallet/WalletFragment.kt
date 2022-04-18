package com.lego.taxitest.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lego.taxitest.R
import com.lego.taxitest.databinding.FragmentWalletBinding
import com.lego.taxitest.ui.addItemDecorationWithoutLastDivider
import org.koin.androidx.viewmodel.ext.android.viewModel


class WalletFragment : Fragment() {

    private lateinit var binding: FragmentWalletBinding
    private val viewModel: WalletViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dealLabel.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_nav_wallet_to_nav_deal)
        }

        val adapter = DealsAdapter()
        binding.rvDeals.adapter = adapter
        binding.rvDeals.addItemDecorationWithoutLastDivider()
        binding.rvDeals.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: DealsAdapter) {
        viewModel.walletTransactions.observe(viewLifecycleOwner) { deals ->
            adapter.submitList(deals.asReversed())
        }
    }

}