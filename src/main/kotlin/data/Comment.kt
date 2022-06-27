package data

data class Comment (
    val commentId:Int,
    val noteId: Int,
    val ownerId: Int,
    val replyTo: Int?,
    val message: String,
    val guid: String,
    var isDeleted: Boolean = false
)