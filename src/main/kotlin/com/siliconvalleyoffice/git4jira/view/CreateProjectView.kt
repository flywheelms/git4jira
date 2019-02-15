package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.CREATE_PROJECT_DIALOG_VIEW
import com.siliconvalleyoffice.git4jira.app.EMPTY
import com.siliconvalleyoffice.git4jira.contracts.CreateProject
import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.dagger.CreateProjectModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.services.RxService
import javafx.collections.FXCollections
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.io.File
import javax.inject.Inject

class CreateProjectView : View(), CreateProject.View {

    @Inject
    lateinit var createProjectController: CreateProject.Controller

    override val root: BorderPane by fxml(CREATE_PROJECT_DIALOG_VIEW)

    val createButton: Button by fxid("createButton")
    val cancelButton: Button by fxid("cancelButton")
    val browseButton: Button by fxid("browseButton")

    val projectName: TextField by fxid("projectName")
    val projectLogo: TextField by fxid("projectLogo")
    val versionControl: ChoiceBox<String> by fxid("versionControl")
    val projectManagement: ChoiceBox<String> by fxid("projectManagement")
    val communication: ChoiceBox<String> by fxid("communication")
    val continuousIntegration: ChoiceBox<String> by fxid("continuousIntegration")

    init {
        Injector.Instance.appComponent.plus(CreateProjectModule(this)).inject(this)

        setUpInitialView()
        assignButtonListeners()
        setPrimaryStageDimensions()
    }

    private fun setUpInitialView() {
        versionControl.items = FXCollections.observableArrayList(createProjectController.versionControlItems())
        projectManagement.items = FXCollections.observableArrayList(createProjectController.projectManagementItems())
        communication.items = FXCollections.observableArrayList(createProjectController.communicationItems())
        continuousIntegration.items = FXCollections.observableArrayList(createProjectController.continuousIntegrationItems())
    }

    private fun assignButtonListeners() {
        browseButton.setOnMouseClicked { createProjectController.onBrowseClick() }
        createButton.setOnMouseClicked { createProjectController.onCreateClick() }
        cancelButton.setOnMouseClicked { createProjectController.onCancelClick() }
    }

    override fun updateProjectLogoPath(logoFile: File?) {
        projectLogo.text = logoFile?.absolutePath
    }

    override fun closeView() = close()


    override fun versionControlSelection() = versionControl.value ?: EMPTY

    override fun projectManagementSelection() = projectManagement.value ?: EMPTY

    override fun communicationSelection() = communication.value ?: EMPTY

    override fun continuousIntegrationSelection() = continuousIntegration.value ?: EMPTY

    override fun projectName() = projectName.text ?: EMPTY

    override fun projectLogo() = projectLogo.text ?: EMPTY

    private fun setPrimaryStageDimensions() { /*TODO*/ }
}