package com.example.demo.views

import com.example.demo.model.GridScope
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints
import tornadofx.View
import tornadofx.gridpane

class MyTiles : View() {
    /***** Global Variables *****/
    override val scope = super.scope as GridScope
    private val model = scope.model
    private val gridInfo = model.item

    /***** View *****/
    override val root = gridpane {

        alignment = Pos.CENTER
        hgap = 5.0
        vgap = 5.0

        columnConstraints.clear()
        rowConstraints.clear()

        for (i in 0 until gridInfo.columns) {
            val c = ColumnConstraints().apply {
                halignment = HPos.CENTER
                hgrow = Priority.NEVER
                minWidth = 100.0
                maxWidth = 100.0
            }
            columnConstraints.add(c)
        }

        for (i in 0 until gridInfo.rows) {
            val r = RowConstraints().apply {
                valignment = VPos.CENTER
                vgrow = Priority.NEVER
                minHeight = 100.0
                maxHeight = 100.0
            }
            rowConstraints.add(r)
        }
    }

    init {
        gridInfo.moduleTiles.forEach {
            root.add(it.tile, it.colIndex, it.rowIndex, it.colSpan, it.rowSpan)
        }
    }
}

