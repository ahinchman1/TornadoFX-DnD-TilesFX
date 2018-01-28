package com.example.demo.controllers

import com.example.demo.views.*
import tornadofx.*

class WorkbenchController : Controller() {

    /***** Global Variables *****/
    private val workbench: Workbench by inject()
    var tile = 0

    /**
     * Decide which grid to use and switch views from workbench to demo gui
     *
     * @property String image
     */
    fun goToEditor(image: String) {
        tile = parseImage(image)
        workbench.replaceWith(find<TileGUI>(), centerOnScreen = true, sizeToScene = true)
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