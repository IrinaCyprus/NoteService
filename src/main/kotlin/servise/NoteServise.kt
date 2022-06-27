package servise

import CommentNotFoundException
import data.Comment
import data.Note

object NoteServise {
    private var currentNoteId = 0
    private var currentCommentId = 0
    private var notes = mutableListOf<Note>()
    var comments = mutableListOf<Comment?>()
    var deletedNotes = mutableListOf<Note?>()
    var deletedComments = mutableListOf<Comment?>()

    fun addNote(note: Note): Int {                                    //создает новую заметку для текущего пользователя
        currentNoteId++
        val addNote = note.copy(noteId = currentNoteId)
        notes.add(addNote)
        return addNote.noteId
    }

    fun createComment(comment: Comment): Int {                       //Добавляет новый комментарий к заметке
        currentCommentId++
        val addComment = comment.copy(commentId = currentCommentId)
        for (note in notes) {
            if (comment.noteId == note.noteId) {
                comments.add(addComment)
                return addComment.commentId
            }
        }
        return 0
    }

    fun deleteNote(noteToDelete: Note): Boolean {                    //Удаляет заметку текущего пользователя.
        for (note in notes) {
            if (noteToDelete.noteId == note.noteId) {
                deletedNotes.add(noteToDelete)
                notes.remove(note)
                return true
            }
        }
        return false
    }

    fun deleteComment(commentToDelete: Comment): Boolean {           //Удаляет комментарий к заметке.
        for (comment in comments) {
            if (commentToDelete.commentId == comment?.commentId) {
                deletedComments.add(commentToDelete)
                comments.remove(comment)
                return true
            }
        }
        throw CommentNotFoundException()
    }

    fun editNote(note: Note): Boolean {                              //Редактирует заметку текущего пользователя.
        for ((index, item) in notes.withIndex()) {
            if (item.noteId == note.noteId) {
                notes[index] = note
                return true
            }
        }
        return false
    }

    fun editComment(comment: Comment): Boolean {                     // Редактирует указанный комментарий у заметки.
        for ((index, item) in comments.withIndex()) {
            if (item?.commentId == comment.commentId) {
                comments[index] = comment
                return true
            }
        }
        return false
    }

    fun get(ownerId: Int): List<Note> {                            // Возвращает список заметок, созданных пользователем.
        val ownerNotes = mutableListOf<Note>()
        for (note in notes) {
            if (ownerId == note.ownerId)
                ownerNotes.add(note)
            return ownerNotes
        }
        return emptyList()
    }

    fun getById(noteId: Int): Note? {                             // Возвращает заметку по её id.
        for (note in notes) {
            if (note.noteId == noteId) {
                return note
            }
        }
        return null
    }

    fun getComments(noteId: Int): List<Comment> {                //Возвращает список комментариев к заметке.
        val listCommentByNote = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment?.noteId == noteId) {
                return listCommentByNote
            }
        }
        return emptyList()
    }

    fun restoreComment(remoteId: Int): Boolean {                //Восстанавливает удалённый комментарий.
        for (comment in deletedComments) {
            if (comment?.commentId == remoteId) {
                comments.add(comment)
                deletedComments.remove(comment)
                return true
            }
        }
        throw CommentNotFoundException()
    }

    fun clear() {
        notes.clear()
    }
}
