package models

import anorm._

case class User(id: Option[Long] = None, name: String, email: String, pw: String, role:Int){
}

object User {
  implicit def toParameters: ToParameterList[User] =
    Macro.toParameters[User]
}