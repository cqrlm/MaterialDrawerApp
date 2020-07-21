package com.example.materialdrawerapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.example.materialdrawerapp.controller.*
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.SectionDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawer: Drawer
    private lateinit var router: Router
    private val homeItem = PrimaryDrawerItem().withName(R.string.home_title)
    private val galleryItem = PrimaryDrawerItem().withName(R.string.gallery_title)
    private val slideshowItem = PrimaryDrawerItem().withName(R.string.slideshow_title)
    private val toolsItem = PrimaryDrawerItem().withName(R.string.tools_title)
    private val shareItem = SecondaryDrawerItem().withName(R.string.share_title)
    private val sendItem = SecondaryDrawerItem().withName(R.string.send_title)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        router = Conductor.attachRouter(this, container, savedInstanceState)
        drawer = initDrawer()

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(HomeController()))
        }
        selectItem()
    }

    private fun initDrawer() = DrawerBuilder()
        .withActivity(this)
        .withToolbar(toolbar)
        .withAccountHeader(initAccountHeader())
        .withActionBarDrawerToggle(true)
        .withActionBarDrawerToggleAnimated(true)
        .addDrawerItems(
            homeItem.withIcon(GoogleMaterial.Icon.gmd_home),
            galleryItem.withIcon(GoogleMaterial.Icon.gmd_photo_library),
            slideshowItem.withIcon(GoogleMaterial.Icon.gmd_slideshow),
            toolsItem.withIcon(GoogleMaterial.Icon.gmd_build),
            SectionDrawerItem().withName("Communicate"),
            shareItem.withIcon(GoogleMaterial.Icon.gmd_share),
            sendItem.withIcon(GoogleMaterial.Icon.gmd_send)
        )
        .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
            override fun onItemClick(
                view: View?,
                position: Int,
                drawerItem: IDrawerItem<*>
            ): Boolean {
                selectItem(drawerItem)
                return true
            }
        })
        .build()

    private fun initAccountHeader() = AccountHeaderBuilder()
        .withActivity(this)
        .withHeaderBackground(R.drawable.header)
        .build()

    private fun selectItem(drawerItem: IDrawerItem<*>) {
        val controller: Controller = when (drawerItem) {
            homeItem -> HomeController()
            galleryItem -> GalleryController()
            slideshowItem -> SlideshowController()
            toolsItem -> ToolsController()
            shareItem -> ShareController()
            sendItem -> SendController()
            else -> HomeController()
        }
        router.pushController(
            RouterTransaction.with(controller)
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler())
        )
        drawer.closeDrawer()
    }

    private fun selectItem() {
        when (router.backstack[router.backstackSize - 1].controller()) {
            is HomeController -> drawer.setSelection(homeItem)
            is GalleryController -> drawer.setSelection(galleryItem)
            is SlideshowController -> drawer.setSelection(slideshowItem)
            is ToolsController -> drawer.setSelection(toolsItem)
            is ShareController -> drawer.setSelection(shareItem)
            is SendController -> drawer.setSelection(sendItem)
        }
    }

    override fun onBackPressed() {
        if (router.backstack[router.backstackSize - 1].controller() !is HomeController) {
            router.popToRoot()
            drawer.setSelection(homeItem)
        } else {
            super.onBackPressed()
        }
    }

}
