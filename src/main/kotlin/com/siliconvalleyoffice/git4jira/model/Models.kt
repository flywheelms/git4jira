package com.siliconvalleyoffice.git4jira.model

/**
 * GitHub User Response Data Object
 */
data class User(
    val avatar_url: String,
    val bio: String,
    val blog: String,
    val collaborators: Int,
    val company: Any,
    val created_at: String,
    val disk_usage: Int,
    val email: String,
    val events_url: String,
    val followers: Int,
    val followers_url: String,
    val following: Int,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val hireable: Boolean,
    val html_url: String,
    val id: Int,
    val location: Any,
    val login: String,
    val name: String,
    val node_id: String,
    val organizations_url: String,
    val owned_private_repos: Int,
    val plan: Plan,
    val private_gists: Int,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val total_private_repos: Int,
    val two_factor_authentication: Boolean,
    val type: String,
    val updated_at: String,
    val url: String
)

data class Plan(
    val collaborators: Int,
    val name: String,
    val private_repos: Int,
    val space: Int
)

/**
 * Error Object to capture basic Error Information
 */
data class Error(var message: String, var code: Int)