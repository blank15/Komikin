package id.blank.home.ui.manhwa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.blank.home.R


class ManhwaFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manhwa, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) {}
    }
}