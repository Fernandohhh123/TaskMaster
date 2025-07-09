
-------------------------------------------------------------------------
|									|
| ####### ##### ##### #  #   ##     ## ##### ##### ####### ##### #####  |
|    #    #   # #     # #    # #   # # #   # #        #    #     #   #  |  
|    #    ##### ##### ##     #  # #  # ##### #####    #    ##### #####  |
|    #    #   #     # # #    #   #   # #   #     #    #    #     #  #   |
|    #    #   # ##### #  #   #       # #   # #####    #    ##### #   #  |
|									|
-------------------------------------------------------------------------


-- DESCRIPCION --

Task Master es un gestor de tareas basico y funcinal de codigo abierto enfocado en la
simplicidad.

Este proyecto escolar fue desarrollado por Fernando Hernandez Valverde para la materia de aplicaciones moviles.


-- CARACTERISTICAS --

  - El usuario puede agregar una tarea mediante un campo de texto y un boton.
  - Cada tarea muestra un titulo y un checkbox para marcarla como completada.
  - Al marcar una tarea como completada, se marcara como tachada y cambiara a un color mas tenue
     en el cuadro en donde se muestra la tarea.
  - Se puede actualizar (cambiar) el titulo de la tarea.
  - Se podran eliminar las tareas con un boton de borrar.
  - La lista de tareas se actualiza cuando se agrega una nueva tarea, se edita el titulo o se borra una tarea.
  - Cuando hay demasiadas tareas en la pantalla se puede hacer scoll.
  - No tiene papelera para las tareas borradas.
  - Las tareas no se guardan en una base de datos, por lo tanto, al cerrar la aplicacion, estas se perderan.


-- INSTALACION --

  - Puede descargar el apk "app-debug.apk" del repositorio de github: https://github.com/Fernandohhh123/TaskMaster
    - Instalelo como un apk cualquiera

  - Puede clonar el repositorio de github: https://github.com/Fernandohhh123/TaskMaster.git
    - Puede compilar el proyecto con android studio
    - El proyecto se hizo para android 5.0, con java 17.0.12 y para la interfaz se uso jetpack


-- MODO DE USO --
  - Pasos para agregar una tarea:
      1.- Presione el boton verde "+ Nueva tarea" ubicado en la esquina superior derecha de la pantalla.
      2.- Se abrira una pequeña ventanita (formulario) que unicamente tiene un campo el cual es el titulo
           de la tarea (puede ser un titulo o un texto cualquiera que desee el usuario).
      3.- Una vez lleno el campo de texto (Titulo) presione el boton "Aceptar" ubicado en la esquina inferior derecha de la ventana
           de dialogo que se abrio al presionar el boton "+ Nueva tarea".
      4.- En caso de que no quiera agregar una tarea nueva puede presionar el boton "Cancelar" ubicado en la esquina inferior izquierda
           de la ventanita.

  - Como actualizar el titulo de una tarea:
    1.- Presione el icono del lapiz qe esta a lado del boton borrar.
    2.- Se abrira una ventana de dialog parecida a la de agregar tarea
    3.- Escriba el nuevo titulo y presione el boton de aceptar
    4.- La tarea tendra un titulo actualizado
        - si no quiere actualizar el titulo solo presione el boton de "Cancelar"

  - Como borrar una tarea:
      1.- Simplemente toque el boton rojo ubicado en la parte derecha de la tarea que desee eliminar "x"
          (La tarea se borrara sin preguntarle al usuario previamente :P).
