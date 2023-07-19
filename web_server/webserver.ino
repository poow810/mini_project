#include <WiFi.h>
#include <WiFiClient.h>
#include <WebServer.h>

const char* ssid = "SmartFactory";
const char* password = "inha4885";

WebServer server(80);  // create WebServer object, port

void setup(void) {
  Serial.begin(115200);  // ESP32 baud rate
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  Serial.println("");
  
  while (WiFi.status() != WL_CONNECTED) { // wait for connection
    delay(500);
    Serial.print("-> ");
  }
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP addr: ");
  Serial.println(WiFi.localIP()); // print assigned ip address

  server.on("/", handleRootEvent);  // root, event handling function

  server.begin();
  Serial.println("Web server started!");
}

void loop(void) {
  server.handleClient();  // clients process
  delay(5); // 5/1000 sec
}

void handleRootEvent() {
  Serial.println("main page");
  server.send(200, "text/plain", "Welcome Inha SmartFactory WebServer!");  // status code 200(OK), format, message
}