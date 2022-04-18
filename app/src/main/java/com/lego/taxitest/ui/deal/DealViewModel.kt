package com.lego.taxitest.ui.deal

import androidx.lifecycle.*
import com.lego.taxitest.domain.models.DealType
import com.lego.taxitest.domain.models.WalletTransaction
import com.lego.taxitest.domain.usecase.AddWalletTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DealViewModel(
    private val addWalletTransaction: AddWalletTransaction
) : ViewModel() {

    private val _nameValidator = MutableLiveData(false)
    private val _valueValidator = MutableLiveData(false)
    private val _type = MutableLiveData(0)
    private val _btnEnabled: MediatorLiveData<Boolean> = MediatorLiveData()
    val btnEnabled: LiveData<Boolean>
        get() = _btnEnabled

    private val _completed: MediatorLiveData<Boolean> = MediatorLiveData()
    val completed: LiveData<Boolean>
        get() = _completed


    init {
        _btnEnabled.addSource(_nameValidator) {
            _btnEnabled.value = _valueValidator.value!! && it
        }

        _btnEnabled.addSource(_valueValidator) {
            _btnEnabled.value = _nameValidator.value!! && it
        }

    }

    fun addTransaction(
        name: String,
        value: String,
        date: String,
    ) {
        viewModelScope.launch(SupervisorJob() + Dispatchers.IO) {
            addWalletTransaction.add(
                WalletTransaction(
                    name = name,
                    value = value,
                    date = date,
                    type = DealType.values()[_type.value!!]
                )
            )
            withContext(Dispatchers.Main) {
                _completed.value = true
            }
        }
    }

    fun setNameValidation(passed: Boolean) {
        _nameValidator.value = passed
    }

    fun setValueValidation(passed: Boolean) {
        _valueValidator.value = passed
    }

    fun setType(type: Int) {
        _type.value = type
    }
}