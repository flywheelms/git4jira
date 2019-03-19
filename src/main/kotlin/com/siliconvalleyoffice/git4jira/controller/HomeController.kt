package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.Home
import com.siliconvalleyoffice.git4jira.model.GitBaseUrl
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.service.git.GitAuthInterceptor
import com.siliconvalleyoffice.git4jira.view.ProjectProfileView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

class HomeController(val homeView: Home.View,
                     val loginService: Service.Login,
                     val jsonFileService: Service.JsonFiles) : Home.Controller {

    override fun projectNames() = jsonFileService.projectNames()

    override fun lastSelectedProject() = jsonFileService.getLastSelectedProject()

    override fun onEditButtonClick() {
        ProjectProfileView().openWindow(escapeClosesWindow = false, block = true)
        homeView.updateView()
    }

    override fun onPrintButtonClick() {
        showMessageDialog("Print Tab Summary")
    }

    override fun onLogoutButtonClick() {
        loginService.logout()
        homeView.launchLoginView()
    }

    override fun onGitHubErrorClick() {
        showMessageDialog("GitHub CustomError")
    }

    override fun onJiraErrorClick() {
        showMessageDialog("Jira CustomError")
    }

    override fun onSlackErrorClick() {
        showMessageDialog("Slack CustomError")
    }

    override fun onTeamCityClick() {
        showMessageDialog("Team City CustomError")
    }

    override fun onChoiceBoxSelectionChanged(selectedValue: String) {
        jsonFileService.updateLastSelectedProject(selectedValue)

        //Validate Credentials
        initGitHub()

        homeView.refreshTabs()
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }

    /**
     * Gather GitHub Data and Validate Credentials
     */
    private fun initGitHub() {
        val lastSelectedProject = jsonFileService.getLastSelectedProject()
        lastSelectedProject?.gitService?.gitServiceEnum?.service?.authenticate()?.subscribe(
                { homeView.gitErrorIconVisibility(false) }, { homeView.gitErrorIconVisibility(true) }
        )
    }
}