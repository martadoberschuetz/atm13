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
   * Describes the hello form.
   */
  val helloForm = Form(
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
    Ok(views.html.index(helloForm))
  }

  /**
   * Handles the form submission.
   */


  def sayHello = Action.async { implicit request =>

    val (id, amountToWithdraw) = helloForm.bindFromRequest.get


     //  val id = 2
    WS.url("http://localhost:8080/customer/" + id).get().map { response =>
      Ok(response.body)

    }
  }

  /*
  * previous version:
  * */
 /* def sayHello = Action { implicit request =>
    helloForm.bindFromRequest.fold(
    formWithErrors => BadRequest(html.index(formWithErrors)),
    {case (id, amountToWithdraw) => Ok(html.hello(id, amountToWithdraw))}
    )
  }*/
/*
  def sayHello = Action.async { implicit request =>

 // val paramId = request.map(AnyContent => "id") //this is a request!
 //  println(par)

    val parameterId = request.body.asFormUrlEncoded.productArity.toLong
  println(parameterId)

    val paramVal = request.body.asFormUrlEncoded.get("id").map(_.head)
  println(paramVal)

//val parameterId = request.body.asFormUrlEncoded.get("id") //Seq[String]
//val bla =  parameterId.headOption.get //String
  //val asLong = bla.toLong //Long
  //println(bla)
  //println(asLong)

   // val paramId = (request.body.asFormUrlEncoded.get("id")(0)).toLong
  //  println(paramId)
  // paramId map {_.toInt} getOrElse 0
val id = 2
    WS.url("http://localhost:8080/customer/" + id).get().map { response =>
      Ok(response.body)

    }
  }*/

  /*
  * def sayHello = Action.async {

    //val id = helloForm("id")
  val id = 2
    // now instead of 2 you have to replace it with the value from the textfield

    //http://localhost:9000/hello?id=4&amountToWithdraw=

   // val response =   Http("http://localhost:8080/customer/" + id).asString
 //  println(response)

    WS.url("http://localhost:8080/customer/" + id).get().map { response =>
      Ok(response.body)


      //Http.post("http://foo.com/add").params("name" -> "jon", "age" -> "29").asString

      //Http.post("http://localhost:8080/customer/id").params("id" -> "4").asString

    }
  }
  * */
/*
  def sayHello = Action { implicit request =>

    val body: AnyContent = request.body
   val textBody: Option[String] = body.asText


   // paramId map {_.toInt} getOrElse 0

    // Expecting text body
    textBody.map { text =>
      Ok("Got: " + text)
    }.getOrElse {
      BadRequest("Expecting text/plain request body")
    }

   // val id = 2


   // WS.url("http://localhost:8080/customer/" + id).get().map { response =>
    //  Ok(response.body)



    }*/



  /*def showData: Unit = {

    def values = helloForm.data
    def id = values("id")
    def amountToWithdraw = values("amountToWithdraw")

    print(id)
    print(amountToWithdraw)
       def paramId = request.body.asFormUrlEncoded.get("id")(0)
    //val paramId = request.body.get("id").map(_.head)
    def paramAmountToWithdraw = request.body.asFormUrlEncoded.get("amountToWithdraw").map(_.head)
  val idAsInt = paramId.toString().toInt
  val amountAsInt = paramAmountToWithdraw.toString().toInt
    println(paramId)


    ...
        def values = helloForm.data
    def id = values("id")
    def amountToWithdraw = values("amountToWithdraw")

    val idAsInt = id.toInt
    val amountAsInt = amountToWithdraw.toInt

   Ok(views.html.hello(idAsInt, amountAsInt))

.....
    val data = helloForm.bindFromRequest.get
    Ok(views.html.hello(data._1, data._2))

  }*/


/*
  def sayHello = Action.async{

    Action { implicit request =>
    helloForm.bindFromRequest.fold(
    formWithErrors => BadRequest(html.index(formWithErrors)),
    {case (id, amountToWithdraw) => Ok(html.hello(id, amountToWithdraw))
      WS.url("http://localhost:8080/customer/" + id).get().map { response =>
        Ok(response.body)
    }
    )
  }
    ///WS.url("http://localhost:8080/customer/" + id).get().map { response =>
   //   Ok(response.body)

  }

  }*/



  /*
  def sayHello(id: Long, amountToWithdraw: Long) = Action.async {


     WS.url(Http("http://localhost:8080/withdraw/id/amountToWithdraw").param(id.toString(), amountToWithdraw.toString()).asString).get().map{
     response => Ok(response.body)
     }

    //  Http("http://foo.com/search").param("q","monkeys").asString

   // WS.url("http://localhost:8080/customer/id").get().map { response =>
   //   Ok(response.body)
   // }



    //println(response)

  }*/
}
