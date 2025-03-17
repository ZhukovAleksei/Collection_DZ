import ServiceNote.serviceNote
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ServiceNoteTest {

    @Before
    fun clearColleection() {
        var service = serviceNote
        service.clear()
    }

    @Test
    fun addInCollection() {
        var service = ServiceNote
        val result = service.add(Note(1, "Title"))
        assertTrue(result)
    }

    @Test
    fun testCreateComment() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        val result = service.createComment(1, Comment(1, "First Comment"))
        assertEquals("Коментарий добавлен!", result)
    }

    @Test
    fun testNotCreateComment() {
        var service = ServiceNote
        val result = service.createComment(1, Comment(1, "First Comment"))
        assertEquals("Нет заметки с данным индексом!", result)
    }

    @Test
    fun testDelete() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        val result = service.delete(1)
        assertEquals("Заметка удалена!", result)
    }

    @Test
    fun testNotDelete() {
        var service = ServiceNote
        val result = service.delete(1)
        assertEquals("Такой заметки нет!", result)
    }

    @Test
    fun testDeleteComment() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(1, "First Comment"))
        var result = service.deleteComment(1, 1)
        assertEquals("Коментарий успешно удален!", result)
    }

    @Test
    fun testNotDeleteComment() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(1, "First Comment"))
        var result = service.deleteComment(1, 2)
        assertEquals("Коментария с таким id нет!", result)
    }

    @Test
    fun testNotNotesDeleteComment() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(1, "First Comment"))
        var result = service.deleteComment(2, 2)
        assertEquals("Заметки с таким id нет!", result)
    }

    @Test
    fun testEdit() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        var result = service.edit(1, Note(1, "TitleFirst"))
        assertEquals("Заметка измененна!", result)
    }

    @Test
    fun testNotEdit() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        var result = service.edit(3, Note(1, "TitleFirst"))
        assertEquals("Такой заметки нет!", result)
    }

    @Test
    fun testEditComment() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(1, "First Comment"))
        var result = service.editComment(1, 1, Comment(1, "Second Comment"))
        assertEquals("Коментарий успешно изменен!", result)
    }

    @Test
    fun testEditNotComment() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(2, "First Comment"))
        var result = service.editComment(1, 1, Comment(1, "Second Comment"))
        assertEquals("Коментария с таким id нет!", result)
    }

    @Test
    fun testEditNotNotes() {
        var service = ServiceNote
        service.add(Note(3, "Title"))
        service.createComment(1, Comment(1, "First Comment"))
        var result = service.editComment(1, 1, Comment(1, "Second Comment"))
        assertEquals("Заметки с таким id нет", result)
    }

    @Test
    fun testGet() {
        var service = ServiceNote
        service.add(Note(3, "Title"))
        var test = mutableListOf<Note>()
        test.add(Note(3, "Title"))
        val result = service.get()
        assertEquals(test, result)
    }

    @Test
    fun testGetById() {
        var service = ServiceNote
        service.add(Note(3, "Title"))
        var result = service.getById(3)
        assertEquals(Note(3, "Title"), result)
    }

    @Test
    fun testNotGetById() {
        var service = ServiceNote
        service.add(Note(3, "Title"))
        var result = service.getById(5)
        assertEquals(null, result)
    }

    @Test
    fun testGetComments() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(1, "First Comment"))
        service.createComment(1, Comment(2, "Second Comment"))

        var test = mutableListOf<Comment>()
        test.add(Comment(1, "First Comment"))
        test.add(Comment(2, "Second Comment"))

        val result = service.getComments(1)

        assertEquals(test, result)
    }
    @Test
    fun testRestoreComments() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(1, "First Comment"))
        service.deleteComment(1,1)

        var result = service.restoreComments(1, 1)
        assertEquals("Коментарий успешно востановлен!", result)

    }
    @Test
    fun testRestoreNotComments() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(2, "First Comment"))
        service.deleteComment(1,1)

        var result = service.restoreComments(1, 1)
        assertEquals("Коментария с таким id нет!", result)

    }
    @Test
    fun testRestoreNotNotesComments() {
        var service = ServiceNote
        service.add(Note(1, "Title"))
        service.createComment(1, Comment(1, "First Comment"))
        service.deleteComment(1,1)

        var result = service.restoreComments(2, 1)
        assertEquals("Заметки с таким id нет!", result)

    }

}