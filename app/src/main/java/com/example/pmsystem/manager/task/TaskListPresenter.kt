package com.example.pmsystem.manager.task

import android.util.Log
import com.example.pmsystem.model.TaskListResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable


class TaskListPresenter (var view : TaskListContract.View) : TaskListContract.Presenter,Serializable {
    override fun buttonClicked() {
        view.navigateToCreateTask()
    }

    override fun viewIsCreated() {
        val apiInterface = ApiInterface.getRetrofitInstance().getTaskList()
        apiInterface.enqueue(object : Callback<TaskListResponse> {
            override fun onFailure(call: Call<TaskListResponse>, t: Throwable) {
                Log.e("TaskListError---:", t.message)
                view.showTaskListError(t.message)
            }
            override fun onResponse(call: Call<TaskListResponse>, response: Response<TaskListResponse>) {
                Log.e("TaskListResponse--", response.body()!!.projectTask.toString())
                /*var assignFragment : Fragment = AssignFragment()
                var bundle  = Bundle()
                for(i in 0..response.body()!!.projectTask.size) {
                    bundle.putStringArrayList("taskList", arrayListOf(response.body()!!.projectTask.get(i).toString()))
                    Log.e("TaskPresenter---",response.body()!!.projectTask.get(i).toString())
                }

                var taskListAssign = TaskListAssignResponse().projectTask
                bundle.putSerializable('taskList',taskListAssign)*/


                view.passDataToAdapter(response.body()!!.projectTask)
            }
        })
        /*apiInterface.enqueue(object : Callback<TaskListAssignResponse>{
            override fun onFailure(call: Call<TaskListAssignResponse>, t: Throwable) {
                Log.e("TaskListError---:", t.message)
                view.showTaskListError(t.message)
            }

            override fun onResponse(
                call: Call<TaskListAssignResponse>,
                response: Response<TaskListAssignResponse>
            ) {
                Log.e("TaskListResponse--", response.body()!!.projectTask.toString())
                var assignFragment : Fragment = AssignFragment()
                var bundle  = Bundle()
                var taskListAssign = TaskListAssignResponse()
                taskListAssign = response.body()!!.projectTask
                Log.e("Presenter ---", taskListAssign.toString())
                bundle.putSerializable("taskList",taskListAssign)
                assignFragment.arguments = bundle
                Log.e("TaskListPresenter----", taskListAssign.projectTask[0]?.taskid)
            }
        })*/

    }

}