package com.example.demo.controllers

import com.example.demo.views.*
import tornadofx.*

class WorkbenchController : Controller() {

    /***** Global Variables *****/
    private val workbench: Workbench by inject()
    var metroTile = 0

    /**
     * Switch views from workbench to demo gui
     *
     * @param [String] image
     */
    fun goToEditor(image: String) {
        // decide which metroTileHomepage grid to use
        metroTile = parseImage(image)
        workbench.replaceWith(find<MetroTileHomepage>(), centerOnScreen = true, sizeToScene = true)
    }

    /**
     * Determine which grid had been picked
     *
     * @param [String] image
     */
    private fun parseImage(image: String): Int {
        val separate = image.split("/",".")
        val size = separate[2].length - 1
        return separate[2].substring(size).toInt()
    }

    /**
     * Determine which grid had been picked
     *
     * @param [UIComponent] className
     */
    fun returnToWorkbench(className: UIComponent) {
        (className).replaceWith(workbench, centerOnScreen = true, sizeToScene = true)
    }

}