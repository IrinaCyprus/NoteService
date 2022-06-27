package data

data class Note(
    var noteId: Int,
    val ownerId: Int,
    var title: String,
    var text: String,
    var privacy: Int,
    var commentPrivacy: Int,
    var privacyView: String,
    var privacyComment: String
)