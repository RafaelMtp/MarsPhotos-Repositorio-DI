# Mars Photos con repositorio e inyección de dependencias manual

Práctica del codelab de Android Developers «Cómo agregar un repositorio y una DI manual».

La app consulta la API pública de Marte y muestra cuántas fotografías se recuperaron. Esta etapa del codelab muestra un contador, no una galería de imágenes. Requiere conexión a internet para consultar la API.

## Abrir y ejecutar en Android Studio

1. Descomprime el ZIP y abre la carpeta `MarsPhotos` con **File > Open**. La carpeta correcta contiene `settings.gradle.kts`.
2. Espera la sincronización de Gradle. Permite descargar Android SDK 34 y Build Tools 34.0.0 si Android Studio los solicita.
3. En la configuración de Gradle utiliza JDK 17 o JDK 21. La compilación de comprobación se hizo con JDK 21.
4. Elige el módulo `app` y un emulador o teléfono con Android 7.0 o superior.
5. Pulsa **Run**. Con una respuesta válida aparece `Success: N Mars photos retrieved`. La cantidad puede variar.

## Qué cambió

- `data/MarsPhotosRepository.kt`: contrato del repositorio e implementación que recibe el servicio por constructor.
- `data/AppContainer.kt`: crea Retrofit, el servicio y el repositorio.
- `MarsPhotosApplication.kt`: conserva el contenedor durante la vida de la aplicación.
- `AndroidManifest.xml`: registra `.MarsPhotosApplication`.
- `ui/screens/MarsViewModel.kt`: recibe el repositorio y expone los estados Loading, Success y Error. Su Factory obtiene el repositorio del contenedor.
- `ui/MarsPhotosApp.kt`: crea el ViewModel con `viewModel(factory = MarsViewModel.Factory)`.
- `app/src/test/java/com/example/marsphotos`: pruebas con una API y un repositorio falsos.

## Ejecutar las pruebas

En Windows, desde la terminal del proyecto:

```powershell
.\gradlew.bat :app:testDebugUnitTest
```

En macOS o Linux:

```bash
./gradlew :app:testDebugUnitTest
```

También puedes abrir `MarsViewModelTest` y `NetworkMarsRepositoryTest` en Android Studio y ejecutarlos con el botón verde. Las seis pruebas son locales y no necesitan un emulador ni una API disponible.

Comprobación completa:

```bash
./gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
```

## Resultados comprobados

- Compilación debug correcta y APK generado.
- 6 pruebas aprobadas, 0 fallos y 0 errores.
- Lint sin errores; 4 advertencias del proyecto base sobre una etiqueta redundante y recursos sin utilizar.
- La consulta independiente a la API obtuvo HTTP 200 y 838 registros el 18 de noviembre de 2026. No es un valor fijo ni una captura de la app.
- Los registros originales de la comprobación se guardan en `evidencias/`. Abre `evidencias/pruebas_unitarias/index.html` para ver el informe de Gradle.

Estas comprobaciones se ejecutaron en el entorno de preparación. Falta ejecutar la app en la PC del alumno para obtener las capturas que solicita la actividad.

## Evidencias que debes añadir al informe

1. Android Studio con la app ejecutándose y el mensaje de éxito visible en el emulador o teléfono.
2. Android Studio con las seis pruebas aprobadas. También puedes capturar la terminal con `BUILD SUCCESSFUL`.
3. El enlace del repositorio publicado y su historial de commits.

No sustituyas tus capturas por imágenes del codelab. El informe Word incluye una sección para insertar tus evidencias reales.

## GitHub e historial

El paquete conserva el historial Git de preparación. No uses «Upload files» si quieres conservar esos commits. Publica desde Android Studio con **Git > GitHub > Share Project on GitHub**, o conecta un repositorio vacío con Git y haz `push` de `main`.

Los cambios de preparación están registrados como commits de Codex; se conserva la atribución del proyecto base de Android Open Source Project. Tus cambios posteriores deben usar la identidad configurada en tu cuenta Git.

## Fuente

- Codelab: https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository?hl=es-419
- Código base: https://github.com/google-developer-training/basic-android-kotlin-compose-training-mars-photos/tree/repo-starter
- Licencia del código base: Apache License 2.0, conservada en `LICENSE`.

Los archivos nuevos y modificados se prepararon para completar esta práctica. Se mantienen los avisos de licencia del código de Google; no se añadieron comentarios explicativos en las actividades.
