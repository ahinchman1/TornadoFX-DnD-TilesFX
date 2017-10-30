package com.example.demo.controllers

import com.example.demo.views.MetroTileHomepages.*
import com.example.demo.views.Workbench
import tornadofx.*

class WorkbenchController : Controller() {
    private val workbench: Workbench by inject()
    private val metroTileHomepage1: MetroTileHomepage1 by inject()
    private val metroTileHomepage2: MetroTileHomepage2 by inject()
    private val metroTileHomepage3: MetroTileHomepage3 by inject()
    private val metroTileHomepage4: MetroTileHomepage4 by inject()
    private val metroTileHomepage5: MetroTileHomepage5 by inject()
    private val metroTileHomepage6: MetroTileHomepage6 by inject()
    private val metroTileHomepage7: MetroTileHomepage7 by inject()
    private val metroTileHomepage8: MetroTileHomepage8 by inject()
    private val metroTileHomepage9: MetroTileHomepage9 by inject()
    private val metroTileHomepage10: MetroTileHomepage10 by inject()
    private val metroTileHomepage11: MetroTileHomepage11 by inject()
    private val metroTileHomepage12: MetroTileHomepage12 by inject()
    private val metroTileHomepage13: MetroTileHomepage13 by inject()
    private val metroTileHomepage14: MetroTileHomepage14 by inject()
    private val metroTileHomepage15: MetroTileHomepage15 by inject()
    private val metroTileHomepage16: MetroTileHomepage16 by inject()
    private val metroTileHomepage17: MetroTileHomepage17 by inject()
    private val metroTileHomepage18: MetroTileHomepage18 by inject()
    private val metroTileHomepage19: MetroTileHomepage19 by inject()
    private val metroTileHomepage20: MetroTileHomepage20 by inject()


    fun goToGUIEditor(image: String) {
        // parse filename
        val page = image.split(".")

        // open gui based on filename
        when(page[0]) {
            "MetroTileHomepage1" -> (workbench).replaceWith(metroTileHomepage1, null, sizeToScene = true)
            "MetroTileHomepage2" -> (workbench).replaceWith(metroTileHomepage2, null, sizeToScene = true)
            "MetroTileHomepage3" -> (workbench).replaceWith(metroTileHomepage3, null, sizeToScene = true)
            "MetroTileHomepage4" -> (workbench).replaceWith(metroTileHomepage4, null, sizeToScene = true)
            "MetroTileHomepage5" -> (workbench).replaceWith(metroTileHomepage5, null, sizeToScene = true)
            "MetroTileHomepage6" -> (workbench).replaceWith(metroTileHomepage6, null, sizeToScene = true)
            "MetroTileHomepage7" -> (workbench).replaceWith(metroTileHomepage7, null, sizeToScene = true)
            "MetroTileHomepage8" -> (workbench).replaceWith(metroTileHomepage8, null, sizeToScene = true)
            "MetroTileHomepage9" -> (workbench).replaceWith(metroTileHomepage9, null, sizeToScene = true)
            "MetroTileHomepage10" -> (workbench).replaceWith(metroTileHomepage10, null, sizeToScene = true)
            "MetroTileHomepage11" -> (workbench).replaceWith(metroTileHomepage11, null, sizeToScene = true)
            "MetroTileHomepage12" -> (workbench).replaceWith(metroTileHomepage12, null, sizeToScene = true)
            "MetroTileHomepage13" -> (workbench).replaceWith(metroTileHomepage13, null, sizeToScene = true)
            "MetroTileHomepage14" -> (workbench).replaceWith(metroTileHomepage14, null, sizeToScene = true)
            "MetroTileHomepage15" -> (workbench).replaceWith(metroTileHomepage15, null, sizeToScene = true)
            "MetroTileHomepage16" -> (workbench).replaceWith(metroTileHomepage16, null, sizeToScene = true)
            "MetroTileHomepage17" -> (workbench).replaceWith(metroTileHomepage17, null, sizeToScene = true)
            "MetroTileHomepage18" -> (workbench).replaceWith(metroTileHomepage18, null, sizeToScene = true)
            "MetroTileHomepage19" -> (workbench).replaceWith(metroTileHomepage19, null, sizeToScene = true)
            "MetroTileHomepage20" -> (workbench).replaceWith(metroTileHomepage20, null, sizeToScene = true)
        }
    }
}