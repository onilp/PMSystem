package com.example.pmsystem.developer.showtaskdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.developer.showtasklist.ShowTaskListContract
import com.example.pmsystem.developer.showtasklist.ShowTaskListFragment
import com.example.pmsystem.model.SubTaskResponse


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ShowTaskDetailsFragment : Fragment(), ShowTaskDetailsContract.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view  =  inflater.inflate(R.layout.fragment_show_task_details, container, false)


        return view

    }





    override fun showTaskDetailsFail(message: String?) {
        Toast.makeText(context, "Error :$message", Toast.LENGTH_LONG).show()
    }

    override fun showTaskDetailsSuccess(response: SubTaskResponse?) {
        Toast.makeText(context, "Successfully show task list", Toast.LENGTH_LONG).show()
    }

    override fun listAllSubTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


    }


    companion object{
        fun newInstance(): ShowTaskDetailsFragment {
            return ShowTaskDetailsFragment()
        }
    }



}













