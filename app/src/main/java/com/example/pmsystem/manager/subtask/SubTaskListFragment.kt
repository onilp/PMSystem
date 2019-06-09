package com.example.pmsystem.manager.subtask


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.adapter.SubTaskListAdapter
import com.example.pmsystem.model.SubTaskListResponse
import kotlinx.android.synthetic.main.fragment_sub_task_list.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SubTaskListFragment : Fragment(), SubTaskListContract.View {




    lateinit var recyclerView: RecyclerView
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
}
