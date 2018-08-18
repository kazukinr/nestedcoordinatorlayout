package com.github.kazukinr.nestedcoordinatorlayout.example


import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.kazukinr.coordinator.NestedCoordinatorLayout
import com.github.kazukinr.nestedcoordinatorlayout.example.databinding.FragmentMenuBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MenuFragment : Fragment() {

    private var binding: FragmentMenuBinding? = null

    private val listener: Listener = object : Listener {

        override fun onBothClicked() {
            openNestedCoordinator(NestedCoordinatorLayout.PreScrollStrategy.BOTH)
        }

        override fun onParentFirstClicked() {
            openNestedCoordinator(NestedCoordinatorLayout.PreScrollStrategy.PARENT_FIRST)
        }

        override fun onChildFirstClicked() {
            openNestedCoordinator(NestedCoordinatorLayout.PreScrollStrategy.CHILD_FIRST)
        }

        private fun openNestedCoordinator(preScroll: NestedCoordinatorLayout.PreScrollStrategy) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.content, ExampleFragment.newInstance(preScroll, preScroll))
                    ?.addToBackStack(ExampleFragment::class.java.simpleName)
                    ?.commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding?.let {
            return it.root
        }

        return DataBindingUtil.inflate<FragmentMenuBinding>(
                inflater,
                R.layout.fragment_menu,
                container,
                false
        ).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.listener = listener
    }

    interface Listener {

        fun onBothClicked()

        fun onParentFirstClicked()

        fun onChildFirstClicked()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MenuFragment.
         */
        fun newInstance() = MenuFragment()
    }
}
