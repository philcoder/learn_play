package controllers

import java.util.NoSuchElementException

import javax.inject._
import models.User
import persistences.UserRepository
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class UserController @Inject()(val cc: ControllerComponents)(implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  def add = Action.async(parse.json) { request =>
    val placeResult = request.body.validate[User]
    placeResult.fold(
      errors => {
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> JsError.toJson(errors))))
      },
      user => {
        try{
          UserRepository.add(user)
          Future.successful(Ok(Json.obj("status" -> "ok", "message" -> ("User '" + user.id + "' added."))))
        }catch{
          case e:NoSuchElementException =>
            Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> s"User id:${user.id} not found")))
        }
      }
    )
  }

  def update = Action.async(parse.json) { request =>
    val placeResult = request.body.validate[User]
    placeResult.fold(
      errors => {
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> JsError.toJson(errors))))
      },
      user => {
        try{
          UserRepository.update(user)
          Future.successful(Ok(Json.obj("status" -> "ok", "message" -> ("User '" + user.id + "' updated."))))
        }catch{
          case e:NoSuchElementException =>
            Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> s"User id:${user.id} not found")))
        }
      }
    )
  }

  def findById(id:Int) = Action.async {
    val user = UserRepository.findById(id)
    if(user.isDefined){
      val json = Json.toJson(user.get)
      Future.successful(Ok(json))
    }else{
      Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> s"User id:$id not found")))
    }
  }

  def list = Action.async {
    val json = Json.toJson(UserRepository.list)
    Future.successful(Ok(json))
  }

  def delete(id:Int) = Action.async {
    try{
      UserRepository.remove(id)
      Future.successful(Ok(s"user from id:$id was removed with successful"))
    }catch{
      case e:NoSuchElementException =>
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> s"User id:$id not found")))
    }
  }
}
