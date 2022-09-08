package test
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import Galileosky._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class DemoTest extends AnyFlatSpec with Matchers{
  Str = "01 EA 00 03 33 35 39 31 35 39 39 37 32 32 36 38 32 30 36 10 5C 92 20 4E 1B AB 62 30 0F 5F 91 81 01 0D 51 4C 03 33 00 00 37 0A 34 19 00 35 06 40 00 3A 41 0F 2E 42 3E 0F 43 24 45 07 00 46 01 00 47 00 00 00 00 48 03 00 50 F1 2D 51 00 00 52 00 00 53 00 00 59 00 00 90 00 00 00 00 C4 00 C5 00 C6 00 C7 00 C8 00 C9 00 CA 00 D4 3B 04 00 00 D6 00 00 D7 00 00 D8 00 00 D9 00 00 DB 00 00 00 00 DC 00 00 00 00 E2 00 03 00 00 E3 00 00 00 00 E4 38 00 00 00 E7 38 34 04 00 E8 80 53 04 F2 E9 04 00 00 00 F8 00 00 00 00 F9 00 00 00 00 FE 3D 00 81 00 B3 22 82 00 0F 10 83 00 A8 01 84 00 02 00 85 00 01 92 00 34 32 34 30 32 34 30 31 31 32 33 37 34 35 30 93 00 00 94 00 39 39 37 31 31 32 32 31 32 37 34 38 30 35 32 36 30 34 35 00".replaceAll(" ","")
  String_len = Str.length

  "Galileosky" should "Hexa to Integer" in {
    ToInt("0A") should be (10)
  }
  it should "throw Format Exception " in {
    a[java.lang.NumberFormatException] should be thrownBy {
      ToInt("AS")
    }
  }

  it should "ASCII conversion " in {
    toASCII("33 35 39 31 35 39 39 37 32 32 36 38 32 30 36") should be ("359159972268206")
    }

  it should "Get Little endian format of a String" in {
    LittleEdianform("00AB1A") should be ("1AAB00")
  }


  it should "01 TAG RESULT" in { pointer = pointer+2
    tagfunction(2, "hi", conversiontype.ToInt, "yes")  should be (234)   }
  it should "03 TAG RESULT" in { pointer = pointer+2
    tagfunction(15, "hi", conversiontype.ToASCII) should be ("359159972268206")   }
  it should "10 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt) should be (23698)   }
  it should "20 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (1310436194)   }
  it should "30 TAG RESULT" in { pointer = pointer + 2
    tagfunction(9, "hi", conversiontype.Coordinates) should be ("Coordinates are correct, Number of Satellites 15, Latitude 55.333134, Longitude 25.268576")   }
  it should "33 TAG RESULT" in {pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.SpeedDirection) should be ("Direction 261.5 ,Speed 0.0")   }
  it should "34 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToSignedShort) should be (6400)   }
  it should "35 TAG RESULT" in { pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToInt) should be (6)   }
  it should "40 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt,"yes") should be (14848)   }
  it should "41 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt) should be (3886)   }
  it should "42 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt) should be (15887)   }
  it should "43 TAG RESULT" in { pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToSignedByte) should be (36)   }
  it should "45 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.NoChange) should be ("0700")   }
  it should "46 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.NoChange) should be ("0100")   }
  it should "47 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.EcoDrive) should be ("Acceleration 0   Braking 0   Cornering acceleration 0   Strike on bumps 0")   }
  it should "48 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ExpandedStatus) should be ("Primary server connected, PGPRS session on, no jamming detected, Additional server not connected, GPS/GLONASS jamming not detected, USB of device  not connected, SD card absent")   }
  it should "50 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt) should be (61741)   }
  it should "51 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt) should be (0)   }
  it should "52 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt) should be (0)   }
  it should "53 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt) should be (0)   }
  it should "59 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt) should be (0)   }
  it should "90 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (0)   }
  it should "C4 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToInt) should be (0)   }
  it should "C5 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToInt) should be (0)   }
  it should "C6 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToInt) should be (0)   }
  it should "C7 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToInt) should be (0)   }
  it should "C8 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToInt) should be (0)   }
  it should "C9 TAG RESULT" in {  pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToInt) should be (0)   }
  it should "CA TAG RESULT" in {  pointer = pointer + 2
    tagfunction(1, "hi", conversiontype.ToInt) should be (0)   }
  it should "D4 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (990117888)   }
  it should "D6 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt, "no",2) should be (0)   }
  it should "D7 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt, "no",2) should be (0)   }
  it should "D8 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt, "no",2) should be (0)   }
  it should "D9 TAG RESULT" in { pointer = pointer + 2
    tagfunction(2, "hi", conversiontype.ToInt, "no",2) should be (0)   }
  it should "DB TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt, "no",100) should be (0)   }
  it should "DC TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt, "no",10) should be (0)   }
  it should "E2 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (196608)   }
  it should "E3 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (0)   }
  it should "E4 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (939524096)   }
  it should "E7 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (942932992)   }
  it should "E8 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (2152924402L)   }
  it should "E9 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.ToInt) should be (67108864)   }
  it should "F8 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.NoChange) should be ("00000000")   }
  it should "F9 TAG RESULT" in { pointer = pointer + 2
    tagfunction(4, "hi", conversiontype.NoChange) should be ("00000000")   }
  it should "FE TAG RESULT" in { pointer = pointer + 2
    tagfunction(0, "hi", conversiontype.NoChange) should be ("")   }

  it should "fake test" in { pointer= ptrInc(2)
    "61" should be ("61")   }


  it should "81 TAG RESULT" in { pointer= ptrInc(2)
    tagfunction(2, "hi", conversiontype.ToInt, "yes") should be (8883)   }
  it should "82 TAG RESULT" in { pointer= ptrInc(2)
    tagfunction(2, "hi", conversiontype.ToInt, "yes") should be (4111)   }
  it should "83 TAG RESULT" in { pointer= ptrInc(2)
    tagfunction(2, "hi", conversiontype.ToInt, "yes") should be (424)   }
  it should "84 TAG RESULT" in { pointer= ptrInc(2)
    tagfunction(2, "hi", conversiontype.ToInt, "yes") should be (2)   }
  it should "85 TAG RESULT" in {  pointer= ptrInc(2)
    tagfunction(1, "hi", conversiontype.ToInt, "yes") should be (1)   }
  it should "92 TAG RESULT" in { pointer= ptrInc(2)
    tagfunction(15, "hi", conversiontype.ToASCII) should be ("424024011237450")   }
  it should "93 TAG RESULT" in {  pointer= ptrInc(2)
    tagfunction(1, "hi", conversiontype.ToInt) should be (0)   }
  it should "94 TAG RESULT" in {  pointer= ptrInc(2)
    tagfunction(20, "hi", conversiontype.ToASCII) should be ("9971122127480526045")   }


}
