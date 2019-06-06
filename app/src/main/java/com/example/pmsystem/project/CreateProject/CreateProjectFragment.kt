package com.example.pmsystem.project.CreateProject


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.pmsystem.R
import com.example.pmsystem.project.Home.HomeFragment
import kotlinx.android.synthetic.main.fragment_create_project.*
import org.jetbrains.anko.toast

class CreateProjectFragment : Fragment(), CreateProjectContract.View {

    lateinit var createProjectPresenter: CreateProjectPresenter
    private lateinit var create_project_button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_create_project, container, false)

        init(rootView)

        create_project_button.setOnClickListener {
            createProjectPresenter.createProject(
                create_project_name_tiet.text!!.toString(),
                create_project_status_tiet.text!!.toString(),
                create_project_desc_tiet.text!!.toString(),
                create_project_start_date_tiet.text!!.toString(),
                crete_project_end_date_tiet.text!!.toString())

            activity!!.toast("Creating project")
        }

        return rootView
    }

    private fun init(rootView: View) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.create_project)
        createProjectPresenter = CreateProjectPresenter.newInstance(this)
        create_project_button = rootView.findViewById(R.id.create_project_button)
    }


    // Call this function to create a new fragment
    companion object{
        fun newInstance(): CreateProjectFragment {
            return CreateProjectFragment()
        }
    }

    override fun toastCreateProject(createProjectResult: String) {
        activity!!.toast(createProjectResult)
    }

    override fun navigateToHome() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment.newInstance()).commit();
    }
}
