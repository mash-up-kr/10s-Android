package com.mashup.tenSecond.ui.setting

import com.mashup.tenSecond.R
import com.mashup.tenSecond.databinding.FragmentSettingBinding
import com.namget.diaryLee.ui.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    override fun onLayoutId(): Int = R.layout.fragment_setting

    companion object {
        var settingFragment: SettingFragment? = null

        fun getInstance(): SettingFragment {
            if (settingFragment == null) {
                synchronized(SettingFragment::class) {
                    settingFragment = SettingFragment()
                }
            }
            return settingFragment as SettingFragment
        }
    }

}