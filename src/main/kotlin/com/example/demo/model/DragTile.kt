package com.example.demo.model

import eu.hansolo.tilesfx.Tile
import javafx.beans.property.Property
import tornadofx.Commit
import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

/***** Data classes and models intended for tile and grid location, saving
 *     tile objects needed for module rendering,
 *     dragging, and copying in a view  *****/

class DragTile(tile: Tile, colSpan: Int, rowSpan: Int, colIndex: Int,
               rowIndex: Int) {
    var tile by property(tile)
    fun tileProperty() = getProperty(DragTile::tile)

    var colSpan by property(colSpan)
    fun colSpanProperty() = getProperty(DragTile::colSpan)

    var rowSpan by property(rowSpan)
    fun rowSpanProperty() = getProperty(DragTile::rowSpan)

    var colIndex by property(colIndex)
    fun colIndexProperty() = getProperty(DragTile::colIndex)

    var rowIndex by property(rowIndex)
    fun rowIndexProperty() = getProperty(DragTile::rowIndex)
}

class DragTileModel : ItemViewModel<DragTile>() {
    private val tile = bind { item?.tileProperty()  }
    private val colSpan = bind { item?.colSpanProperty() }
    private val rowSpan = bind { item?.rowSpanProperty() }
    private val colIndex = bind { item?.colIndexProperty() }
    private val rowIndex = bind { item?.rowIndexProperty() }

    override fun onCommit(commits: List<Commit>) {
        super.onCommit(commits)

        // The println will only be called if findChanged is not null
        commits.findChanged(tile)?.let { println("Module Tile changed from ${it.first} to ${it.second}")}
        commits.findChanged(colSpan)?.let { println("Column Span changed from ${it.first} to ${it.second}")}
        commits.findChanged(rowSpan)?.let { println("Row Span changed from ${it.first} to ${it.second}")}
        commits.findChanged(colIndex)?.let { println("Column Index changed from ${it.first} to ${it.second}")}
        commits.findChanged(rowIndex)?.let { println("Row Index changed from ${it.first} to ${it.second}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}