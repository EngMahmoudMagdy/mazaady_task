package com.magdy.mazaadytask.firstPage

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

// OptionAdapter.kt
class OptionAdapter(context: Context, resource: Int, private val options: List<String>) :
    ArrayAdapter<String>(context, resource, options) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)

        return view
    }
}
