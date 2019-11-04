package controllers

import java.util.NoSuchElementException

import javax.inject._
import models.User
import org.postgresql.util.PSQLException
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
        repo.add(user).map{ _ =>
          Ok(Json.obj("status" -> "ok", "message" -> ("User '" + user.id + "' added.")))
        } recover {
          case e: PSQLException => BadRequest(Json.obj("status" -> "error", "message" -> s"${e.getMessage}"))
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
        repo.update(user).map{ _ =>
          Ok(Json.obj("status" -> "ok", "message" -> ("User '" + user.id + "' updated.")))
        } recover {
          case e: PSQLException => BadRequest(Json.obj("status" -> "error", "message" -> s"${e.getMessage}"))
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
    repo.delete(id).map{ _ =>
      Ok(Json.obj("status" -> "ok", "message" -> (s"user from id:$id was removed with successful")))
    } recover {
      case e: PSQLException => BadRequest(Json.obj("status" -> "error", "message" -> s"${e.getMessage}"))
    }
  }
}
