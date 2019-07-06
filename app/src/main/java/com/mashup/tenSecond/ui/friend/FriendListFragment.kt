package com.mashup.tenSecond.ui.friend

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide.init
import com.mashup.tenSecond.data.model.Friend
import com.mashup.tenSecond.databinding.FragmentFriendlistBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.friend.adapter.FriendListAdapter
import com.mashup.tenSecond.ui.setting.SettingActivity
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.toastMakeToast
import com.namget.diaryLee.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_friendlist.*
import org.koin.android.ext.android.inject


class FriendListFragment : BaseFragment<FragmentFriendlistBinding>() {

    val TAG = "FriendListFragment"
    override fun onLayoutId(): Int = com.mashup.tenSecond.R.layout.fragment_friendlist
    val friendListViewModelFactory: FriendListViewModelFactory by inject()
    lateinit var friendListViewModel: FriendListViewModel

    val diffCallback = object : DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean =
            oldItem.email == newItem.email

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean =
            oldItem.email == newItem.email
    }

    private val friendListAdapter: FriendListAdapter by lazy {
        FriendListAdapter(diffCallback)
    }


    companion object {
        lateinit var friendListFragment: FriendListFragment

        fun newInstance(): FriendListFragment {
            synchronized(FriendListFragment::class) {
                friendListFragment = FriendListFragment()
                val args = Bundle()
                friendListFragment.arguments = args
                return friendListFragment
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setRecyclerView()
        initViewModel()
        return view
    }

    private fun setRecyclerView() {
        binding.friendListRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.itemAnimator = DefaultItemAnimator()
            this.addItemDecoration(SimpleDividerItemDecoration(context))
            this.adapter = friendListAdapter
        }
    }

    private fun initViewModel() {
        friendListViewModel =
            ViewModelProviders.of(this, friendListViewModelFactory).get(FriendListViewModel::class.java)
        friendListViewModel.friendList.observe(this, Observer {
            friendListAdapter.submitList(it.friendList)
        })
        binding.fragment = this
        friendListViewModel.getFriendList()
    }

    fun startSettingActivity(view: View){
        val intent = Intent(context, SettingActivity::class.java)
        startActivity(intent)
    }

    fun addFriend(view: View) {
        LogUtil.e(TAG, "친구추가")
        val inputText = EditText(context)
        val builder = AlertDialog.Builder(context)
            .setTitle(resources.getString(com.mashup.tenSecond.R.string.friendAdd))
            .setView(inputText)
            .setNegativeButton(
                resources.getString(com.mashup.tenSecond.R.string.cancel)
            ) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(
                resources.getString(com.mashup.tenSecond.R.string.add)
            ) { dialog, which ->
                val input = inputText.text.toString()
                if (friendListViewModel.isValidEmail(input)) {
                    friendListViewModel.addFriendList(input)
                    dialog.dismiss()
                } else {
                    context?.toastMakeToast("유효한 이메일이 아닙니다.")
                }
            }
        val dialog = builder.create()
        dialog.show()

        val positiveButton: Button = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        inputText.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    positiveButton.isEnabled = friendListViewModel.isValidEmail(s ?: "")
                }
            }
        )
    }


}