#include <ArduinoJson.h>
#include <SoftwareSerial.h>
#include <SPI.h>
#include <RFID.h>
#include <SerialCommand.h>  // Thêm vào sketch thư viện Serial Command
#include <Servo.h>
Servo myservo;
const byte RX = 3;          // Chân 3 được dùng làm chân RX
const byte TX = 2;          // Chân 2 được dùng làm chân TX
 unsigned char reading_card[5]; // Mảng đọc mã card

SoftwareSerial mySerial = SoftwareSerial(RX, TX); 
 
 
SerialCommand sCmd(mySerial); // Khai báo biến sử dụng thư viện Serial Command
 
int red = 4, blue = 5; // led đỏ đối vô digital 4, led xanh đối vô digital 5
 
void setup() {
  //Khởi tạo Serial ở baudrate 57600 để debug ở serial monitor
  Serial.begin(57600);
 
  //Khởi tạo Serial ở baudrate 57600 cho cổng Serial thứ hai, dùng cho việc kết nối với ESP8266
  mySerial.begin(57600);
  
  //pinMode 2 đèn LED là OUTPUT
  pinMode(red,OUTPUT);
  pinMode(blue,OUTPUT);
  
  
  // Một số hàm trong thư viện Serial Command
  sCmd.addCommand("LED",   led); //Khi có lệnh LED thì sẽ thực thi hàm led  
  sCmd.addCommand("Accept", Accept);
  Serial.println("Da san sang nhan lenh");
  myservo.attach(7);
  myservo.write(0);
  
}
 
void loop() {
  sCmd.readSerial();
   if (rfid.isCard()) {
        if (rfid.readCardSerial()) // Nếu có thẻ
        {
 
            for (i = 0; i < 5; i++) {
 
                reading_card[i] = rfid.serNum[i]; //Lưu mã thẻ đọc được vào mảng reading_card
            }
            Serial.println();
              StaticJsonBuffer<200> jsonBuffer; //tạo Buffer json có khả năng chứa tối đa 200 ký tự
              JsonObject& root = jsonBuffer.parseObject(json);//đặt một biến root mang kiểu json
             //truyen ma the
              StaticJsonBuffer<200> jsonBuffer2;
              JsonObject& root2 = jsonBuffer2.createObject();
              root2["1"] = reding_card[0];
              root2["2"] = reding_card[1];
              root2["3"] = reding_card[2];
              root2["4"] = reding_card[3];
              root2["5"] = reding_card[4];
 
  //Tạo một mảng trong JSON
  JsonArray& data = root2.createNestedArray("data");
  data.add(redStatus); 
  data.add(blueStatus);
  
  
  //in ra cổng software serial để ESP8266 nhận
  mySerial.print("kiem-tra");   //gửi tên lệnh
  mySerial.print('\r');           // gửi \r
  root2.printTo(mySerial); //gửi chuỗi JSON
  mySerial.print('\r');           // gửi \r
        }
        rfid.halt();
    }
  //Bạn không cần phải thêm bất kỳ dòng code nào trong hàm loop này cả
}
 
// hàm led_red sẽ được thực thi khi gửi hàm LED_RED
void led() {
  Serial.println("LED");
  char *json = sCmd.next(); //Chỉ cần một dòng này để đọc tham số nhận đươc
  Serial.println(json);
  StaticJsonBuffer<200> jsonBuffer; //tạo Buffer json có khả năng chứa tối đa 200 ký tự
  JsonObject& root = jsonBuffer.parseObject(json);//đặt một biến root mang kiểu json
 
  int redStatus = root["led"][0];//json -> tham số root --> phần tử thứ 0. Đừng lo lắng nếu bạn không có phần tử này, không có bị lỗi đâu!
  int blueStatus = root["led"][1];//json -> tham số root --> phần tử thứ 0. Đừng lo lắng nếu bạn không có phần tử này, không có bị lỗi đâu!
 
  //kiểm thử giá trị
  Serial.print(F("redStatus "));
  Serial.println(redStatus);
  Serial.print(F("blueStatus "));
  Serial.println(blueStatus);
 
  
  //in ra Serial để debug
  root2.printTo(Serial); //Xuống dòng
  
  //xuất ra màn hình
  digitalWrite(red, redStatus);
  digitalWrite(blue, blueStatus);
}
void Accept(){
  myservo.write(90);
}
