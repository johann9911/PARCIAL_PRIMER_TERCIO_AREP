# Parcial Johann Steven Bogotá
# Parcial primer tercio

Se construyo un webservice simple desplegado en heroku, en este webservice se puede consultar el estado en lugares especificos de la tierra. Para obtener esta información se utilizo la API gratuita de la página ** https://openweathermap.org/ **, igualmente se implemento un cache para que la aplicación sea eficiente 

La API de este servicio esta dado por:
{url del servicio en heroku}/clima?lugar={ciudad o lugar}


## Compilar programa
- Para construir el programa y ejecutar todas las fases de un repositorio maven
```
mvn clean install
``` 
- Para correr los tests
```
mvn test
```
- Para generar la documentación
```
mcn javadoc:javadoc o mvn javadoc:jar (generar jar)
```
## Ejecutar Programa
Para probrar el programa localmente, ejecutar y dirigirse a  **http://localhost:35000/**

## Enlace Heroku
https://safe-beach-16400.herokuapp.com/clima?lugar=bogota
## Licencia
Ver licencia en LICENCE.txt para más detalles.