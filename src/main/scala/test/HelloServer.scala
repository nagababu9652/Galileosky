package test



import com.example.protos.hello._
import io.grpc.netty.NettyServerBuilder
import io.grpc.{Server, ServerBuilder}

import java.util.logging.Logger
import scala.concurrent.ExecutionContext

object App {
  val logger: Logger = Logger.getLogger(classOf[App].getName)
  val port = 50051

  def main(args: Array[String]): Unit = {
    val server = new HelloServer(ExecutionContext.global)
    server.start()
    server.blockUntilShutdown()
  }
}
class HelloServer(executionContext: ExecutionContext) { self =>
  private[this] var server: Server = null

  def start(): Unit = {
    server = NettyServerBuilder
      .forPort(App.port)
      .addService(GreeterGrpc.bindService(new HelloService, executionContext))
      .build
      .start
    App.logger.info("Starting server on port: " + App.port)
    sys.addShutdownHook {
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      self.stop()
      System.err.println("*** server shut down")
    }
  }
  def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }
}