package com.example.textsharing

import cats.effect.Sync
import cats.implicits._
import doobie.implicits._
import doobie.util.transactor.Transactor

class Database[F[_]: Sync](transactor: Transactor[F]) {

  def getNotes: F[List[Note]] =
    sql"SELECT id, content FROM notes"
      .query[Note]
      .to[List]
      .transact(transactor)

  def getNoteById(noteId: Long): F[Option[Note]] =
    sql"SELECT id, content FROM notes WHERE id = $noteId"
      .query[Note]
      .option
      .transact(transactor)

  def createNote(note: Note): F[Unit] =
    sql"INSERT INTO notes (id, content) VALUES (${note.id}, ${note.content})"
      .update
      .run
      .transact(transactor)
      .void

  def updateNote(noteId: Long, note: Note): F[Unit] =
    sql"UPDATE notes SET content = ${note.content} WHERE id = $noteId"
      .update
      .run
      .transact(transactor)
      .void

  def deleteNote(noteId: Long): F[Unit] =
    sql"DELETE FROM notes WHERE id = $noteId"
      .update
      .run
      .transact(transactor)
      .void
}