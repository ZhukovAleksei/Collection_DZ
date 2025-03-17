fun main() {

    var service = ServiceNote
service.add(Note(noteId = 1, "Hello World!"))
    service.add(Note(noteId = 2, "Hello World!"))
    service.add(Note(noteId = 3, "Hello World!"))
    service.createComment(1, Comment(1, "First Comment"))
    service.createComment(1, Comment(2, "Second Comment"))
    service.delete(2)
    //println(service.getComments(1))
    //println(service.get())

}

data class Note(
    val noteId: Int? = null,
    val text: String,
    val commentPrivacy: Int = 0,
    var comments: List<Comment> = mutableListOf()
)

data class Comment(
    val commentId: Int? = null,
    var message: String,
    var delete: Boolean = false
)

object ServiceNote {

    var serviceNote = mutableListOf<Note>()

    fun add(note: Note): Boolean {
        if (note != null) {
            serviceNote += note
            return true
        }else{
            return false
        }
    }

    fun createComment(noteId: Int, comment: Comment): String {
        for (notes in serviceNote) {
            if (noteId == notes.noteId) {
                notes.comments += comment
                return "Коментарий добавлен!"
            }
        }
        return "Нет заметки с данным индексом!"
    }

    fun delete(noteId: Int): String {
        for (notes in serviceNote) {
            if (noteId == notes.noteId) {
                serviceNote.remove(notes)
                return "Заметка удалена!"
            }
        }
        return "Такой заметки нет!"
    }

    fun deleteComment(noteId: Int, commentId: Int): String {
        for (notes in serviceNote) {
            if (noteId == notes.noteId) {
                for (comment in notes.comments) {
                    if (commentId == comment.commentId) {
                        comment.delete = true
                        return "Коментарий успешно удален!"
                    }
                    return "Коментария с таким id нет!"
                }
            }
        }
        return "Заметки с таким id нет!"
    }

    fun edit(noteId: Int, note: Note): String {
        for ((index, notes) in serviceNote.withIndex()) {
            if (noteId == notes.noteId) {
                serviceNote[index] = note
                return "Заметка измененна!"
            }
        }
        return "Такой заметки нет!"
    }

    fun editComment(noteId: Int, commentId: Int, newComment: Comment): String {
        for (notes in serviceNote) {
            if (notes.noteId == noteId) {
                for (comment in notes.comments) {
                    if (comment.commentId == commentId && !comment.delete) {
                        comment.message = newComment.message
                        return "Коментарий успешно изменен!"
                    }
                    return "Коментария с таким id нет!"
                }
            }
            return "Заметки с таким id нет"
        }
        return ""
    }

    fun get(): List<Note> {
        var list = mutableListOf<Note>()
        for (notes in serviceNote) {
            list.add(notes)
        }
        return list
    }

    fun getById(noteId: Int): Note? {
        for (notes in serviceNote) {
            if (notes.noteId == noteId) {
                return notes
            }
        }
        return null
    }

    fun getComments(noteId: Int): List<Comment> {
        var list = mutableListOf<Comment>()
        for (notes in serviceNote) {
            if (notes.noteId == noteId) {
                for (comment in notes.comments) {
                    if (!comment.delete) {
                        list.add(comment)
                    }
                }
            }
        }
        return list
    }

    fun restoreComments(noteId: Int, commentId: Int): String {
        for (notes in serviceNote) {
            if (notes.noteId == noteId) {
                for (comment in notes.comments) {
                    if (commentId == comment.commentId) {
                        comment.delete = false
                        return "Коментарий успешно востановлен!"
                    }
                    return "Коментария с таким id нет!"
                }
            }
            return "Заметки с таким id нет!"
        }
        return ""
    }


    fun clearComments(){
        serviceNote.clear()

    }

}