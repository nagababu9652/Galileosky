package test

import v1.iot_decoder._
import scala.concurrent.Future

class iot_decoderService extends IoTDecoderServiceGrpc.IoTDecoderService {
  override def decode(request: IOTDecodeRequests): Future[IOTDecodeResponses] = {
    val name : String = request.toProtoString
    val reply = IOTDecodeResponses( )
    Future.successful(reply)
  }
}



