package com.example.pmsystem.developer.showtasklist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.adapter.ShowTaskListAdapter
import com.example.pmsystem.manager.subtask.CreateSubTaskFragment
import com.example.pmsystem.model.ShowTaskListResponse
import com.example.pmsystem.model.SubTaskResponse


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ShowTaskListFragment : Fragment(), ShowTaskListContract.View {

    lateinit var recyclerView: RecyclerView
    lateinit var showTaskListAdapter: ShowTaskListAdapter
    lateinit var showtaskListPresenter: ShowtaskListPresenter


    override fun showTaskListError(message: String?) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    override fun passDataToAdapter(showtaskList: List<ShowTaskListResponse.ViewTask>) {

        showTaskListAdapter = ShowTaskListAdapter(context!!.applicationContext,showtaskList)
        recyclerView.adapter = showTaskListAdapter
        showTaskListAdapter.notifyDataSetChanged()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_show_task_list, container, false)
        showtaskListPresenter = ShowtaskListPresenter(this)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        showtaskListPresenter.viewIsCreated()
    }



    companion object{
        fun newInstance(): ShowTaskListFragment {
            return ShowTaskListFragment()
        }
    }




}
