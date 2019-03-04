package com.siliconvalleyoffice.git4jira.service.crendential

import com.google.gson.Gson
import com.siliconvalleyoffice.git4jira.util.GITHUB_BASE_URL
import com.siliconvalleyoffice.git4jira.constant.MESSAGE
import com.siliconvalleyoffice.git4jira.constant.USER
import com.siliconvalleyoffice.git4jira.model.User
import com.siliconvalleyoffice.git4jira.model.CustomError
import com.siliconvalleyoffice.git4jira.service.Service
import tornadofx.*
import javax.json.JsonObject


class LoginService(val gson: Gson): Service.Login {
    val api: Rest = Rest()
    var user: User? = null

    init {
        api.baseURI = GITHUB_BASE_URL
    }

    override fun login(username: String, password: String): Pair<User?, CustomError?> {
        api.setBasicAuth(username, password)
        val response = api.get(USER)
        val json: JsonObject = response.one()
        if (response.ok()) {
            user = gson.fromJson(json.toString(), User::class.java)
            return Pair(user, null)
        } else {
            return Pair(null, CustomError(json.string(MESSAGE)))
        }
    }

    override fun logout() {
        user = null
    }
}