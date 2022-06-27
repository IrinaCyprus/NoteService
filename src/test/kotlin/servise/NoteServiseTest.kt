package servise

import CommentNotFoundException
import data.Comment
import data.Note

import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class NoteServiseTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        NoteServise.clear()
    }

    @Test
    fun createNoteTest() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        val expected = 1
        val actual = NoteServise.addNote(note)

        assertEquals(expected, actual)
    }

    @Test
    fun createCommentTest() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)

        val comment = Comment(0, 1, 3, 0, "", "", false)
        val expected = 1
        val actual = NoteServise.createComment(comment)

        assertEquals(expected, actual)
    }

    @Test
    fun deleteNoteTrue() {
        val note = Note(1, 2, "title", "afghan", 6, 2, "", "")
        val noteToDelete = Note(1, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)
        val actual = NoteServise.deleteNote(noteToDelete)

        assertTrue(actual)
    }

    @Test
    fun deleteNoteFalse() {
        val note = Note(1, 2, "title", "afghan", 6, 2, "", "")
        val noteToDelete = Note(2, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)
        val actual = NoteServise.deleteNote(noteToDelete)

        assertFalse(actual)
    }

    @Test
    fun deleteCommentTrue() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)

        val comment = Comment(0, 1, 3, 0, "", "", false)
        val commentToDelete = Comment(1, 1, 3, 0, "", "", false)
        NoteServise.createComment(comment)
        val actual = NoteServise.deleteComment(commentToDelete)

        assertTrue(actual)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentFalse() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)
        val comment = Comment(0, 1, 3, 0, "", "", false)
        val commentToDelete = Comment(3, 1, 3, 0, "", "", false)
        NoteServise.createComment(comment)
        NoteServise.deleteComment(commentToDelete)
    }

    @Test
    fun editTrue() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)
        val editNote = Note(1, 2, "title", "изменена", 6, 2, "", "")
        val actual = NoteServise.editNote(editNote)

        assertTrue(actual)
    }

    @Test
    fun editFalse() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)
        val editNote = Note(4, 2, "title", "изменена", 6, 2, "", "")
        val actual = NoteServise.editNote(editNote)

        assertFalse(actual)
    }

    @Test
    fun editCommentTrue() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)
        val comment = Comment(0, 1, 3, 0, "", "", false)
        NoteServise.createComment(comment)
        val editComment = Comment(1, 1, 3, 0, "xfykx", "", false)
        val actual = NoteServise.editComment(editComment)

        assertTrue(actual)
    }

    @Test
    fun editCommentFalse() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)
        val comment = Comment(0, 1, 3, 0, "", "", false)
        NoteServise.createComment(comment)
        val editComment = Comment(2, 1, 3, 0, "xfykx", "", false)
        val actual = NoteServise.editComment(editComment)

        assertFalse(actual)
    }

    @Test
    fun get() {
        val note1 = Note(0, 2, "title", "afghan", 6, 2, "", "")
        val note2 = Note(1, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note1)
        NoteServise.addNote(note2)

        val actual = NoteServise.get(ownerId = 2)
        assertEquals(2, actual)
//        assertEquals(arrayListOf(note1.ownerId, note2.ownerId), actual)
    }

    @Test
    fun getById() {
        val note = Note(1, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)
//        val expected = 2
        val actual = NoteServise.getById(noteId = 1)
        assertEquals(note, actual)
    }

    @Test
    fun getComments() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)

        val comment1 = Comment(0, 1, 4, 0, "1", "", false)
        val comment2 = Comment(1, 1, 3, 0, "2", "", false)
        NoteServise.createComment(comment1)
        NoteServise.createComment(comment2)

        val actual = NoteServise.getComments(noteId = 1)
        assertEquals(arrayListOf(comment1,comment2), actual)
    }

    @Test
    fun restoreCommentTrue() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)

        val comment = Comment(0, 1, 3, 0, "", "", false)
        NoteServise.createComment(comment)

        val commentToDelete = Comment(1, 1, 3, 0, "", "", false)
        NoteServise.deleteComment(commentToDelete)

        val actual = NoteServise.restoreComment(1)

        assertTrue(actual)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentFalse() {
        val note = Note(0, 2, "title", "afghan", 6, 2, "", "")
        NoteServise.addNote(note)

        val comment = Comment(0, 1, 3, 0, "", "", false)
        NoteServise.createComment(comment)

        val commentToDelete = Comment(1, 1, 3, 0, "", "", false)
        NoteServise.deleteComment(commentToDelete)
        NoteServise.restoreComment(2)

    }
}
