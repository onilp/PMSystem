package com.example.pmsystem.manager.subtask


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.adapter.SubTaskListAdapter
import com.example.pmsystem.adapter.TaskListAdapter
import com.example.pmsystem.developer.updatesubtask.UpdateSubTaskFragment
import com.example.pmsystem.manager.task.CreateTaskFragment
import com.example.pmsystem.manager.task.TaskListViewModel
import com.example.pmsystem.model.SubTaskListResponse
import com.example.pmsystem.model.TaskListResponse
import kotlinx.android.synthetic.main.fragment_sub_task_list.view.*
import kotlinx.android.synthetic.main.fragment_task_list.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SubTaskListFragment : Fragment()//, SubTaskListContract.View
 {




    /*lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: SubTaskListAdapter
    lateinit var subtaskListPresenter: SubTaskPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_sub_task_list, container, false)
        subtaskListPresenter = SubTaskPresenter(this)
        view.btn_create_sub_task.setOnClickListener {
            subtaskListPresenter.buttonClickedd()
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        subtaskListPresenter.viewIsCreated()

    }


    override fun navigateToCreateSubTask() {
        val fg: Fragment = CreateSubTaskFragment()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fg)
            ?.commit()
    }


    override fun showTaskListError(message: String?) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    override fun passDatasToAdapter(projectsubTask: List<SubTaskListResponse.ProjectSubTask>) {
        myAdapter = SubTaskListAdapter(context!!.applicationContext, projectsubTask)
        recyclerView.adapter = myAdapter
        myAdapter.notifyDataSetChanged()

    }

    companion object{
        fun newInstance(): SubTaskDeveloperListFragment {
            return SubTaskDeveloperListFragment()
        }
    }

*/
     lateinit var recyclerView: RecyclerView
     lateinit var myAdapter: SubTaskListAdapter
     private var subTasks : ArrayList<SubTaskListResponse.ProjectSubTask> = ArrayList()
     private lateinit var btn_createSubTask : Button
     private lateinit var btn_updateSubTaskStatus : Button

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         var view = inflater.inflate(R.layout.fragment_sub_task_list, container, false)
         btn_createSubTask = view.findViewById(R.id.btn_create_sub_task)
         btn_updateSubTaskStatus = view.findViewById(R.id.btn_update_sub_task_status)
         var subTaskListViewModel = ViewModelProviders.of(this).get(SubTaskListViewModel::class.java)
         var subTaskList : LiveData<List<SubTaskListResponse.ProjectSubTask>> = subTaskListViewModel.requestSubTaskList()

         (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.sub_task_list)
         subTaskList.observe(this, Observer {it->
             if (it != null) {
                 for(i in 0 until it.size) {
                     subTasks.add(it.get(i))
                 }
                 myAdapter = SubTaskListAdapter(context!!.applicationContext, subTasks)
                 recyclerView.adapter = myAdapter
                 myAdapter.notifyDataSetChanged()
             }
         })
         btn_createSubTask.setOnClickListener {
             val fg: Fragment = CreateSubTaskFragment()
             activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fg)!!.addToBackStack(null)?.commit()
         }

         btn_updateSubTaskStatus.setOnClickListener {
             val fg: Fragment = UpdateSubTaskFragment()
             activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fg)!!.addToBackStack(null)?.commit()
         }

         return view
     }
     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         recyclerView = view.findViewById(R.id.recyclerView)
         recyclerView.layoutManager = LinearLayoutManager(this.context)
     }
}
