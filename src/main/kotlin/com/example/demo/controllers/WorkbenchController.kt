package com.example.demo.controllers

import com.example.demo.views.*
import tornadofx.*

class WorkbenchController : Controller() {

    private val workbench: Workbench by inject()
    var metroTile = 0

    fun goToEditor(image: String) {
        // decide which metroTileHomepage grid to use
        metroTile = parseImage(image)
        workbench.replaceWith(find<MetroTileHomepage>(), null, sizeToScene = true)
    }

    private fun parseImage(image: String): Int {
        val separate = image.split("/",".")
        return separate[2].substring(17).toInt()
    }

    fun returnToWorkbench(className: UIComponent) {
        (className).replaceWith(workbench, null, sizeToScene = true)
    }

}