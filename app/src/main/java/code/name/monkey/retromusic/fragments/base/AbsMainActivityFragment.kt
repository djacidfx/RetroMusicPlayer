package code.name.monkey.retromusic.fragments.base

import android.os.Build
import android.os.Bundle
import android.view.View
import code.name.monkey.appthemehelper.util.ATHUtil
import code.name.monkey.appthemehelper.util.ColorUtil
import code.name.monkey.appthemehelper.util.VersionUtils
import code.name.monkey.retromusic.R
import code.name.monkey.retromusic.activities.MainActivity
import code.name.monkey.retromusic.dialogs.OptionsSheetDialogFragment

abstract class AbsMainActivityFragment : AbsMusicServiceFragment() {

    val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        mainActivity.setNavigationbarColorAuto()
        mainActivity.setLightNavigationBar(true)
        mainActivity.setTaskDescriptionColorAuto()
        mainActivity.hideStatusBar()
        mainActivity.setBottomBarVisibility(View.VISIBLE)
    }

    private fun setStatusBarColor(view: View, color: Int) {
        val statusBar = view.findViewById<View>(R.id.status_bar)
        if (statusBar != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //statusBar.setBackgroundColor(color)
                mainActivity.setLightStatusbarAuto(color)
            } else {
                statusBar.setBackgroundColor(color)
            }
        }
    }

    fun setStatusBarColorAuto(view: View) {
        val colorPrimary = ATHUtil.resolveColor(requireContext(), android.R.attr.windowBackground)
        // we don't want to use statusbar color because we are doing the color darkening on our own to support KitKat
        if (VersionUtils.hasMarshmallow()) {
            setStatusBarColor(view, colorPrimary)
        } else {
            setStatusBarColor(view, ColorUtil.darkenColor(colorPrimary))
        }
    }

    protected fun showMainMenu(option: Int) {
        OptionsSheetDialogFragment.newInstance(option).show(childFragmentManager, "Main_Menu")
    }
}
