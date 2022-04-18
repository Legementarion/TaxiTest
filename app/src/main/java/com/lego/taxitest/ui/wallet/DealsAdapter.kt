package com.lego.taxitest.ui.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lego.taxitest.R
import com.lego.taxitest.databinding.RvDealItemBinding
import com.lego.taxitest.domain.models.DealType
import com.lego.taxitest.domain.models.WalletTransaction

class DealsAdapter :
    ListAdapter<WalletTransaction, RecyclerView.ViewHolder>(TransactionsDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DealViewHolder).bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DealViewHolder(
            RvDealItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class DealViewHolder(
        private val binding: RvDealItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WalletTransaction) {
            binding.apply {
                tvDealName.text = item.name
                tvCashValue.text = binding.root.context.getString(R.string.sum_value, item.value)
                tvDate.text = item.date
                ivDealTypeIcon.setImageResource(
                    if (item.type == DealType.Income) {
                        tvCashValue.setTextColor(binding.root.context.getColor(R.color.positive))
                        R.drawable.ic_bx_credit_card_black
                    } else {
                        tvCashValue.setTextColor(binding.root.context.getColor(R.color.negative))
                        R.drawable.ic_bx_user
                    }
                )
            }
        }
    }
}

private class TransactionsDiffCallback : DiffUtil.ItemCallback<WalletTransaction>() {

    override fun areItemsTheSame(
        transaction: WalletTransaction,
        newTransaction: WalletTransaction
    ): Boolean {
        return transaction.id == newTransaction.id
    }

    override fun areContentsTheSame(
        oldItem: WalletTransaction,
        newItem: WalletTransaction
    ): Boolean {
        return oldItem == newItem
    }
}
