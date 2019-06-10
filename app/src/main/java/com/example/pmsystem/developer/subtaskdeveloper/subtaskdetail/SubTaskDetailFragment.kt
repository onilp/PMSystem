package com.example.pmsystem.developer.subtaskdeveloper.subtaskdetail


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.MyApplication
import com.example.pmsystem.R
import com.example.pmsystem.model.subtaskdetaildeveloper.SubTaskDetailDeveloperResponse
import com.example.pmsystem.model.subtaskdeveloperlist.SubTaskDeveloperListResponse
import kotlinx.android.synthetic.main.fragment_sub_task_list_detail.*
import kotlinx.android.synthetic.main.fragment_sub_task_list_detail.view.*

class SubTaskDetailFragment : Fragment() {

    private lateinit var subTaskDetailViewModel: SubTaskDetailViewModel
    lateinit var subTaskDetailLiveData: MutableLiveData<SubTaskDetailDeveloperResponse>

    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sub_task_list_detail, container, false)

        init()

        subTaskDetailLiveData = subTaskDetailViewModel.fetchSubTaskDetail(arguments!!.getString("taskid", ""), arguments!!.getString("subtask_id", ""), arguments!!.getString("project_id", ""))

        subTaskDetailLiveData.observe(this, Observer {
            if(it == null){
                rootView.no_sub_task_detail_tv.visibility = View.VISIBLE
                rootView.sub_task_detail_ll.visibility = View.GONE
            }else{
                rootView.no_sub_task_detail_tv.visibility = View.GONE
                rootView.sub_task_detail_ll.visibility = View.VISIBLE

                Log.d("Sub Task Detail", it.subtaskid)
                rootView.sub_task_id_detail_tv.text = it.subtaskid
                rootView.task_id_detail_tv.text = it.taskid
                rootView.project_id_detail_tv.text = it.projectid
                rootView.sub_task_name_detail_tv.text = it.subtaskname
                rootView.sub_task_status_detail_tv.text = it.subtaskstatus
                rootView.sub_task_desc_detail_tv.text = it.subtaskdesc
                rootView.start_date_detail_tv.text = it.startdate
                rootView.end_start_detail_tv.text = it.endstart
            }
        })
        return rootView
    }

    private fun init() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Sub Task Details"

        MyApplication.component.inject(this)

        subTaskDetailViewModel = ViewModelProviders.of(this).get(SubTaskDetailViewModel::class.java)
    }


}
