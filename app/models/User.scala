package models

import play.api.libs.json.{JsPath, Json, Reads, Writes}
import play.api.libs.functional.syntax._

case class User(id:Option[Long], name:Option[String], email:String, pw:String, role:Int, state:Int)

object User{

  implicit val writes = new Writes[User]{
    def writes(user: User) = Json.obj(
      "id" -> user.id,
      "name" -> user.name,
      "email" -> user.email,
      "pw" -> user.pw,
      "role" -> user.role,
      "state" -> user.state
    )
  }

  implicit val reads: Reads[User] = (
      (JsPath \ "id").readNullable[Long] and
      (JsPath \ "name").readNullable[String] and
      (JsPath \ "email").read[String](Reads.email) and
      (JsPath \ "pw").read[String] and
      (JsPath \ "role").read[Int](Reads.min(0) keepAnd Reads.max(3)) and
      (JsPath \ "state").read[Int]
    )(User.apply _)
}