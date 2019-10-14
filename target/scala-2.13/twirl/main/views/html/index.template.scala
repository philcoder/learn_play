
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,AssetsFinder,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template takes a two arguments, a String containing a
 * message to display and an AssetsFinder to locate static assets.
 */
  def apply/*5.2*/(message: String)(implicit assetsFinder: AssetsFinder):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.1*/("""
"""),format.raw/*11.4*/("""

"""),_display_(/*13.2*/main("Welcome to Play", assetsFinder)/*13.39*/ {_display_(Seq[Any](format.raw/*13.41*/("""

    """),format.raw/*18.8*/("""
    """),_display_(/*19.6*/welcome(message, style = "scala")),format.raw/*19.39*/("""

""")))}),format.raw/*21.2*/("""
"""))
      }
    }
  }

  def render(message:String,assetsFinder:AssetsFinder): play.twirl.api.HtmlFormat.Appendable = apply(message)(assetsFinder)

  def f:((String) => (AssetsFinder) => play.twirl.api.HtmlFormat.Appendable) = (message) => (assetsFinder) => apply(message)(assetsFinder)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2019-10-14T16:57:26.124
                  SOURCE: /home/philipp/central/app/workspace_idea/learn-play/app/views/index.scala.html
                  HASH: 63ca2c658a0ffa13d6a003bee5a3be8f5107db05
                  MATRIX: 873->137|1021->192|1049->387|1078->390|1124->427|1164->429|1197->558|1229->564|1283->597|1316->600
                  LINES: 24->5|29->6|30->11|32->13|32->13|32->13|34->18|35->19|35->19|37->21
                  -- GENERATED --
              */
          