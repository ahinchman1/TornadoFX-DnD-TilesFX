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

        // open gui based on filename
        when {
            image === ("/img/MetroTileHomepage.png") -> (workbench).replaceWith(metroTileHomepage1, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage2.png") -> (workbench).replaceWith(metroTileHomepage2, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage3.png") -> (workbench).replaceWith(metroTileHomepage3, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage4.png") -> (workbench).replaceWith(metroTileHomepage4, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage5.png") -> (workbench).replaceWith(metroTileHomepage5, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage6.png") -> (workbench).replaceWith(metroTileHomepage6, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage7.png") -> (workbench).replaceWith(metroTileHomepage7, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage8.png") -> (workbench).replaceWith(metroTileHomepage8, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage9.png") -> (workbench).replaceWith(metroTileHomepage9, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage10.png") -> (workbench).replaceWith(metroTileHomepage10, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage11.png") -> (workbench).replaceWith(metroTileHomepage11, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage12.png") -> (workbench).replaceWith(metroTileHomepage12, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage13.png") -> (workbench).replaceWith(metroTileHomepage13, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage14.png") -> (workbench).replaceWith(metroTileHomepage14, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage15.png") -> (workbench).replaceWith(metroTileHomepage15, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage16.png") -> (workbench).replaceWith(metroTileHomepage16, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage17.png") -> (workbench).replaceWith(metroTileHomepage17, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage18.png") -> (workbench).replaceWith(metroTileHomepage18, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage19.png") -> (workbench).replaceWith(metroTileHomepage19, null, sizeToScene = true)
            image === ("/img/MetroTileHomepage20.png") -> (workbench).replaceWith(metroTileHomepage20, null, sizeToScene = true)
        }

    }

    fun returnToWorkbench(className: UIComponent) {
        (className).replaceWith(workbench, null, sizeToScene = true)
    }
}