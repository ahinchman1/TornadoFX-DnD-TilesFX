package com.example.demo.model

import javafx.scene.shape.Rectangle
import tornadofx.*



class RectangleModel(var rectangle: Rectangle) : ViewModel() {
    val fill = bind { rectangle.observable(Rectangle::fillProperty) }
    val width = bind { rectangle.observable(Rectangle::widthProperty) }
    val height = bind { rectangle.observable(Rectangle::heightProperty) }
}

