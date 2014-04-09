package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views._

import scalaj.http
import scalaj.http.Http
import play.api.libs.ws.WS
import play.libs.HttpExecution
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global


object Application extends Controller {

  /**
   * Describes the withdraw form.
   */
  val withdrawForm = Form(
    tuple(
      "id" -> longNumber,
      "amountToWithdraw" -> longNumber
    )
  )

  // -- Actions

  /**
   * Home page
   */
  def index = Action {
    Ok(views.html.index(withdrawForm))
  }

  /**
   * Handles the form submission.
   */
  def sayHello = Action.async { implicit request =>
    val (id, amountToWithdraw) = withdrawForm.bindFromRequest.get
    WS.url("http://localhost:8080/withdraw/" + id + "/" + amountToWithdraw).get().map { response =>
      Ok(response.body)
    }
  }
}
