package models

import play.api.libs.json.{JsPath, Json, Reads, Writes}
import play.api.libs.functional.syntax._

case class User(id:Long, name:Option[String], email:String, pw:String, role:Int)

object User{

  implicit val writes = new Writes[User]{
    def writes(location: User) = Json.obj(
      "id" -> location.id,
      "name" -> location.name,
      "email" -> location.email,
      "pw" -> location.pw,
      "role" -> location.role,
    )
  }

  implicit val reads: Reads[User] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "name").readNullable[String] and
      (JsPath \ "email").read[String](Reads.email) and
      (JsPath \ "pw").read[String] and
      (JsPath \ "role").read[Int](Reads.min(0) keepAnd Reads.max(3))
    )(User.apply _)
}