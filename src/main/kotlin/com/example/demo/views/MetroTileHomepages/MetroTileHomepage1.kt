package com.example.demo.views.MetroTileHomepages

import com.example.demo.app.Styles.Companion.GUI
import com.example.demo.app.Styles.Companion.metroRect
import com.example.demo.app.Styles.Companion.metroTileHomepageGUI
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.WorkbenchController
import com.example.demo.model.RectangleModel
import javafx.application.Platform
import javafx.scene.control.TableView
import javafx.scene.control.TextInputDialog
import javafx.scene.input.TransferMode
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.stage.FileChooser
import org.osgi.framework.Bundle
import org.osgi.framework.startlevel.BundleStartLevel
import tornadofx.*
import tornadofx.osgi.impl.fxBundleContext
import java.nio.file.Files

class MetroTileHomepage1 : View("My View") {
    private val loginController: LoginController by inject()
    private val workbenchController: WorkbenchController by inject()
    val sampleRect = listOf(Rectangle(75.0, 75.0, Color.BLUE)).observable()
    val model : RectangleModel by inject()

    override val root = borderpane()

    init {
        with(root) {
            addClass(metroTileHomepageGUI)
            setPrefSize(750.0, 700.0)

            top {
                label(title) {
                    font = Font.font(22.0)
                }
                menubar {
                    menu("File") {
                        item("Logout").action {
                            loginController.logout()
                        }
                        item("Quit").action {
                            Platform.exit()
                        }
                    }
                }
            }

            center {
                vbox {
                    addClass(GUI)
                    gridpane {
                        row{
                            // Greeting Banner
                            rectangle {
                                addClass(metroRect)
                                model.fill
                                model.width
                                model.height
                                gridpaneConstraints {
                                    marginBottom = 10.0
                                    marginRight = 10.0
                                    columnSpan = 3
                                }
                            }
                            // Module 10
                            rectangle {
                                addClass(metroRect)
                                model.fill
                                model.width
                                model.height
                                gridpaneConstraints {
                                    marginBottom = 10.0
                                    marginRight = 10.0
                                    columnSpan = 3
                                }
                            }
                        }
                        row {
                            // Module 1
                            rectangle {
                                addClass(metroRect)
                                model.fill
                                model.width
                                model.height
                                gridpaneConstraints {
                                    marginBottom = 10.0
                                    marginRight = 10.0
                                }
                            }
                            // Module 2
                            rectangle {
                                addClass(metroRect)
                                model.fill
                                model.width
                                model.height
                                gridpaneConstraints {
                                    marginBottom = 10.0
                                    marginRight = 10.0
                                }
                            }
                            // Module 3
                            rectangle {
                                addClass(metroRect)
                                model.fill
                                model.width
                                model.height
                                gridpaneConstraints {
                                    border
                                    marginBottom = 10.0
                                    marginRight = 10.0
                                }
                            }
                            // Module 4
                            rectangle {
                                addClass(metroRect)
                                model.fill
                                model.width
                                model.height
                                gridpaneConstraints {
                                    marginBottom = 10.0
                                    marginRight = 10.0
                                }
                            }
                            // Module 5
                            rectangle {
                                addClass(metroRect)
                                model.fill
                                model.width
                                model.height
                                gridpaneConstraints {
                                    marginBottom = 10.0
                                    marginRight = 10.0
                                    columnSpan = 2
                                    rowSpan = 2
                                }
                            }
                        }
                        row{}
                        row{}

                    }

                    /*var orgSceneX: Double = -1.0
                    var orgSceneY: Double = -1.0

                    setOnMousePressed { event ->
                        orgSceneX = event.sceneX
                        orgSceneY = event.sceneY

                        event.source
                    }

                    setOnMouseDragged { event ->
                        var offsetX = event.sceneX - orgSceneX
                        var offsetY = event.sceneY - orgSceneY

                        event.source



                        orgSceneX = event.sceneX
                        orgSceneY = event.sceneY
                        event.consume()
                    }*/
                }
            }

            right {
                button("Return to Workbench") {
                    isDefaultButton = true

                    setOnAction {
                        workbenchController.returnToWorkbench(this@MetroTileHomepage1)
                    }
                }
            }
        }
    }
}

