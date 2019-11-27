int potentiometerPin = 0;
int value = 0;
void setup()
{
  Serial.begin(9600); // inizializzazione porta seriale
}
void loop()
{
  value = analogRead(potentiometerPin); // leggo valore potenziometro
  Serial.println(value); // mando in seriale i dati
  delay(300);//velocità
}
