package com.sample.headlinesbyjusassignment.model

data class HeadlinesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)