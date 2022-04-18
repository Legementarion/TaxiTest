package com.lego.taxitest.ui.deal

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.lego.taxitest.R
import com.lego.taxitest.databinding.FragmentDealBinding
import com.lego.taxitest.ui.addDefaultTextChangedListener
import com.lego.taxitest.ui.conditionally
import com.lego.taxitest.ui.toSpinnerMode
import java.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DealFragment : Fragment() {

    private lateinit var binding: FragmentDealBinding
    private val viewModel: DealViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeArray = resources.getStringArray(R.array.deal_type_array)

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            typeArray
        )

        binding.dropdownDealType.setAdapter(adapter)
        binding.dropdownDealType.toSpinnerMode()
        binding.dropdownDealType.setOnItemClickListener { _, _, position, _ ->
            viewModel.setType(position)
        }
        binding.dropdownDealType.setText(typeArray[0], false) //set default

        viewModel.btnEnabled.observe(viewLifecycleOwner) { enabled ->
            binding.btnSubmit.isEnabled = enabled
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.addTransaction(
                binding.etDealName.text.toString(),
                binding.etDealSum.text.toString(),
                binding.etDealDate.text.toString()
            )
        }

        binding.etDealName.addDefaultTextChangedListener(
            binding.tilDealDescription,
            viewModel::setNameValidation
        )
        binding.etDealSum.addDefaultTextChangedListener(
            binding.tilDealSum,
            viewModel::setValueValidation
        )

        viewModel.completed.observe(viewLifecycleOwner) { completed ->
            if (completed) {
                NavHostFragment.findNavController(this).popBackStack()
            }
        }

        setDatePicker()
    }

    private fun setDatePicker() {
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_UTC))

        setCurrentDateOnView(calendar)

        binding.etDealDate.inputType = InputType.TYPE_NULL
        binding.etDealDate.keyListener = null

        binding.etDealDate.setOnClickListener {
            val builder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()
            picker.addOnPositiveButtonClickListener {
                val newDate = Date(it as Long)
                calendar.time = newDate
                setCurrentDateOnView(calendar)
            }

            picker.show(childFragmentManager, DATE_PICKER_TAG)

        }
    }

    private fun setCurrentDateOnView(calendar: Calendar) {
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        binding.etDealDate.setText(
            StringBuilder()
                .append(day).append(DOT)
                .conditionally(month + 1 < 10) {
                    append(ZERO)
                }
                .append(month + 1).append(DOT)
                .append(year)
        )

    }

    companion object {
        const val DATE_PICKER_TAG = "DATE_PICKER"
        const val DOT = "."
        const val ZERO = "0"
        const val TIME_ZONE_UTC = "UTC"
    }


}