package com.lego.taxitest.ui

import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout


fun <T> T.conditionally(condition: Boolean, func: T.() -> T): T {
    return if (condition) func() else this
}

fun AutoCompleteTextView.toSpinnerMode() {
    isFocusable = false
    isFocusableInTouchMode = false
    inputType = 0
    this.setOnClickListener {
        this.showDropDown()
    }
}

fun RecyclerView.addItemDecorationWithoutLastDivider() {

    if (layoutManager !is LinearLayoutManager)
        return

    addItemDecoration(object :
        DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation) {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            if (parent.getChildAdapterPosition(view) == state.itemCount - 1)
                outRect.setEmpty()
            else
                super.getItemOffsets(outRect, view, parent, state)
        }
    })
}

fun EditText.addDefaultTextChangedListener(t: TextInputLayout, func: (b: Boolean) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            func(s.isNotEmpty())
            if (s.isNotEmpty()) {
                if (!TextUtils.isEmpty(t.error)) {
                    t.error = null
                    t.isErrorEnabled = false
                }
            }
        }

        override fun afterTextChanged(s: Editable) {}
    })
}
