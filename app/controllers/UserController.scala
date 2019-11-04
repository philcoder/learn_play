package controllers

import java.util.NoSuchElementException

import javax.inject._
import models.User
import persistences.UserRepository
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(cc: ControllerComponents, repo: UserRepository)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def add = Action.async(parse.json) { request =>
    val placeResult = request.body.validate[User]
    placeResult.fold(
      errors => {
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> JsError.toJson(errors))))
      },
      user => {
        try{
          repo.add(user).map{ _ =>
            Ok(Json.obj("status" -> "ok", "message" -> ("User '" + user.id + "' added.")))
          }
        }catch{
          case e:NoSuchElementException =>
            Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> s"User id:${user.id} not found")))
        }
      }
    )
  }

  def update = Action.async(parse.json) { implicit request =>
    val placeResult = request.body.validate[User]
    placeResult.fold(
      errors => {
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> JsError.toJson(errors))))
      },
      user => {
        try{
          repo.update(user).map{ _ =>
            Ok(Json.obj("status" -> "ok", "message" -> ("User '" + user.id + "' updated.")))
          }
        }catch{
          case e:NoSuchElementException =>
            Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> s"User id:${user.id} not found")))
        }
      }
    )
  }

  def findById(id:Int) = Action.async { implicit request =>
    repo.findById(id).map{ user =>
      if(user.isDefined){
        val json = Json.toJson(user.get)
        Ok(json)
      }else{
        BadRequest(Json.obj("status" -> "error", "message" -> s"User id:$id not found"))
      }
    }
  }

  def list = Action.async { implicit request =>
    repo.listAll.map { user =>
      Ok(Json.toJson(user))
    }
  }

  def delete(id:Int) = Action.async {
    try{
      repo.delete(id).map{ _ =>
        Ok(Json.obj("status" -> "ok", "message" -> (s"user from id:$id was removed with successful")))
      }
    }catch{
      case _:NoSuchElementException =>
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> s"User id:$id not found")))
    }
  }
}
