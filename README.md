# Ejercicio-Mercap
## Realizar un sistema de facturación de llamadas telefónicas teniendo en cuenta los siguientes requerimientos:
### 1) La facturación se realiza de manera mensual
### 2) La facturación está compuesta por:
a. Un abono mensual básico
b. Consumo por llamadas Locales
c. Consumo por llamadas Nacionales e Internacionales
### 3) Las llamadas locales tienen distintos valores según la franja horaria en la que se realizan y el día.
Para los días hábiles, de 8 a 20 hrs. el costo es de 0,20 centavos el minuto, mientras en el resto de las horas es de 0,10 centavos el minuto.
Los sábados y domingos cuesta 0,10 centavos el minuto
### 4) Las llamadas Internacionales tienen un costo distinto según el país al que se llame
### 5) Las llamadas Nacionales tienen un costo distinto según la localidad a la que se llame
## Consideraciones adicionales:
1) No es necesario realizar una interfaz de usuario visual.
2) No es necesario realizar persistencia de los datos (o sea, conexión a base de datos,archivos, etc.). Alcanza con simular los datos creándolos en memoria
3) Como salida alcanza ver por pantalla como sería una factura (sin preocuparse pordarle un formato especial)


# Implementacion de solucion

## Diagrama de clases UML
![Diagramas de clases.jpeg](docs/Diagramas%20de%20clases.jpeg)

## Aclaraciones - Decisiones de Diseño
Se priorizo la legibilidad, escalabilidad y buenas prácticas:
- Se implemento una clase Destination para tener consistencia de datos, evitar if innecesarios y ademas abstraer el precio del minuto segun cada destino (Nacional o Internacional).
- Se aplico el principio Open/Closed Principle de los principios SOLID a la hora de crear la clase abstracta Call, donde la clase Invoince solo conoce a la clase Call y no como calcula su costo.
- Se implemento una clase TimeSlot, que es un value object, centralizando el concepto de rango horario pico y cuanto sale su minuto.
- Se podria haber implementado un service y varios repository para generar una arquitectura mas robusta y que sea el service el encargado de instanciar la clase Invoice y devolverla.
