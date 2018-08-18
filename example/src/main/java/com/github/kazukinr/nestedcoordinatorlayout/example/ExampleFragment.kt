package com.github.kazukinr.nestedcoordinatorlayout.example


import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.kazukinr.coordinator.NestedCoordinatorLayout
import com.github.kazukinr.nestedcoordinatorlayout.example.databinding.FragmentExampleBinding

private const val ARG_PRE_SCROLL_UP = "pre_scroll_up"
private const val ARG_PRE_SCROLL_DOWN = "pre_scroll_down"

/**
 * A simple [Fragment] subclass.
 * Use the [ExampleFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ExampleFragment : Fragment() {

    private var binding: FragmentExampleBinding? = null

    private var preScrollUp: NestedCoordinatorLayout.PreScrollStrategy? = null
    private var preScrollDown: NestedCoordinatorLayout.PreScrollStrategy? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            preScrollUp = it.getSerializable(ARG_PRE_SCROLL_UP) as NestedCoordinatorLayout.PreScrollStrategy
            preScrollDown = it.getSerializable(ARG_PRE_SCROLL_DOWN) as NestedCoordinatorLayout.PreScrollStrategy
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding?.let {
            return it.root
        }

        return DataBindingUtil.inflate<FragmentExampleBinding>(
                inflater,
                R.layout.fragment_example,
                container,
                false
        ).also {
            // You can also set preScrollUp/preScrollDown in layout xml.
            it.nestedCoordinator.preScrollUp = preScrollUp ?: NestedCoordinatorLayout.PreScrollStrategy.BOTH
            it.nestedCoordinator.preScrollDown = preScrollDown ?: NestedCoordinatorLayout.PreScrollStrategy.BOTH
            binding = it
        }.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param preScrollUp
         * @param preScrollDown
         * @return A new instance of fragment ExampleFragment.
         */
        fun newInstance(preScrollUp: NestedCoordinatorLayout.PreScrollStrategy,
                        preScrollDown: NestedCoordinatorLayout.PreScrollStrategy) =
                ExampleFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PRE_SCROLL_UP, preScrollUp)
                        putSerializable(ARG_PRE_SCROLL_DOWN, preScrollDown)
                    }
                }
    }
}
