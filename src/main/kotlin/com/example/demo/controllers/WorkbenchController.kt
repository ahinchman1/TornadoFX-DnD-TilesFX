package com.example.demo.controllers

import com.example.demo.views.MetroTileHomepages.*
import com.example.demo.views.Workbench
import tornadofx.*

class WorkbenchController : Controller() {
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
            "MetroTileHomepage1" -> (workspace).replaceWith(metroTileHomepage1, null, sizeToScene = true)
            "MetroTileHomepage2" -> (workspace).replaceWith(metroTileHomepage2, null, sizeToScene = true)
            "MetroTileHomepage3" -> (workspace).replaceWith(metroTileHomepage3, null, sizeToScene = true)
            "MetroTileHomepage4" -> (workspace).replaceWith(metroTileHomepage4, null, sizeToScene = true)
            "MetroTileHomepage5" -> (workspace).replaceWith(metroTileHomepage5, null, sizeToScene = true)
            "MetroTileHomepage6" -> (workspace).replaceWith(metroTileHomepage6, null, sizeToScene = true)
            "MetroTileHomepage7" -> (workspace).replaceWith(metroTileHomepage7, null, sizeToScene = true)
            "MetroTileHomepage8" -> (workspace).replaceWith(metroTileHomepage8, null, sizeToScene = true)
            "MetroTileHomepage9" -> (workspace).replaceWith(metroTileHomepage9, null, sizeToScene = true)
            "MetroTileHomepage10" -> (workspace).replaceWith(metroTileHomepage10, null, sizeToScene = true)
            "MetroTileHomepage11" -> (workspace).replaceWith(metroTileHomepage11, null, sizeToScene = true)
            "MetroTileHomepage12" -> (workspace).replaceWith(metroTileHomepage12, null, sizeToScene = true)
            "MetroTileHomepage13" -> (workspace).replaceWith(metroTileHomepage13, null, sizeToScene = true)
            "MetroTileHomepage14" -> (workspace).replaceWith(metroTileHomepage14, null, sizeToScene = true)
            "MetroTileHomepage15" -> (workspace).replaceWith(metroTileHomepage15, null, sizeToScene = true)
            "MetroTileHomepage16" -> (workspace).replaceWith(metroTileHomepage16, null, sizeToScene = true)
            "MetroTileHomepage17" -> (workspace).replaceWith(metroTileHomepage17, null, sizeToScene = true)
            "MetroTileHomepage18" -> (workspace).replaceWith(metroTileHomepage18, null, sizeToScene = true)
            "MetroTileHomepage19" -> (workspace).replaceWith(metroTileHomepage19, null, sizeToScene = true)
            "MetroTileHomepage20" -> (workspace).replaceWith(metroTileHomepage20, null, sizeToScene = true)


        }
    }
}