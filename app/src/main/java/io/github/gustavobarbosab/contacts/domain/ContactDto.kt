package io.github.gustavobarbosab.contacts.domain

data class ContactDto(
    val id: Long,
    val imageUrl: String? = null,
    val name: String,
    var phoneList: List<String> = listOf()
) {
    val firstPhone = phoneList.firstOrNull()
}