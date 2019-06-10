package com.example.pmsystem.manager.createproject


import android.app.ProgressDialog
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
import android.widget.Button
import com.example.pmsystem.R
import com.example.pmsystem.manager.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_create_project.*
import org.jetbrains.anko.toast

class CreateProjectFragment : Fragment() {

    private lateinit var createProjectBtn: Button
    private lateinit var createProjectViewModel: CreateProjectViewModel
    lateinit var progressDialog: ProgressDialog
    private var shouldNav: Boolean = false
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_create_project, container, false)

        init()

        createProjectBtn.setOnClickListener {
            if (create_project_name_tiet.text.toString() != ""
                && create_project_status_tiet.text.toString() != ""
                && create_project_desc_tiet.text.toString() != ""
                && create_project_start_date_tiet.text.toString() != ""
                && crete_project_end_date_tiet.text.toString() != ""
            ) {
                progressDialog.show()

                shouldNav = createProjectViewModel.createProject(
                    create_project_name_tiet.text!!.toString(),
                    create_project_status_tiet.text!!.toString(),
                    create_project_desc_tiet.text!!.toString(),
                    create_project_start_date_tiet.text!!.toString(),
                    crete_project_end_date_tiet.text!!.toString()
                )

                if (shouldNav) {
                    progressDialog.dismiss()
                    Log.d("create", "yessss")
                    navigateToHome()
                    activity?.toast(createProjectViewModel.displayCreateProjectMsg())
                } else {
                    progressDialog.dismiss()
                    Log.e("create", "failed")
                    activity?.toast(createProjectViewModel.displayCreateProjectMsg())
                }
            }

        }

        return rootView
    }

    private fun init() {
        (activity as? AppCompatActivity)?.supportActionBar?.title =
            getString(R.string.create_project)
        createProjectBtn = rootView.findViewById(R.id.create_project_button)

        progressDialog = ProgressDialog(context)
        progressDialog.setMessage(getString(R.string.creating_project))

        createProjectViewModel = ViewModelProviders.of(this).get(CreateProjectViewModel::class.java)
    }


    private fun navigateToHome() {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment.newInstance()).commit()
    }

    // Call this function to create a new fragment
    companion object {
        fun newInstance(): CreateProjectFragment {
            return CreateProjectFragment()
        }
    }

}
