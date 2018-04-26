package com.example.demo.views

import com.example.demo.app.Styles
import com.example.demo.model.GridInfoModel
import com.example.demo.model.GridScope
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints
import tornadofx.*

class MyTiles : View() {
    /***** Global Variables *****/
    private val model: GridInfoModel by inject()

    /***** View *****/
    override val root = gridpane {
        addClass(Styles.grid)
        alignment = Pos.CENTER
        hgap = 5.0
        vgap = 5.0

        columnConstraints.clear()
        rowConstraints.clear()

        for (i in 0 until model.item.columns) {
            val c = ColumnConstraints().apply {
                halignment = HPos.CENTER
                hgrow = Priority.NEVER
                minWidth = 100.0
                maxWidth = 100.0
            }
            columnConstraints.add(c)
        }

        for (i in 0 until model.item.rows) {
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
        model.item.moduleTiles.forEach {
            root.add(it.tile, it.colIndex, it.rowIndex, it.colSpan, it.rowSpan)
        }
    }
}

