package com.example.demo.app

import javafx.scene.effect.DropShadow
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    // holds class-level properties that can easily be retrieved
    companion object {
        val loginScreen by cssclass()
        val workbenchScreen by cssclass()
        val tileGUI by cssclass()
        val grid by cssclass()
        val module by cssclass()
        val highlightTile by cssclass()
        val workAreaSelected by cssclass()
        val inflight by cssclass()
        val transparentOverlay by cssclass()
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

        tileGUI {
            backgroundColor += Color.rgb(34, 34, 34)
        }

        grid {
            backgroundColor += Color.WHITE
        }

        module {
            borderColor += box(Color.BLACK)
        }

        tileGUI {
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
            opacity = 1.0
            effect = DropShadow()
        }

        workAreaSelected {
            opacity = 0.9
        }

        transparentOverlay {
            backgroundColor += c(0, 100, 100, 0.05)
        }
    }
}