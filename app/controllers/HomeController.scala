package controllers

import javax.inject._
import play.api.libs.json.{JsArray, JsNull, JsNumber, JsObject, JsString, JsValue, Json}
import play.api.mvc._

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

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
}
