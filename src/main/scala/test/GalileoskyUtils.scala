package test

object conversiontype extends Enumeration{
  type conversiontype = Value
  val ToInt: conversiontype.Value = Value("ToInt")
  val ToASCII : conversiontype.Value= Value("ToASCII")
  val ToSignedByte: conversiontype.Value = Value("ToSignedByte")
  val ToSignedShort: conversiontype.Value = Value("ToSignedShort")
  val ToSignedInt: conversiontype.Value = Value("ToSignedInt")
  val Thermo_Id_Temp: conversiontype.Value = Value("Thermo_Id_Temp")
  val DS1923Sensor: conversiontype.Value = Value("DS1923Sensor")
  val RS485FuelSensor: conversiontype.Value = Value("RS485FuelSensor")
  val ExtTempSensor: conversiontype.Value = Value("ExtTempSensor")
  val ExpandedStatus: conversiontype.Value = Value("ExpandedStatus")
  val EcoDrive: conversiontype.Value = Value("EcoDrive")
  val Acceleration: conversiontype.Value = Value("Acceleration")
  val Coordinates: conversiontype.Value = Value("Coordinates")
  val SpeedDirection: conversiontype.Value = Value("SpeedDirection")
  val UserArray: conversiontype.Value = Value("UserArray")
  val NoChange: conversiontype.Value = Value("NoChange")
}

trait GalileoskyMapV2 {
  def tagfunction(len: Int, TagName: String, ConvertionFun: conversiontype.Value, LittleEndian: String = "no", div: Int = 1): Any

