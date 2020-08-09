package com.example.ai_wrongnote.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_wrongnote.NoteViewModel
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.adapter.NoteAdapter
import data.NoteListItem
import kotlinx.android.synthetic.main.note_fragment.*
import org.jetbrains.anko.toast


class NoteFragment : Fragment() {

    companion object {
        fun newInstance() = NoteFragment()
    }
    //上下自动生成的
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

    //这是view层
    fun init() {
        //下拉刷新的进度条
        swipeRefresh.apply{
            setColorSchemeResources(R.color.GreenDark)
            isRefreshing = true
            //设置监听器，当用户手动下拉刷新的时候也要刷新数据
            setOnRefreshListener {
                loadNotes()
            }
        }

        //初始化recycleview 绑定的第一步
        note_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            //绑定第三步。做好adapter之后再回来绑定就可以
            adapter=NoteAdapter(context,noteListItems)
        }

        //present层加载数据
        loadNotes()

    }
    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    //没有抽取对应的contract，所以这些函数不是继承来的，新建的。（如果抽取了就应该是继承接口而写的）
    //两个函数对应的功能：提示加载列表失败了还是成功了。这是view层的实现
    fun onLoadNotesFailed(){
        //隐藏进度条并提示用户加载失败
        swipeRefresh.isRefreshing = false
        context?.toast("加载错题列表失败")
    }

    fun onLoadNotesSuccess(){
        //加载成功之后要刷新一下数据
        swipeRefresh.isRefreshing = false
        note_recyclerview.adapter?.notifyDataSetChanged()
        context?.toast("加载成功")
    }

    val noteListItems= mutableListOf<NoteListItem>()

    //presenter层的实现
    fun loadNotes(){
        //提醒，有可能要放到主线程做。到时候根据实际情况排查错误。现在没有放到主线程。
        // 应该是根据获取云数据库里的数据的实际方法（异步还是同步）来确定在主线程还是子线程
        //这里应该是需要显示在item上的，需要从数据库里读取的数据，在这里用变量承接
        //统一命名格式为item里对应的变量名加s
        //try {
            //三个变量等号的右边 应该是获取到的数据列表。 数据类型是list……   ！！！！！！！我觉得应该写一个类专门用来做读取数据库里各种数据的工作，然后这里调用那个类里对应的方法来获取数据
            //先用自定义的数组里的数据是实现，后期再连数据库
        //再次加载数据之前要清空数据集合
        noteListItems.clear()
            val note_point_texts = context?.resources?.getStringArray(R.array.know_point)
            //val note_photo_items =
            // 暂时没写图片的列表
            val note_time_texts = context?.resources?.getStringArray(R.array.note_time)

        //读取完数据之后需要做转换。上面定义的变量只是代表加载出来的数据。还需把加载出来的数据转换成UI界面需要看到的数据
        note_point_texts?.forEach {
            val noteListItem = NoteListItem(it, note_time_texts.toString())
            //把创建好的加入到可变的数据集合当中
            noteListItems.add(noteListItem)
            //然后去通知view层。让adapter去维护数据集合
        }


        //成功的话通知view层
        onLoadNotesSuccess()
        //}catch (Exception e){
        //不懂这个异常怎么写。不写了。不try了
        //}

    }

}
