package com.example.demo.app

import javafx.scene.effect.DropShadow
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    // holds class-level properties that can easily be retrieved
    companion object {
        val loginScreen by cssclass()
        val workbenchScreen by cssclass()
        val metroTileHomepageGUI by cssclass()
        val grid by cssclass()
        val banner_tile by cssclass()
        val module_tile by cssclass()
        val module by cssclass()
        val highlightTile by cssclass()
        val workAreaSelected by cssclass()
        val inflight by cssclass()
    }

    // apply styling to classes
    init {
        reloadStylesheetsOnFocus()

        select(loginScreen) {
            padding = box(15.px)
            vgap = 7.px
            hgap = 10.px
        }

        workbenchScreen {
            backgroundColor += Color.rgb(34, 34, 34)
        }

        metroTileHomepageGUI {
            backgroundColor += Color.rgb(34, 34, 34)
        }

        grid {
            backgroundColor += Color.WHITE
        }

        module {
            borderColor += box(Color.BLACK)
        }

        metroTileHomepageGUI {
            form {
                label {
                    textFill = Color.WHITE
                }
            }
        }

        highlightTile {
            opacity = 0.4
        }

        inflight {
            opacity = 0.7
            effect = DropShadow()
        }

        workAreaSelected {
            borderColor += box(Color.BLACK)
            borderWidth += box(3.px)
        }
    }
}