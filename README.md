TemperatureMonitorBack
======================



#include <OneWire.h>
#include <DallasTemperature.h>
#include <SPI.h>
#include <Ethernet.h> 
 
#define ONE_WIRE_BUS 3

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
char server[] = "192.168.0.10";
IPAddress ip(192,168,0,20);
EthernetClient client;

OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);
 
void setup(void)
{
  // start serial port
  Serial.begin(9600);
  Serial.println("Dallas Temperature IC Control Library Demo");

  // Start up the library
  sensors.begin();

   // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    // try to congifure using IP address instead of DHCP:
    Ethernet.begin(mac, ip);
  }
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("connecting...");
}
 
 
void loop(void)
{
  // call sensors.requestTemperatures() to issue a global temperature
  // request to all devices on the bus
  Serial.print(" Requesting temperatures...");
  sensors.requestTemperatures(); // Send the command to get temperatures
  Serial.println("DONE");

  Serial.print("Temperature for Device 1 is: ");
  Serial.println(sensors.getTempCByIndex(0)); // Why "byIndex"? 
  if (client.connect(server, 8182)) {
    Serial.println("connected");
    client.print("GET /temp/rest/temp/add?probeId=1&temperature=");
    client.print((int)sensors.getTempCByIndex(0));
   client.println(" HTTP/1.0");
    client.println();
    
    if (client.available()) {
    char c = client.read();
    Serial.print(c);
  }
  
    client.stop();
  } 
  
  else {
    // kf you didn't get a connection to the server:
    Serial.println("connection failed");
  }
    delay(5000);
}
