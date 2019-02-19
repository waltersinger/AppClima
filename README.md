# AppClima
La siguiente aplicacion muestra el clima (temperactura actual, informacion en general) de una localidad ingresada. Ademas muestra un pronostico extendido para los 7 dias posteriores. 
La aplicacion se conecta con las APIs de yahoo weather para obtener en un formato JSON los datos del mismo. Utilizo la libreria Volley para hacer las peticiones en un patron singleton.
Utilizo pageviewer para mostrar pronostico extendido y pickasso para traer la imagen del clima correspondiente.

Otra caracteristica es que cada vez que se inicia la app, trae una imagen aleatoria desde el sitio Flickr. La idea es implementar librerias y acceder a las API de varios sitios como yahoo weather y flickr.
La app finalizada es se muestra como sigue.
