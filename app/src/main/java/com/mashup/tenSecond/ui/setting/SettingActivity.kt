package com.mashup.tenSecond.ui.setting

import com.mashup.tenSecond.R
import com.mashup.tenSecond.databinding.ActivitySettingBinding
import com.namget.diaryLee.ui.base.BaseActivity

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    override fun onLayoutId(): Int = R.layout.activity_setting

    companion object {
        var settingActivity: SettingActivity? = null

        fun getInstance(): SettingActivity {
            if (settingActivity == null) {
                synchronized(SettingActivity::class) {
                    settingActivity = SettingActivity()
                }
            }
            return settingActivity as SettingActivity
        }
    }

}