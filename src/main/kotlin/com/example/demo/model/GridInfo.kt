package com.example.demo.model

import javafx.beans.property.Property
import tornadofx.Commit
import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

/***** Data classes and models intended for grid rendering *****/

class GridInfo(info: Pair<Pair<Int, Int>, List<TilePlacement>>) {

    private var coordinates by property(info.first)
    fun coordinatesProperty() = getProperty(GridInfo::coordinates)

    var rows: Int by property(info.first.first)
    var columns: Int by property(info.first.second)
    var moduleTiles by property(info.second)
}

class GridInfoModel : ItemViewModel<GridInfo>() {
    private val coordinates = bind { item?.coordinatesProperty() }

    override fun onCommit(commits: List<Commit>) {
        super.onCommit(commits)

        // The println will only be called if findChanged is not null
        commits.findChanged(coordinates)?.let { println("Grid Info changed from ${it.first} to ${it.second}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}
