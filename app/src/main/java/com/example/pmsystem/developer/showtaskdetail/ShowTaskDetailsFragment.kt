package com.example.pmsystem.developer.showtaskdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.developer.showtasklist.ShowTaskListContract
import com.example.pmsystem.developer.showtasklist.ShowTaskListFragment
import com.example.pmsystem.developer.subtaskdeveloper.subtasklist.SubTaskDeveloperListFragment
import com.example.pmsystem.model.ShowTaskDetailResponse
import com.example.pmsystem.model.SubTaskResponse

class ShowTaskDetailsFragment : Fragment(), ShowTaskDetailsContract.View {



    lateinit var textView: TextView
    lateinit var showTaskDetailsPresenter: ShowTaskDetailsPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Task Details"

        var bundle:Bundle? = arguments

        var taskid:String? =  bundle?.getString("taskid","")
        var projectid:String? = bundle?.getString("projectid","")


        showTaskDetailsPresenter = ShowTaskDetailsPresenter(this)
        showTaskDetailsPresenter.taskDetailsShowed(taskid.toString(),projectid.toString())
        var view  =  inflater.inflate(R.layout.fragment_show_task_details, container, false)

        textView = view.findViewById(R.id.taskdetails)

        return view
    }


    override fun showTaskDetailsFail(message: String?) {
        Toast.makeText(context, "Error :$message", Toast.LENGTH_LONG).show()
    }



    override fun showTaskDetailsSuccess(response: ShowTaskDetailResponse?) {
        Log.e("detail response",response.toString())

        textView.setText("taskid"+response!!.taskid.toString()+"\n"+
            "taskname: "+response!!.taskname.toString()+"\n"+
            "taskstatus: "+response!!.taskstatus.toString()+"\n"+
            "taskdesc: "+response!!.taskdesc.toString()+"\n"+
            "projectid: "+response!!.projectid.toString()+"\n"+
            "startdate: "+response!!.startdate.toString()+"\n"+
            "endstart: "+response!!.endstart.toString()+"\n"
        )

        textView.setOnClickListener {
            val subTaskDeveloperListFragment = SubTaskDeveloperListFragment()
            val bundle = Bundle()
            bundle.putString("taskid", response.taskid)
            subTaskDeveloperListFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, subTaskDeveloperListFragment).addToBackStack(null).commit()
        }


        Toast.makeText(context, "Successfully show task list", Toast.LENGTH_LONG).show()

    }

    companion object{
        fun newInstance(): ShowTaskDetailsFragment {
            return ShowTaskDetailsFragment()
        }
    }
}













