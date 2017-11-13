package com.example.demo.views.metroTileHomepages

import com.example.demo.app.Styles
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.WorkbenchController
import javafx.application.Platform
import javafx.scene.text.Font
import tornadofx.*

class MetroTileHomepage19 : View("My View") {
    private val loginController: LoginController by inject()
    private val workbenchController: WorkbenchController by inject()

    override val root = borderpane()

    init {
        with(root) {
            addClass(Styles.metroTileHomepageGUI)
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
            right {
                button("Return to Workbench") {
                    isDefaultButton = true

                    setOnAction {
                        workbenchController.returnToWorkbench(this@MetroTileHomepage19)
                    }
                }
            }
        }
    }
}
