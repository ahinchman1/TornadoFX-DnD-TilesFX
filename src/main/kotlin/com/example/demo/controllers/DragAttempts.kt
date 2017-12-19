package com.example.demo.controllers

import com.example.demo.model.GridInfo
import com.example.demo.model.GridScope
import com.example.demo.views.MetroTileHomepage
import com.example.demo.views.MyTiles
import eu.hansolo.tilesfx.Tile
import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.input.DragEvent
import javafx.scene.input.Dragboard
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import tornadofx.*

/*class DragController: Controller() {
    private val workbenchController: WorkbenchController by inject()
    private val controller: MetroTileHomepageController by inject()
    private val view: MetroTileHomepage by inject()
    private val gridInfo = GridInfo(controller.useTileGrid(workbenchController.metroTile))
    val grid = passGridInfo(gridInfo)


}

private fun passGridInfo(gridInfo: GridInfo): GridPane {
    val metroScope = GridScope()
    metroScope.model.item = gridInfo
    return find<MyTiles>(metroScope).root
}*/