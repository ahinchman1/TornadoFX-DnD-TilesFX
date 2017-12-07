package com.example.demo.controllers

import eu.hansolo.tilesfx.Tile
import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.input.DragEvent
import javafx.scene.input.Dragboard
import javafx.scene.input.MouseEvent

lateinit var mDragTile: Tile
lateinit var mModuleDragOverRoot: EventHandler<MouseEvent>
lateinit var mModuleDragOverLeftPane: EventHandler<MouseEvent>
lateinit var mModuleDragDropped: EventHandler<MouseEvent>

private fun getGraphPosition(event: DragEvent) {
    var db: Dragboard = event.dragboard
    var node: Node = event.pickResult.intersectedNode
    if (node != target && db.hasContent(TILES)) {

    }
}

fun Dragboard.handle(event: DragEvent) {
    var db: Dragboard = event.dragboard
    var node: Node = event.pickResult.intersectedNode
    if (node != target && db.hasContent(TILES)) {

    }
}

private fun addDragDetection(dragTile: Tile) {
    val dragScope = MetroScope()
    val mDragOffset = Point2D(0.0, 0.0)
    dragTile.setOnDragDetected { javafx.event.EventHandler<javafx.scene.input.MouseEvent>  {
        fun handle(event: javafx.scene.input.MouseEvent) {
            // set drag event handlers
            dragScope.view.root.center.setOnDragOver {
                dragScope.view.mModuleDragOverRoot
            }

            dragScope.view.root.right.setOnDragOver {
                dragScope.view.mModuleDragOverLeftPane
            }

            dragScope.view.root.right.setOnDragOver {
                dragScope.view.mModuleDragDropped
            }

            // reference the clicked Tile
            val tile: Tile = event.source //casting attempt?

            // begin drag operations
            dragScope.view.mDragTile = tile
            dragScope.view.relocateToPoint()
        }

        fun relocateToPoint (p: Point2D) {

            //relocates the object to a point that has been converted to
            //scene coordinates
            val localCoords: Point2D =

                    relocate (
                            (localCoords.x - mDragOffset.x), // how to cast Int?
                            (localCoords.y - mDragOffset.y)
                    )
        }
    }
    }