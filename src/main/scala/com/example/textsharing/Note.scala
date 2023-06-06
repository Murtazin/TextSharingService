package com.example.textsharing

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._
import io.circe.{Codec, Decoder, Encoder}

case class Note(id: Long, content: String)

object Note {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames

  implicit val noteDecoder: Decoder[Note] = deriveConfiguredDecoder
  implicit val noteEncoder: Encoder[Note] = deriveConfiguredEncoder
  implicit val noteCodec: Codec[Note] = Codec.from(noteDecoder, noteEncoder)
}