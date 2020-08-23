package com.example.ai_wrongnote

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {
    private var mActivity: Activity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = activity
    }

    override fun getContext(): Context? {
        return if (mActivity == null) {
            MyApplication.getContext()
        } else mActivity
    }
}