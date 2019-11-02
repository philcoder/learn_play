package controllers

import javax.inject._
import models.User
import persistences.UserRepository
import play.api.libs.json.{JsArray, JsNull, JsNumber, JsObject, JsString, JsValue, Json}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
class UserController @Inject()(userService: UserRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  def hello = Action.async {
    val json: JsValue = JsObject(Seq(
      "name" -> JsString("Watership Down"),
      "location" -> JsObject(Seq("lat" -> JsNumber(51.235685), "long" -> JsNumber(-1.309197))),
      "residents" -> JsArray(IndexedSeq(
        JsObject(Seq(
          "name" -> JsString("Fiver"),
          "age" -> JsNumber(4),
          "role" -> JsNull
        )),
        JsObject(Seq(
          "name" -> JsString("Bigwig"),
          "age" -> JsNumber(6),
          "role" -> JsString("Owsla")
        ))
      ))
    ))
    Future.successful(Ok(json))
  }

  def add = Action.async(parse.json) { implicit request =>

    val name = (request.body \ "name").as[String]
    val email = (request.body \ "email").as[String]
    val pw = (request.body \ "pw").as[String]
    val role = (request.body \ "role").as[Int]

    try {
      if (name == "" || email == "" || pw == ""){
        //manual validation
      }

      //can't have overload constructor
      userService.insert(new User(Option.apply(0),name, email, pw, role))

      Future.successful(Ok(JsObject(Seq(
        "msg" -> JsString(s"Insert user $name with successful"),
      ))))
    } catch {
      case e:Exception=>
        Future.successful(
          Status(400)("Error: Unidentified error")
        )
    }
  }
}
