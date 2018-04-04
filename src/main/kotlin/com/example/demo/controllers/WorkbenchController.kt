package com.example.demo.controllers

import com.example.demo.model.GridInfoModel
import com.example.demo.model.HomepageGridBuilder
import com.example.demo.views.*
import tornadofx.*

class WorkbenchController : Controller() {

    /***** Global Variables *****/
    private val workbench: Workbench by inject()
    private val gridInfo: GridInfoModel by inject()

    private val grids = resources.jsonArray("/JSON/GridInfo.json").toModel<HomepageGridBuilder>()

    /**
     * Decide which grid to use and switch views from workbench to demo gui
     *
     * @property String image
     */
    fun goToEditor(image: String) {
        gridInfo.useTileGrid(grids, parseImage(image))
        val scope = Scope()
        scope.set(gridInfo)
        DefaultScope.set(gridInfo)
        workbench.replaceWith(find<TileGUI>(scope), centerOnScreen = true, sizeToScene = true)
    }

    /**
     * Determine which grid had been picked
     *
     * @property String image
     */
    private fun parseImage(image: String): Int {
        val separate = image.split("/",".")
        val size = separate[2].length - 1
        return separate[2].substring(size).toInt()
    }

    /**
     * Determine which grid had been picked
     *
     * @property UIComponent className
     */
    fun returnToWorkbench(className: UIComponent) {
        (className).replaceWith(workbench, centerOnScreen = true, sizeToScene = true)
    }

}