TemperatureMonitorBack
======================
```

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
  Serial.begin(9600);
  sensors.begin();
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    Ethernet.begin(mac, ip);
  }
  delay(1000);
}

void loop(void)
{
  sensors.requestTemperatures(); // Send the command to get temperatures
  Serial.print("Temperature for Device 1 is: ");
  Serial.println(sensors.getTempCByIndex(0)); // Why "byIndex"? 
  if (client.connect(server, 8182)) {
    client.print("GET /temp/rest/temp/add?probeId=1&temperature=");
    client.print((int)sensors.getTempCByIndex(0));
    client.println(" HTTP/1.0");
    client.println();
    client.stop();
  } 
  else {
    Serial.println("connection failed");
  }
    delay(5000);
}
```
