package test


import com.example.protos.hello._

import scala.concurrent.Future

class HelloService extends GreeterGrpc.Greeter {
  //Service to implement calls

  /** Sends a greeting
   */
  override def sayHello(request: HelloRequest): Future[HelloReply] = {
    val name : String = request.name
    val reply = HelloReply(message = "Welcome, " + name)
    Future.successful(reply)
  }
}

