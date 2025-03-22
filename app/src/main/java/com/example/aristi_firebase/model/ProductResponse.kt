package com.example.aristi_firebase.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products") val products: List<Product>
)

data class Product(
    @SerializedName("info") val info: Info
)

data class Info(
    @SerializedName("id") val id: Int,
    @SerializedName("slug") val slug: String,
    @SerializedName("title") val title: String,
    @SerializedName("create_date") val createDate: String,
    @SerializedName("modified_date") val modifiedDate: String,
    @SerializedName("status") val status: String,
    @SerializedName("link") val link: String,
    @SerializedName("content") val content: String,
    @SerializedName("excerpt") val excerpt: String?,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("category") val category: List<Category>,
    @SerializedName("tags") val tags: List<Tag>,
    @SerializedName("bpm") val bpm: Int,
    @SerializedName("duration") val duration: String
)

data class Category(
    @SerializedName("term_id") val termId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("term_group") val termGroup: Int,
    @SerializedName("term_taxonomy_id") val termTaxonomyId: Int,
    @SerializedName("taxonomy") val taxonomy: String,
    @SerializedName("description") val description: String,
    @SerializedName("parent") val parent: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("filter") val filter: String
)

data class Tag(
    @SerializedName("term_id") val termId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("term_group") val termGroup: Int,
    @SerializedName("term_taxonomy_id") val termTaxonomyId: Int,
    @SerializedName("taxonomy") val taxonomy: String,
    @SerializedName("description") val description: String,
    @SerializedName("parent") val parent: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("filter") val filter: String
)
