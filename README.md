# Web Scraping en CurseForge

Este script de Web Scraping utiliza Selenium y Java para extraer información sobre juegos y sus respectivos mods desde la página web de CurseForge (https://curseforge.com/). CurseForge es conocido por ser una plataforma que ofrece mods para varios videojuegos, y esta práctica te ayudará a obtener detalles sobre juegos y los mods asociados.

## Requisitos

- Java JDK instalado (versión 8 o superior)
- WebDriver para Firefox (geckodriver)
- Firefox instalado
- Bibliotecas Selenium (pueden descargarse desde [Selenium](https://www.selenium.dev/downloads/))

## Instrucciones de Uso

1. **Configuración del Entorno:**
   - Asegúrate de tener el entorno configurado correctamente con el JDK instalado y las variables de entorno configuradas.
   - Descarga el WebDriver para Firefox (geckodriver) y colócalo en la ruta especificada en el script.

2. **Ejecutar el Script:**
   - Abre el script `PMCurseForge.java` en tu entorno de desarrollo o editor Java.
   - Ejecuta el script.

3. **Interactuar con el Script:**
   - Al ejecutar el script, abrirá el sitio web de CurseForge y aceptará el aviso de privacidad automáticamente.
   - Verás información sobre los juegos disponibles y podrás explorar más detalles sobre cada juego y sus mods.

4. **Precaución para Minecraft:**
   - Cuando el script llegue al juego de Minecraft y antes de hacer clic en "View all" para ver todos los mods, es necesario minimizar la ventana del navegador para evitar problemas con el aviso de privacidad. Este paso es crucial para garantizar una extracción de datos sin interrupciones.

## Notas Adicionales

- El script utiliza Selenium para la automatización del navegador Firefox. Asegúrate de tener Firefox instalado en tu máquina.
- Ajusta la ruta del geckodriver según la ubicación en tu sistema.
- El script imprimirá información sobre los juegos y sus mods en la consola.

¡Disfruta explorando la información de CurseForge con este script de Web Scraping! Si encuentras problemas o necesitas mejoras, no dudes en ajustar el código según tus necesidades.
