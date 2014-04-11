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
import play.api.libs.json._

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
  val depositForm = Form(
    tuple(
      "id" -> longNumber,
      "amountToDeposit" -> longNumber
    )
  )
  val addCustomerForm = Form(
    tuple(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "accountBalance" -> longNumber,
      "dateOfBirth" -> nonEmptyText
    )
  )
  val viewCustomerForm = Form(
    "id" -> longNumber
  )
  // -- Actions
  /**
   * Home page
   */
  def index = Action {
    Ok(html.index());
  }
  def showWithdrawForm = Action {
    Ok(html.withdraw(withdrawForm));
  }
  def showDepositForm = Action {
    Ok(html.deposit(depositForm));
  }
  def showAddCustomerForm = Action {
    Ok(html.addCustomer(addCustomerForm));
  }
  def showViewCustomerForm = Action {
    Ok(html.viewCustomer(viewCustomerForm));
  }
  /**
   * Handles the form submission.
   */
  def withdrawFunction = Action.async { implicit request =>
    val (id, amountToWithdraw) = withdrawForm.bindFromRequest.get
    WS.url("http://localhost:8080/withdraw/" + id + "/" + amountToWithdraw).get().map { response =>
      Ok(response.body)
    }
  }
  def depositFunction = Action.async { implicit request =>
    val (id, amountToDeposit) = depositForm.bindFromRequest.get
    WS.url("http://localhost:8080/deposit/" + id + "/" + amountToDeposit).get().map { response =>
      Ok(response.body)
    }
  }
 def addCustomerFunction = Action.async { implicit request =>
    val (firstName, lastName, accountBalance, dateOfBirth) = addCustomerForm.bindFromRequest.get
    val data = Json.obj(
      "firstName" -> firstName,
      "lastName" -> lastName,
      "accountBalance" -> accountBalance,
      "birthday" -> dateOfBirth
    )
    WS.url("http://localhost:8080/customer").post(data).map { response =>
      Ok(response.body)
    }
  }
 def viewCustomerFunction =  Action.async { implicit request =>
   val (id) = viewCustomerForm.bindFromRequest.get
   WS.url("http://localhost:8080/customer/" + id).get().map { response =>
     Ok(response.body)
   }
 }

}