  val MapOfTAgs: Map[String, () => Any] = Map(
    "01" -> (()=>tagfunction(2, "Tag Length", conversiontype.ToInt,"yes")),
    "02" -> (()=>tagfunction(1, "Firmware version", conversiontype.ToInt)),
    "03" -> (()=>tagfunction(15, "IMEI", conversiontype.ToASCII)),
    "04" -> (()=>tagfunction(2, "Device ID", conversiontype.ToInt)),
    "10" -> (()=>tagfunction(2, "No of Archive records", conversiontype.ToInt)),
    "20" -> (()=>tagfunction(4, "Date and time", conversiontype.ToInt)),
    "30" -> (()=>tagfunction(9, "Coordinates", conversiontype.Coordinates)),
    "33" -> (()=>tagfunction(4, "Speed and direction", conversiontype.SpeedDirection)),
    "34" -> (()=>tagfunction(2, "Height", conversiontype.ToSignedShort)),
    "35" -> (()=>tagfunction(1, "HDOP", conversiontype.ToInt)),
    "40" -> (()=>tagfunction(2, "Status of device", conversiontype.ToInt,"yes")),
    "41" -> (()=>tagfunction(2, "Supply Voltage", conversiontype.ToInt)),
    "42" -> (()=>tagfunction(2, "Battery Voltage", conversiontype.ToInt)),
    "43" -> (()=>tagfunction(1, "Temperature of tracking device", conversiontype.ToSignedByte)),
    "44" -> (()=>tagfunction(4, "Acceleration", conversiontype.Acceleration)),
    "45" -> (()=>tagfunction(2, "Status of Outputs", conversiontype.NoChange)),
    "46" -> (()=>tagfunction(2, "Status of Inputs", conversiontype.NoChange)),
    "50" -> (()=>tagfunction(2, "Input volatage 0", conversiontype.ToInt)),
    "51" -> (()=>tagfunction(2, "Input volatage 1", conversiontype.ToInt)),
    "52" -> (()=>tagfunction(2, "Input volatage 2", conversiontype.ToInt)),
    "53" -> (()=>tagfunction(2, "Input volatage 3", conversiontype.ToInt)),
    "58" -> (()=>tagfunction(2, "RS232 0", conversiontype.ToInt)),
    "59" -> (()=>tagfunction(2, "RS232 1", conversiontype.ToInt)),
    "70" -> (()=>tagfunction(2, "Thermometer Id 0", conversiontype.Thermo_Id_Temp)),
    "71" -> (()=>tagfunction(2, "Thermometer Id 1", conversiontype.Thermo_Id_Temp)),
    "72" -> (()=>tagfunction(2, "Thermometer Id 2", conversiontype.Thermo_Id_Temp)),
    "73" -> (()=>tagfunction(2, "Thermometer Id 3", conversiontype.Thermo_Id_Temp)),
    "74" -> (()=>tagfunction(2, "Thermometer Id 4", conversiontype.Thermo_Id_Temp)),
    "75" -> (()=>tagfunction(2, "Thermometer Id 5", conversiontype.Thermo_Id_Temp)),
    "76" -> (()=>tagfunction(2, "Thermometer Id 6", conversiontype.Thermo_Id_Temp)),
    "77" -> (()=>tagfunction(2, "Thermometer Id 7", conversiontype.Thermo_Id_Temp)),
    "90" -> (()=>tagfunction(4, "iButton id", conversiontype.ToInt)),
    "C0" -> (()=>tagfunction(4, "CAN_A0", conversiontype.ToInt,"no",2)),
    "C1" -> (()=>tagfunction(4, "CAN_A1", conversiontype.NoChange)),
    "C2" -> (()=>tagfunction(4, "CAN_B0", conversiontype.ToInt,"no",5)),
    "C3" -> (()=>tagfunction(4, "CAN_B1", conversiontype.ToInt,"no",5)),
    "C4" -> (()=>tagfunction(1, "CAN8BITR0", conversiontype.ToInt)),
    "C5" -> (()=>tagfunction(1, "CAN8BITR1", conversiontype.ToInt)),
    "C6" -> (()=>tagfunction(1, "CAN8BITR2", conversiontype.ToInt)),
    "C7" -> (()=>tagfunction(1, "CAN8BITR3", conversiontype.ToInt)),
    "C8" -> (()=>tagfunction(1, "CAN8BITR4", conversiontype.ToInt)),
    "C9" -> (()=>tagfunction(1, "CAN8BITR5", conversiontype.ToInt)),
    "CA" -> (()=>tagfunction(1, "CAN8BITR6", conversiontype.ToInt)),
    "CB" -> (()=>tagfunction(1, "CAN8BITR7", conversiontype.ToInt)),
    "CC" -> (()=>tagfunction(1, "CAN8BITR8", conversiontype.ToInt)),
    "CD" -> (()=>tagfunction(1, "CAN8BITR9", conversiontype.ToInt)),
    "CE" -> (()=>tagfunction(1, "CAN8BITR10", conversiontype.ToInt)),
    "CF" -> (()=>tagfunction(1, "CAN8BITR11", conversiontype.ToInt)),
    "D0" -> (()=>tagfunction(1, "CAN8BITR12", conversiontype.ToInt)),
    "D1" -> (()=>tagfunction(1, "CAN8BITR13", conversiontype.ToInt)),
    "D2" -> (()=>tagfunction(1, "CAN8BITR14", conversiontype.ToInt)),
    "D3" -> (()=>tagfunction(4, "second iButton", conversiontype.ToInt)),
    "D4" -> (()=>tagfunction(4, "Total mileage", conversiontype.ToInt)),
    "D5" -> (()=>tagfunction(1, "State of iButton", conversiontype.ToInt)),
    "D6" -> (()=>tagfunction(2, "CAN16BITR2", conversiontype.ToInt,"no",2)),
    "D7" -> (()=>tagfunction(2, "CAN16BITR2", conversiontype.ToInt,"no",2)),
    "D8" -> (()=>tagfunction(2, "CAN16BITR2", conversiontype.ToInt,"no",2)),
    "D9" -> (()=>tagfunction(2, "CAN16BITR2", conversiontype.ToInt,"no",2)),
    "DA" -> (()=>tagfunction(2, "CAN16BITR2", conversiontype.ToInt,"no",2)),
    "DB" -> (()=>tagfunction(4, "CAN32BITR0", conversiontype.ToInt,"no",100)),
    "DC" -> (()=>tagfunction(4, "CAN32BITR1", conversiontype.ToInt,"no",10)),
    "DD" -> (()=>tagfunction(4, "CAN32BITR2", conversiontype.ToInt,"no",10)),
    "DE" -> (()=>tagfunction(4, "CAN32BITR3", conversiontype.ToInt,"no",10)),
    "DF" -> (()=>tagfunction(4, "CAN32BITR4", conversiontype.ToInt,"no",10)),
    "54" -> (()=>tagfunction(2, "Input 4", conversiontype.ToInt)),
    "55" -> (()=>tagfunction(2, "Input 5", conversiontype.ToInt)),
    "56" -> (()=>tagfunction(2, "Input 6", conversiontype.ToInt)),
    "57" -> (()=>tagfunction(2, "Input 7", conversiontype.ToInt)),
    "80" -> (()=>tagfunction(3, "DS1923 sensor 0", conversiontype.DS1923Sensor)),
    "81" -> (()=>tagfunction(3, "DS1923 sensor 1", conversiontype.DS1923Sensor)),
    "82" -> (()=>tagfunction(3, "DS1923 sensor 2", conversiontype.DS1923Sensor)),
    "83" -> (()=>tagfunction(3, "DS1923 sensor 3", conversiontype.DS1923Sensor)),
    "84" -> (()=>tagfunction(3, "DS1923 sensor 4", conversiontype.DS1923Sensor)),
    "85" -> (()=>tagfunction(3, "DS1923 sensor 5", conversiontype.DS1923Sensor)),
    "86" -> (()=>tagfunction(3, "DS1923 sensor 6", conversiontype.DS1923Sensor)),
    "87" -> (()=>tagfunction(3, "DS1923 sensor 7", conversiontype.DS1923Sensor)),
    "60" -> (()=>tagfunction(2, "RS485 [0]", conversiontype.ToInt)),
    "61" -> (()=>tagfunction(2, "RS485 [1]", conversiontype.ToInt)),
    "62" -> (()=>tagfunction(2, "RS485 [2]", conversiontype.ToInt)),
    "63" -> (()=>tagfunction(3, "RS485[3]", conversiontype.RS485FuelSensor)),
    "64" -> (()=>tagfunction(3, "RS485[4]", conversiontype.RS485FuelSensor)),
    "65" -> (()=>tagfunction(3, "RS485[5]", conversiontype.RS485FuelSensor)),
    "66" -> (()=>tagfunction(3, "RS485[6]", conversiontype.RS485FuelSensor)),
    "67" -> (()=>tagfunction(3, "RS485[7]", conversiontype.RS485FuelSensor)),
    "68" -> (()=>tagfunction(3, "RS485[8]", conversiontype.RS485FuelSensor)),
    "69" -> (()=>tagfunction(3, "RS485[9]", conversiontype.RS485FuelSensor)),
    "6A" -> (()=>tagfunction(3, "RS485[10]", conversiontype.RS485FuelSensor)),
    "6B" -> (()=>tagfunction(3, "RS485[11]", conversiontype.RS485FuelSensor)),
    "6C" -> (()=>tagfunction(3, "RS485[12]", conversiontype.RS485FuelSensor)),
    "6D" -> (()=>tagfunction(3, "RS485[13]", conversiontype.RS485FuelSensor)),
    "6E" -> (()=>tagfunction(3, "RS485[14]", conversiontype.RS485FuelSensor)),
    "6F" -> (()=>tagfunction(3, "RS485[15]", conversiontype.RS485FuelSensor)),
    "88" -> (()=>tagfunction(1, "RS232[0]", conversiontype.ToSignedByte)),
    "89" -> (()=>tagfunction(1, "RS232[1]", conversiontype.ToSignedByte)),
    "8A" -> (()=>tagfunction(1, "RS485 0", conversiontype.ToSignedByte)),
    "8B" -> (()=>tagfunction(1, "RS485 1", conversiontype.ToSignedByte)),
    "8C" -> (()=>tagfunction(1, "RS485 2", conversiontype.ToSignedByte)),
    "A0" -> (()=>tagfunction(1, "CAN8BITR15", conversiontype.NoChange)),
    "A1" -> (()=>tagfunction(1, "CAN8BITR16", conversiontype.NoChange)),
    "A2" -> (()=>tagfunction(1, "CAN8BITR17", conversiontype.NoChange)),
    "A3" -> (()=>tagfunction(1, "CAN8BITR18", conversiontype.NoChange)),
    "A4" -> (()=>tagfunction(1, "CAN8BITR19", conversiontype.NoChange)),
    "A5" -> (()=>tagfunction(1, "CAN8BITR20", conversiontype.NoChange)),
    "A6" -> (()=>tagfunction(1, "CAN8BITR21", conversiontype.NoChange)),
    "A7" -> (()=>tagfunction(1, "CAN8BITR22", conversiontype.NoChange)),
    "A8" -> (()=>tagfunction(1, "CAN8BITR23", conversiontype.NoChange)),
    "A9" -> (()=>tagfunction(1, "CAN8BITR24", conversiontype.NoChange)),
    "AA" -> (()=>tagfunction(1, "CAN8BITR25", conversiontype.NoChange)),
    "AB" -> (()=>tagfunction(1, "CAN8BITR26", conversiontype.NoChange)),
    "AC" -> (()=>tagfunction(1, "CAN8BITR27", conversiontype.NoChange)),
    "AD" -> (()=>tagfunction(1, "CAN8BITR28", conversiontype.NoChange)),
    "AE" -> (()=>tagfunction(1, "CAN8BITR29", conversiontype.NoChange)),
    "AF" -> (()=>tagfunction(1, "CAN8BITR30", conversiontype.NoChange)),
    "B0" -> (()=>tagfunction(2, "CAN16BITR5", conversiontype.NoChange)),
    "B1" -> (()=>tagfunction(2, "CAN16BITR6", conversiontype.NoChange)),
    "B2" -> (()=>tagfunction(2, "CAN16BITR7", conversiontype.NoChange)),
    "B3" -> (()=>tagfunction(2, "CAN16BITR8", conversiontype.NoChange)),
    "B4" -> (()=>tagfunction(2, "CAN16BITR9", conversiontype.NoChange)),
    "B5" -> (()=>tagfunction(2, "CAN16BITR10", conversiontype.NoChange)),
    "B6" -> (()=>tagfunction(2, "CAN16BITR11", conversiontype.NoChange)),
    "B7" -> (()=>tagfunction(2, "CAN16BITR12", conversiontype.NoChange)),
    "B8" -> (()=>tagfunction(2, "CAN16BITR13", conversiontype.NoChange)),
    "B9" -> (()=>tagfunction(2, "CAN16BITR14", conversiontype.NoChange)),
    "F0" -> (()=>tagfunction(4, "CAN32BITR5", conversiontype.NoChange)),
    "F1" -> (()=>tagfunction(4, "CAN32BITR6", conversiontype.NoChange)),
    "F2" -> (()=>tagfunction(4, "CAN32BITR7", conversiontype.NoChange)),
    "F3" -> (()=>tagfunction(4, "CAN32BITR8", conversiontype.NoChange)),
    "F4" -> (()=>tagfunction(4, "CAN32BITR9", conversiontype.NoChange)),
    "F5" -> (()=>tagfunction(4, "CAN32BITR10", conversiontype.NoChange)),
    "F6" -> (()=>tagfunction(4, "CAN32BITR11", conversiontype.NoChange)),
    "F7" -> (()=>tagfunction(4, "CAN32BITR12", conversiontype.NoChange)),
    "F8" -> (()=>tagfunction(4, "CAN32BITR13", conversiontype.NoChange)),
    "F9" -> (()=>tagfunction(4, "CAN32BITR14", conversiontype.NoChange)),
    "5A" -> (()=>tagfunction(4, "REP-500 electricity meter", conversiontype.ToInt)),
    "5B" -> (()=>tagfunction(4, "Refrigeration Unit", conversiontype.NoChange)), //len??
    "47" -> (()=>tagfunction(4, "EcoDrive", conversiontype.EcoDrive)),
    "5C" -> (()=>tagfunction(68, "PressurePro monitoring system", conversiontype.NoChange)),
    "5D" -> (()=>tagfunction(3, "DBG-S11Ddosimeter data", conversiontype.NoChange)),
    "E2" -> (()=>tagfunction(4, "User data 0", conversiontype.ToInt)),
    "E3" -> (()=>tagfunction(4, "User data 1", conversiontype.ToInt)),
    "E4" -> (()=>tagfunction(4, "User data 2", conversiontype.ToInt)),
    "E5" -> (()=>tagfunction(4, "User data 3", conversiontype.ToInt)),
    "E6" -> (()=>tagfunction(4, "User data 4", conversiontype.ToInt)),
    "E7" -> (()=>tagfunction(4, "User data 5", conversiontype.ToInt)),
    "E8" -> (()=>tagfunction(4, "User data 6", conversiontype.ToInt)),
    "E9" -> (()=>tagfunction(4, "User data 7", conversiontype.ToInt)),
    "EA" -> (()=>tagfunction(1, "User Array", conversiontype.UserArray)),
    "48" -> (()=>tagfunction(2, "Expanded Status of device", conversiontype.ExpandedStatus)),
    "FE" -> (()=>tagfunction(0, "Expanded Tag", conversiontype.NoChange)),

    "0100" -> (()=>tagfunction(4, "Tag Modbus 0" ,conversiontype.ToInt,"no",100)),
    "0200" -> (()=>tagfunction(4, "Tag Modbus 1" ,conversiontype.ToInt,"no",100)),
    "0300" -> (()=>tagfunction(4, "Tag Modbus 2" ,conversiontype.ToInt,"no",100)),
    "0400" -> (()=>tagfunction(4, "Tag Modbus 3" ,conversiontype.ToInt,"no",100)),
    "0500" -> (()=>tagfunction(4, "Tag Modbus 4" ,conversiontype.ToInt,"no",100)),
    "0600" -> (()=>tagfunction(4, "Tag Modbus 5" ,conversiontype.ToInt,"no",100)),
    "0700" -> (()=>tagfunction(4, "Tag Modbus 6" ,conversiontype.ToInt,"no",100)),
    "0800" -> (()=>tagfunction(4, "Tag Modbus 7" ,conversiontype.ToInt,"no",100)),
    "0900" -> (()=>tagfunction(4, "Tag Modbus 8" ,conversiontype.ToInt,"no",100)),
    "0A00" -> (()=>tagfunction(4, "Tag Modbus 9" ,conversiontype.ToInt,"no",100)),
    "0B00" -> (()=>tagfunction(4, "Tag Modbus 10" ,conversiontype.ToInt,"no",100)),
    "0C00" -> (()=>tagfunction(4, "Tag Modbus 11" ,conversiontype.ToInt,"no",100)),
    "0D00" -> (()=>tagfunction(4, "Tag Modbus 12" ,conversiontype.ToInt,"no",100)),
    "0E00" -> (()=>tagfunction(4, "Tag Modbus 13" ,conversiontype.ToInt,"no",100)),
    "0F00" -> (()=>tagfunction(4, "Tag Modbus 14" ,conversiontype.ToInt,"no",100)),
    "1000" -> (()=>tagfunction(4, "Tag Modbus 15" ,conversiontype.ToInt,"no",100)),
    "1100" -> (()=>tagfunction(4, "Tag Modbus 16" ,conversiontype.ToInt,"no",100)),
    "1200" -> (()=>tagfunction(4, "Tag Modbus 17" ,conversiontype.ToInt,"no",100)),
    "1300" -> (()=>tagfunction(4, "Tag Modbus 18" ,conversiontype.ToInt,"no",100)),
    "1400" -> (()=>tagfunction(4, "Tag Modbus 19" ,conversiontype.ToInt,"no",100)),
    "1500" -> (()=>tagfunction(4, "Tag Modbus 20" ,conversiontype.ToInt,"no",100)),
    "1600" -> (()=>tagfunction(4, "Tag Modbus 21" ,conversiontype.ToInt,"no",100)),
    "1700" -> (()=>tagfunction(4, "Tag Modbus 22" ,conversiontype.ToInt,"no",100)),
    "1800" -> (()=>tagfunction(4, "Tag Modbus 23" ,conversiontype.ToInt,"no",100)),
    "1900" -> (()=>tagfunction(4, "Tag Modbus 24" ,conversiontype.ToInt,"no",100)),
    "1A00" -> (()=>tagfunction(4, "Tag Modbus 25" ,conversiontype.ToInt,"no",100)),
    "1B00" -> (()=>tagfunction(4, "Tag Modbus 26" ,conversiontype.ToInt,"no",100)),
    "1C00" -> (()=>tagfunction(4, "Tag Modbus 27" ,conversiontype.ToInt,"no",100)),
    "1D00" -> (()=>tagfunction(4, "Tag Modbus 28" ,conversiontype.ToInt,"no",100)),
    "1E00" -> (()=>tagfunction(4, "Tag Modbus 29" ,conversiontype.ToInt,"no",100)),
    "1F00" -> (()=>tagfunction(4, "Tag Modbus 30" ,conversiontype.ToInt,"no",100)),
    "2000" -> (()=>tagfunction(4, "Tag Modbus 31" ,conversiontype.ToInt,"no",100)),

    "2100" -> (()=>tagfunction(4, "Bluetooth Tag 0",conversiontype.ToInt,"no",100)),
    "2200" -> (()=>tagfunction(4, "Bluetooth Tag 1",conversiontype.ToInt,"no",100)),
    "2300" -> (()=>tagfunction(4, "Bluetooth Tag 2",conversiontype.ToInt,"no",100)),
    "2400" -> (()=>tagfunction(4, "Bluetooth Tag 3",conversiontype.ToInt,"no",100)),
    "2500" -> (()=>tagfunction(4, "Bluetooth Tag 4",conversiontype.ToInt,"no",100)),
    "2600" -> (()=>tagfunction(4, "Bluetooth Tag 5",conversiontype.ToInt,"no",100)),
    "2700" -> (()=>tagfunction(4, "Bluetooth Tag 6",conversiontype.ToInt,"no",100)),
    "2800" -> (()=>tagfunction(4, "Bluetooth Tag 7",conversiontype.ToInt,"no",100)),
    "2900" -> (()=>tagfunction(4, "Bluetooth Tag 8",conversiontype.ToInt,"no",100)),
    "2A00" -> (()=>tagfunction(4, "Bluetooth Tag 9",conversiontype.ToInt,"no",100)),
    "2B00" -> (()=>tagfunction(4, "Bluetooth Tag 10",conversiontype.ToInt,"no",100)),
    "2C00" -> (()=>tagfunction(4, "Bluetooth Tag 11",conversiontype.ToInt,"no",100)),
    "2D00" -> (()=>tagfunction(4, "Bluetooth Tag 12",conversiontype.ToInt,"no",100)),
    "2E00" -> (()=>tagfunction(4, "Bluetooth Tag 13",conversiontype.ToInt,"no",100)),
    "2F00" -> (()=>tagfunction(4, "Bluetooth Tag 14",conversiontype.ToInt,"no",100)),
    "3000" -> (()=>tagfunction(4, "Bluetooth Tag 15",conversiontype.ToInt,"no",100)),
    "3100" -> (()=>tagfunction(4, "Bluetooth Tag 16",conversiontype.ToInt,"no",100)),
    "3200" -> (()=>tagfunction(4, "Bluetooth Tag 17",conversiontype.ToInt,"no",100)),
    "3300" -> (()=>tagfunction(4, "Bluetooth Tag 18",conversiontype.ToInt,"no",100)),
    "3400" -> (()=>tagfunction(4, "Bluetooth Tag 19",conversiontype.ToInt,"no",100)),
    "3500" -> (()=>tagfunction(4, "Bluetooth Tag 20",conversiontype.ToInt,"no",100)),
    "3600" -> (()=>tagfunction(4, "Bluetooth Tag 21",conversiontype.ToInt,"no",100)),
    "3700" -> (()=>tagfunction(4, "Bluetooth Tag 22",conversiontype.ToInt,"no",100)),
    "3800" -> (()=>tagfunction(4, "Bluetooth Tag 23",conversiontype.ToInt,"no",100)),
    "3900" -> (()=>tagfunction(4, "Bluetooth Tag 24",conversiontype.ToInt,"no",100)),
    "3A00" -> (()=>tagfunction(4, "Bluetooth Tag 25",conversiontype.ToInt,"no",100)),
    "3B00" -> (()=>tagfunction(4, "Bluetooth Tag 26",conversiontype.ToInt,"no",100)),
    "3C00" -> (()=>tagfunction(4, "Bluetooth Tag 27",conversiontype.ToInt,"no",100)),
    "3D00" -> (()=>tagfunction(4, "Bluetooth Tag 28",conversiontype.ToInt,"no",100)),
    "3E00" -> (()=>tagfunction(4, "Bluetooth Tag 29",conversiontype.ToInt,"no",100)),
    "3F00" -> (()=>tagfunction(4, "Bluetooth Tag 30",conversiontype.ToInt,"no",100)),
    "4000" -> (()=>tagfunction(4, "Bluetooth Tag 31",conversiontype.ToInt,"no",100)),
    "4100" -> (()=>tagfunction(4, "Bluetooth Tag 32",conversiontype.ToInt,"no",100)),
    "4200" -> (()=>tagfunction(4, "Bluetooth Tag 33",conversiontype.ToInt,"no",100)),
    "4300" -> (()=>tagfunction(4, "Bluetooth Tag 34",conversiontype.ToInt,"no",100)),
    "4400" -> (()=>tagfunction(4, "Bluetooth Tag 35",conversiontype.ToInt,"no",100)),
    "4500" -> (()=>tagfunction(4, "Bluetooth Tag 36",conversiontype.ToInt,"no",100)),
    "4600" -> (()=>tagfunction(4, "Bluetooth Tag 37",conversiontype.ToInt,"no",100)),
    "4700" -> (()=>tagfunction(4, "Bluetooth Tag 38",conversiontype.ToInt,"no",100)),
    "4800" -> (()=>tagfunction(4, "Bluetooth Tag 39",conversiontype.ToInt,"no",100)),
    "4900" -> (()=>tagfunction(4, "Bluetooth Tag 40",conversiontype.ToInt,"no",100)),
    "4A00" -> (()=>tagfunction(4, "Bluetooth Tag 41",conversiontype.ToInt,"no",100)),
    "4B00" -> (()=>tagfunction(4, "Bluetooth Tag 42",conversiontype.ToInt,"no",100)),
    "4C00" -> (()=>tagfunction(4, "Bluetooth Tag 43",conversiontype.ToInt,"no",100)),
    "4D00" -> (()=>tagfunction(4, "Bluetooth Tag 44",conversiontype.ToInt,"no",100)),
    "4E00" -> (()=>tagfunction(4, "Bluetooth Tag 45",conversiontype.ToInt,"no",100)),
    "4F00" -> (()=>tagfunction(4, "Bluetooth Tag 46",conversiontype.ToInt,"no",100)),
    "5000" -> (()=>tagfunction(4, "Bluetooth Tag 47",conversiontype.ToInt,"no",100)),
    "5100" -> (()=>tagfunction(4, "Bluetooth Tag 48",conversiontype.ToInt,"no",100)),
    "5200" -> (()=>tagfunction(4, "Bluetooth Tag 49",conversiontype.ToInt,"no",100)),
    "5300" -> (()=>tagfunction(4, "Bluetooth Tag 50",conversiontype.ToInt,"no",100)),
    "5400" -> (()=>tagfunction(4, "Bluetooth Tag 51",conversiontype.ToInt,"no",100)),
    "5500" -> (()=>tagfunction(4, "Bluetooth Tag 52",conversiontype.ToInt,"no",100)),
    "5600" -> (()=>tagfunction(4, "Bluetooth Tag 53",conversiontype.ToInt,"no",100)),
    "5700" -> (()=>tagfunction(4, "Bluetooth Tag 54",conversiontype.ToInt,"no",100)),
    "5800" -> (()=>tagfunction(4, "Bluetooth Tag 55",conversiontype.ToInt,"no",100)),
    "5900" -> (()=>tagfunction(4, "Bluetooth Tag 56",conversiontype.ToInt,"no",100)),
    "5A00" -> (()=>tagfunction(4, "Bluetooth Tag 57",conversiontype.ToInt,"no",100)),
    "5B00" -> (()=>tagfunction(4, "Bluetooth Tag 58",conversiontype.ToInt,"no",100)),
    "5C00" -> (()=>tagfunction(4, "Bluetooth Tag 59",conversiontype.ToInt,"no",100)),
    "5D00" -> (()=>tagfunction(4, "Bluetooth Tag 60",conversiontype.ToInt,"no",100)),
    "5E00" -> (()=>tagfunction(4, "Bluetooth Tag 61",conversiontype.ToInt,"no",100)),
    "5F00" -> (()=>tagfunction(4, "Bluetooth Tag 62",conversiontype.ToInt,"no",100)),
    "6000" -> (()=>tagfunction(4, "Bluetooth Tag 63",conversiontype.ToInt,"no",100)),

    "6100" -> (()=>tagfunction(4, "Tag Modbus 32" ,conversiontype.ToInt,"no",100)),
    "6200" -> (()=>tagfunction(4, "Tag Modbus 33" ,conversiontype.ToInt,"no",100)),
    "6300" -> (()=>tagfunction(4, "Tag Modbus 34" ,conversiontype.ToInt,"no",100)),
    "6400" -> (()=>tagfunction(4, "Tag Modbus 35" ,conversiontype.ToInt,"no",100)),
    "6500" -> (()=>tagfunction(4, "Tag Modbus 36" ,conversiontype.ToInt,"no",100)),
    "6600" -> (()=>tagfunction(4, "Tag Modbus 37" ,conversiontype.ToInt,"no",100)),
    "6700" -> (()=>tagfunction(4, "Tag Modbus 38" ,conversiontype.ToInt,"no",100)),
    "6800" -> (()=>tagfunction(4, "Tag Modbus 39" ,conversiontype.ToInt,"no",100)),
    "6900" -> (()=>tagfunction(4, "Tag Modbus 40" ,conversiontype.ToInt,"no",100)),
    "6A00" -> (()=>tagfunction(4, "Tag Modbus 41" ,conversiontype.ToInt,"no",100)),
    "6B00" -> (()=>tagfunction(4, "Tag Modbus 42" ,conversiontype.ToInt,"no",100)),
    "6C00" -> (()=>tagfunction(4, "Tag Modbus 43" ,conversiontype.ToInt,"no",100)),
    "6D00" -> (()=>tagfunction(4, "Tag Modbus 44" ,conversiontype.ToInt,"no",100)),
    "6E00" -> (()=>tagfunction(4, "Tag Modbus 45" ,conversiontype.ToInt,"no",100)),
    "6F00" -> (()=>tagfunction(4, "Tag Modbus 46" ,conversiontype.ToInt,"no",100)),
    "7000" -> (()=>tagfunction(4, "Tag Modbus 47" ,conversiontype.ToInt,"no",100)),
    "7100" -> (()=>tagfunction(4, "Tag Modbus 48" ,conversiontype.ToInt,"no",100)),
    "7200" -> (()=>tagfunction(4, "Tag Modbus 49" ,conversiontype.ToInt,"no",100)),
    "7300" -> (()=>tagfunction(4, "Tag Modbus 50" ,conversiontype.ToInt,"no",100)),
    "7400" -> (()=>tagfunction(4, "Tag Modbus 51" ,conversiontype.ToInt,"no",100)),
    "7500" -> (()=>tagfunction(4, "Tag Modbus 52" ,conversiontype.ToInt,"no",100)),
    "7600" -> (()=>tagfunction(4, "Tag Modbus 53" ,conversiontype.ToInt,"no",100)),
    "7700" -> (()=>tagfunction(4, "Tag Modbus 54" ,conversiontype.ToInt,"no",100)),
    "7800" -> (()=>tagfunction(4, "Tag Modbus 55" ,conversiontype.ToInt,"no",100)),
    "7900" -> (()=>tagfunction(4, "Tag Modbus 56" ,conversiontype.ToInt,"no",100)),
    "7A00" -> (()=>tagfunction(4, "Tag Modbus 57" ,conversiontype.ToInt,"no",100)),
    "7B00" -> (()=>tagfunction(4, "Tag Modbus 58" ,conversiontype.ToInt,"no",100)),
    "7C00" -> (()=>tagfunction(4, "Tag Modbus 59" ,conversiontype.ToInt,"no",100)),
    "7D00" -> (()=>tagfunction(4, "Tag Modbus 60" ,conversiontype.ToInt,"no",100)),
    "7E00" -> (()=>tagfunction(4, "Tag Modbus 61" ,conversiontype.ToInt,"no",100)),
    "7F00" -> (()=>tagfunction(4, "Tag Modbus 62" ,conversiontype.ToInt,"no",100)),
    "8000" -> (()=>tagfunction(4, "Tag Modbus 63" ,conversiontype.ToInt,"no",100)),

    "8100" -> (()=>tagfunction(2,  "Cell identifier",conversiontype.ToInt,"yes")),
    "8200" -> (()=>tagfunction(2,  "Local Area",conversiontype.ToInt,"yes")),
    "8300" -> (()=>tagfunction(2, "Country code",conversiontype.ToInt,"yes")),
    "8400" -> (()=>tagfunction(2, "Operation code",conversiontype.ToInt,"yes")),
    "8500" -> (()=>tagfunction(1, "RSSI",conversiontype.ToInt,"yes")),
    "8600" -> (()=>tagfunction(4,  "Temp Sensor Extended value tag 0" ,conversiontype.ExtTempSensor)),
    "8700" -> (()=>tagfunction(4,  "Temp Sensor Extended value tag 1" ,conversiontype.ExtTempSensor)),
    "8800" -> (()=>tagfunction(4,  "Temp Sensor Extended value tag 2" ,conversiontype.ExtTempSensor)),
    "8900" -> (()=>tagfunction(4,  "Temp Sensor Extended value tag 3" ,conversiontype.ExtTempSensor)),
    "8A00" -> (()=>tagfunction(4,  "Temp Sensor Extended value tag 4" ,conversiontype.ExtTempSensor)),
    "8B00" -> (()=>tagfunction(4,  "Temp Sensor Extended value tag 5" ,conversiontype.ExtTempSensor)),
    "8C00" -> (()=>tagfunction(4,  "Temp Sensor Extended value tag 6" ,conversiontype.ExtTempSensor)),
    "8D00" -> (()=>tagfunction(4,  "Temp Sensor Extended value tag 7" ,conversiontype.ExtTempSensor)),
    "8E00" -> (()=>tagfunction(4, "GPS satellie info tag",conversiontype.ToInt)),
    "8F00" -> (()=>tagfunction(4, "GLONASS satellie info tag",conversiontype.ToInt)),
    "9000" -> (()=>tagfunction(4, "BAIDOU satellie info tag",conversiontype.ToInt)),
    "9100" -> (()=>tagfunction(4, "GALILEO satellie info tag",conversiontype.ToInt)),
    "9200" -> (()=>tagfunction(15,  "Active SIM IMSI tag",conversiontype.ToASCII)),
    "9300" -> (()=>tagfunction(1,  "Active SIM card numb",conversiontype.ToInt)),
    "9400" -> (()=>tagfunction(20,  "Active SIM CCID tag",conversiontype.ToASCII))
  )
}
