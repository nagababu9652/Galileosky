package test

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, from_json, udf}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

import scala.annotation.tailrec
import scala.util.control.Breaks._


object PureScalaGalileosky extends GalileoskyMapV2 {
  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)
  var pointer = 0
  var result = ""
  var match1: Any = ""
  var map: scala.collection.immutable.ListMap[String, Any] = scala.collection.immutable.ListMap[String,Any]()
  var recordsmap: scala.collection.immutable.ListMap[Int,Map[String,Any]] = scala.collection.immutable.ListMap[Int,Map[String,Any]]()
  var String_len = 0
  var Str = ""


  //**************** functions ****************************

  def ToInt(str_to_convert: String, endian: String = "no",div: Int=1): BigInt = {
    if(endian == "no"){
      BigInt(str_to_convert, 16) /div
    }else{
      BigInt(LittleEdianform(str_to_convert), 16) /div
    }
  }

  def SignedByte(str_to_convert: String): BigInt = {
    java.lang.Long.valueOf(str_to_convert, 16).toByte
  }

  def SignedShort(str_to_convert: String): BigInt = {
    java.lang.Long.valueOf(str_to_convert, 16).toShort
  }

  def SignedInt(str_to_convert: String): BigInt = {
    java.lang.Long.valueOf(str_to_convert, 16).toInt
  }

  def toASCII(str_to_convert: String):String = {
    val hex = str_to_convert.replaceAll("\\s", "")
    val sb = new scala.collection.mutable.StringBuilder
    for (i <- 0 until hex.length by 2) {
      val str = hex.substring(i, i + 2)
      sb.append(Integer.parseInt(str, 16).toChar)
    }
    sb.toString().replaceAll("\0","") //replacing null literal in string
  }

  def LittleEdianform(str_to_convert:String):String ={
    val str = str_to_convert.replaceAll("\\s", "")
    var i=0
    val arr = new Array[String](str.length / 2 )
    while (str.length > i ){
      arr(i/2)=str.substring(i,i+2)
      i=i+2
    }
    arr.reverse.mkString
  }

  def Thermo_Id_Temp(str_to_convert: String):String={
    val a = str_to_convert.split(" ").mkString
    "Identifier: "+BigInt(a.substring(0,2),16).toString +" Temperature: "+ BigInt(a.substring(2,4),16).toString
  }

  def Sensor(str_to_convert: String):String={
    val a = str_to_convert.split(" ").mkString
    "Identifier: "+BigInt(a.substring(0,2),16).toString +" Temperature: "+ BigInt(a.substring(2,4),16).toString +" Humidity: "+ BigInt(a.substring(4,6),16).toString
  }

  val ptrInc: Int => Int = (n: Int)=> {
    pointer + (n*2)
  }


  //**************** function for matching the cases ****************************

  override def tagfunction(len: Int, TagName: String, ConvertionFun: conversiontype.Value,  LittleEndian: String = "no", div: Int = 1): Any = ConvertionFun match {
    case conversiontype.ToInt =>
      val tempstr = Str.substring(pointer, ptrInc(len)).trim
      pointer = ptrInc(len)
      map += (TagName -> ToInt(tempstr, LittleEndian,div))
      ToInt(tempstr, LittleEndian,div)
    case conversiontype.ToASCII =>
      val tempstr = Str.substring(pointer, ptrInc(len)).trim
      pointer = ptrInc(len)
      map += (TagName -> toASCII(tempstr))
      toASCII(tempstr)
    case conversiontype.ToSignedByte =>
      val tempstr = Str.substring(pointer, ptrInc(len)).trim
      pointer = ptrInc(len)
      map += (TagName -> SignedByte(tempstr))
      SignedByte(tempstr)
    case conversiontype.ToSignedShort=>
      val tempstr = Str.substring(pointer, ptrInc(len)).trim
      pointer = ptrInc(len)
      map += (TagName -> SignedShort(tempstr))
      SignedShort(tempstr)
    case conversiontype.ToSignedInt =>
      val tempstr = Str.substring(pointer, ptrInc(len)).trim
      pointer = ptrInc(len)
      map += (TagName -> SignedInt(tempstr))
      SignedInt(tempstr)
    case conversiontype.Thermo_Id_Temp =>
      val tempstr = Str.substring(pointer, ptrInc(len)).trim
      pointer = ptrInc(len)
      map += (TagName -> Thermo_Id_Temp(tempstr))
      Thermo_Id_Temp(tempstr)
    case conversiontype.DS1923Sensor =>
      val tempstr = Str.substring(pointer, ptrInc(len)).trim
      pointer = ptrInc(len)
      map += (TagName -> Sensor(tempstr))
      Sensor(tempstr)
    case conversiontype.RS485FuelSensor =>
      val tempstr = Str.substring(pointer, ptrInc(len) ).trim
      pointer= ptrInc(len)
      var res = LittleEdianform(tempstr)
      res = "Temperature " + SignedByte(res.substring(0,2)) + ", Fuel level" + ToInt(res.substring(2,6),"no")
      map += (TagName -> res)
      res
    case conversiontype.ExtTempSensor =>
      val tempstr = Str.substring(pointer, ptrInc(len) ).trim
      pointer= ptrInc(len)
      val a = tempstr.split(" ").mkString
      val res = "Sensor Id: "+BigInt(a.substring(0,2),16).toString +" Temperature: "+ (SignedShort(a.substring(4,8))/256).toString
      map += (TagName -> res)
      res
    case conversiontype.Coordinates =>
      var tempstr = Str.substring(pointer, ptrInc(len) ).trim
      pointer= ptrInc(len)
      tempstr = LittleEdianform(tempstr)
      var res: String = ""
      if (tempstr(16).toString == "0"|| tempstr(16).toString == "2"){ res = "Coordinates are correct, " }
      res += "Number of Satellites "+Integer.parseInt(tempstr.substring(17,18),16) + ", "
      res +=  "Latitude " + SignedInt(tempstr.substring(0,8)).toFloat /1000000 + ", "
      res +=  "Longitude " + SignedInt(tempstr.substring(8,16)).toFloat /1000000
      map += (TagName -> res)
      res
    case conversiontype.SpeedDirection =>
      var tempstr = Str.substring(pointer, ptrInc(len) ).trim
      pointer= ptrInc(len)
      tempstr = LittleEdianform(tempstr)
      var res: String = ""
      res = "Direction " + BigInt(tempstr.substring(0,4),16).toFloat /10+ " ,"
      res += "Speed " + BigInt(tempstr.substring(4,8),16).toFloat /10
      map += (TagName -> res)
      res
    case conversiontype.EcoDrive =>
      var tempstr = Str.substring(pointer, ptrInc(len) ).trim
      tempstr = LittleEdianform(tempstr)
      pointer= ptrInc(len)
      var res="Acceleration " + BigInt(tempstr.substring(0,2),16)
      res += "   Braking " + BigInt(tempstr.substring(0,2),16)
      res += "   Cornering acceleration " + BigInt(tempstr.substring(0,2),16)
      res += "   Strike on bumps " + BigInt(tempstr.substring(0,2),16)
      map += (TagName -> res)
      res
    case conversiontype.Acceleration =>
      var tempstr = Str.substring(pointer, ptrInc(len) ).trim
      pointer= ptrInc(len)
      tempstr = LittleEdianform(tempstr)
      var x = Integer.parseInt(tempstr,16).toBinaryString
      x = "0"*(32-x.length)+x
      x = "x" + Integer.parseInt(x.substring(22,32),2)
      x = "y" + Integer.parseInt(x.substring(12,22),2)
      x = "z" + Integer.parseInt(x.substring(2,12),2)
      map += (TagName -> x)
      x
    case conversiontype.ExpandedStatus =>
      val tempstr = Str.substring(pointer, ptrInc(len) ).trim.replaceAll("\\s", "")
      pointer= ptrInc(len)
      val binaryrep= BigInt(tempstr,16).toString(2)
      var res = ""
      if(binaryrep(0).toString=="0")
        res += "Primary server not connected, "
      else
        res += "Primary server connected, "
      if(binaryrep(1).toString=="0")
        res += "GPRS session off, "
      else
        res += "PGPRS session on, "
      if(binaryrep(2).toString=="0")
        res += "no jamming detected, "
      else
        res += "GSM jamming detected, "
      if(binaryrep(3).toString=="0")
        res += "Additional server not connected, "
      else
        res += "Additional server connected, "
      if(binaryrep(4).toString=="0")
        res += "GPS/GLONASS jamming not detected, "
      else
        res += "GPS/GLONASS jamming detected, "
      if(binaryrep(5).toString=="0")
        res += "USB of device  not connected, "
      else
        res += "USB of device  connected, "
      if(binaryrep(6).toString=="0")
        res += "SD card absent"
      else
        res += "SD card present"
      map += (TagName -> res)
      res
    case conversiontype.UserArray=>
      val arraylen = Integer.parseInt(Str.substring(pointer, ptrInc(len)),16)
      pointer = ptrInc(len)
      val tempstr = Str.substring(pointer, ptrInc(arraylen))
      pointer = ptrInc(arraylen)
      map += (TagName -> tempstr)
      tempstr
    case conversiontype.NoChange=>
      val tempstr = Str.substring(pointer, ptrInc(len)).trim.replaceAll("\\s", "")
      pointer = ptrInc(len)
      map += (TagName -> tempstr)
      tempstr
    case _ => map += (TagName -> ConvertionFun)
  }


  @tailrec
  def parseExtTag(recordDataStart: Int, ext_len: String): Int = {
    if (!(pointer - recordDataStart < BigInt(LittleEdianform(ext_len), 16) * 2)) return 1
    val exttag = Str.substring(pointer, ptrInc(2)).trim.replaceAll(" ", "")
    pointer = ptrInc(2) //INCREMENT TWO BYTES
    try {
      MapOfTAgs(exttag)()
    }
    catch {
      case e: Exception => println(e)
    }
    parseExtTag(recordDataStart, ext_len)
  }

  @tailrec
  def parseAllTags(tagdataStartPnt: Int, taglen: BigInt): Any = {
    if (!((pointer - tagdataStartPnt) < taglen * 2)) return 1
    val tag = Str.substring(pointer, pointer + 2).trim
    pointer = pointer + 2

    //Extended tag
    if (tag == "FE") {
      val ext_len = Str.substring(pointer, ptrInc(2)).trim
      val recordDataStart: Int = pointer
      map += ("Extended tag length" -> BigInt(LittleEdianform(ext_len), 16))
      pointer = ptrInc(2)
      parseExtTag(recordDataStart, ext_len) // call extended tag parser
      return 1
    }
      //General tags
    else {
      try {
        MapOfTAgs(tag)()
      }
      catch {
        case e: Exception => println(e)
      }
    }
    parseAllTags(tagdataStartPnt, taglen)
  }

  @tailrec
  def ParseRecords(tagdataStartPnt: Int, taglen: BigInt, record: Int): Int ={
    var rc =record
    if (!((pointer - tagdataStartPnt ) < taglen * 2)) return rc
    parseAllTags(tagdataStartPnt, taglen)
    recordsmap += ( rc-> map)
    rc = rc +1
    map = map.empty
    ParseRecords(tagdataStartPnt, taglen, rc)
  }

  //**************** UDF for converting Gelileosky code to Readable format ****************************
  val galileoskyCase: String => String = (Stri: String) => {
    Str = Stri.replaceAll("\\s", "").toUpperCase
    println("\n\n************************************************START***************************************************\n\n")
    String_len = Str.length
    var tagdataStartPnt: Int = 0
    var tag = Str.substring(pointer, pointer + 2)
    var record = 0
    // for multiple packets
    while(tag == "01"){
      pointer = ptrInc(1)
      val taglen = BigInt(LittleEdianform(Str.substring(pointer, ptrInc(2))),16)
      map += ("Tag length" -> taglen)
      pointer = ptrInc(2)
      tagdataStartPnt= pointer
      record = ParseRecords(tagdataStartPnt, taglen, record) // parsing records in packet
      pointer = ptrInc(2)
      tag = Str.substring(pointer, pointer + 2)
    }
    //printing output
    recordsmap.foreach {  case (key, values) => println("record "+key + " -> " + values +"\n")
      values.foreach {  case (key, values) =>
        println(key + " -> " + values )
      }
      println("____________________________________________________________________________________")
      println()
    }
    println("____________________________________________________________________________________")
    recordsmap.toString()
  }

  //**************** Main function ****************************
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[3]")
      .appName("Kafka to kafka")
      .getOrCreate()

    val df = spark.read
      .format("kafka")
      .option("kafka.bootstrap.servers","localhost:9092")
      .option("startingOffsets","earliest")
      .option("subscribe","galileosky1")
      .load()

    val schema = StructType(List(
      StructField("galileosky",StringType)
    ))


    val jsondf = df.select(from_json(col("value").cast(StringType),schema).alias("value"))
    val explodeDf = jsondf.selectExpr("value.galileosky")
    val convertUDF = udf(galileoskyCase)
    explodeDf.select(col("galileosky"), convertUDF(col("galileosky")) as "result").show()

    println("\n\n***********************************************END****************************************************\n\n")

  }
}
