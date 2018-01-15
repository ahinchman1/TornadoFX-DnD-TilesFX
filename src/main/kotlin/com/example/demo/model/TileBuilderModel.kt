package com.example.demo.model

import javafx.beans.property.Property
import javafx.scene.paint.Color
import tornadofx.*

/***** Data classes and model intended for saving
 *     tile objects needed for module rendering,
 *     dragging, and copying in a view *****/

class TileBuilder(width: Double, height: Double, tileColor: Color, title: String) {
    var width by property(width)
    fun widthProperty() = getProperty(TileBuilder::width)

    var height by property(height)
    fun heightProperty() = getProperty(TileBuilder::height)

    var tileColor by property(tileColor)
    fun tileColorProperty() = getProperty(TileBuilder::tileColor)

    var title by property(title)
    fun titleProperty() = getProperty(TileBuilder::title)
}

class TileBuilderModel : ItemViewModel<TileBuilder>() {
    private val width = bind { item?.widthProperty() }
    private val height = bind { item?.heightProperty() }
    private val tileColor = bind { item?.tileColorProperty() }
    private val title = bind { item?.titleProperty() }

    override fun onCommit(commits: List<Commit>) {
        super.onCommit(commits)

        // The println will only be called if findChanged is not null
        commits.findChanged(width)?.let { println("Width changed from ${it.first} to ${it.second}")}
        commits.findChanged(height)?.let { println("Height changed from ${it.first} to ${it.second}")}
        commits.findChanged(tileColor)?.let { println("Tile Color changed from ${it.first} to ${it.second}")}
        commits.findChanged(title)?.let { println("Title changed from ${it.first} to ${it.second}")}

    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}
