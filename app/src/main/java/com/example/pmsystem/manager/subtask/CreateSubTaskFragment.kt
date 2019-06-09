package com.example.pmsystem.manager.subtask


import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.pmsystem.R
import com.example.pmsystem.manager.task.CreateTaskPresenter
import com.example.pmsystem.manager.task.TaskListFragment
import com.example.pmsystem.model.SubTaskResponse
import com.example.pmsystem.project.createproject.CreateProjectFragment
import kotlinx.android.synthetic.main.fragment_create_project.*
import kotlinx.android.synthetic.main.fragment_create_sub_task.*
import kotlinx.android.synthetic.main.fragment_create_sub_task.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CreateSubTaskFragment : Fragment(), CreateSubTaskContract.View {
    lateinit var createSubTaskPresenter : CreateSubTaskPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = LayoutInflater.from(context).inflate(R.layout.fragment_create_sub_task, container, false)
        createSubTaskPresenter = CreateSubTaskPresenter(this)
        view.create_sub_task_button.setOnClickListener {
            var project_id: String = create_project_id_tiet.text.toString()
            var task_id: String = create_project_task_id_tiet.text.toString()
            var sub_task_name: String = create_project_sub_task_name_tiet.text.toString()
            var sub_task_status: String = create_project_sub_task_status_tiet.text.toString()
            var sub_task_desc: String = create_project_sub_task_desc_tiet.text.toString()
            var start_date: String = create_project_start_date_tiet.text.toString()
            var end_date: String = create_project_sub_task_end_date_tiet.toString()

          createSubTaskPresenter.buttonClicked(project_id,task_id,sub_task_name,sub_task_status,sub_task_desc,start_date,end_date)


        }

        return view

    }



    override fun showCreateSubTaskFail(message: String?) {
        Toast.makeText(context, "Error :$message", Toast.LENGTH_LONG).show()
    }

    override fun showCreateSubTaskSuccess(response: SubTaskResponse?) {
        Toast.makeText(context, "Successfully Created a new Sub Task", Toast.LENGTH_LONG).show()
    }



    override fun listAllSubTask() {

        val fg: Fragment = SubTaskListFragment()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fg)
            ?.addToBackStack(null)?.commit()


    }

    companion object{
        fun newInstance(): CreateSubTaskFragment {
            return CreateSubTaskFragment()
        }
    }

}
