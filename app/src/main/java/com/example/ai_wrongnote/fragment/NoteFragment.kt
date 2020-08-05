package com.example.ai_wrongnote.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ai_wrongnote.NoteViewModel
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.adapter.NoteAdapter
import kotlinx.android.synthetic.main.note_fragment.*


class NoteFragment : Fragment() {

    companion object {
        fun newInstance() = NoteFragment()
    }

    private lateinit var viewModel: NoteViewModel

    /*没写BaseFragment的后果就是，onCreateView函数和onViewCreated函数每次都要写。另外onActivityCreated
    目前应该用不到,可以删掉*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    fun init() {
        //初始化recycleview 绑定的第一步
        note_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            //绑定第三步。做好adapter之后再回来绑定就可以
            adapter=NoteAdapter(context)
        }
    }
    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

}
