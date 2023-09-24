package com.example.dt4ce.network.model

data class Content(
    val againstVotesCount: Int?,
    val createdAt: String?,
    val description: String?,
    val for_votes_count: Int?,
    val id: Int?,
    val imageUrls: List<String?>?,
    val title: String?,
    val updatedAt: String?,
    val user: User?,
    val views_count: Int?
)