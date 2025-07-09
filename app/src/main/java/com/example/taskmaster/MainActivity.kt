package com.example.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskmaster.ui.theme.TaskMasterTheme


/*

diseño de la app

-----------------------------
| TITULO    | + Nueva tarea |
|           ----------------|
|---------------------------|
| CheckBox Titulo Borrar    |
|---------------------------|
| CheckBox Titulo Borrar    |
|---------------------------|
| CheckBox Titulo Borrar    |
|---------------------------|
| CheckBox Titulo Borrar    |
|---------------------------|
...


*/

// Fernando Hernández Valverde

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskMasterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //funcion principal
                    TaskMasterApp()
                }
            }
        }
    }
}

// previsualizacion del layout
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TaskMasterTheme {
        TaskMasterApp()
    }
}



//tabla para cada tarea
data class Task(
    val id: Int,
    val title: String,
    var isCompleted: Boolean = false
)

//pantalla principal
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskMasterApp() {


    var tasks by remember { mutableStateOf(emptyList<Task>()) }
    var taskCounter by remember { mutableStateOf(1) }
    var showDialog by remember { mutableStateOf(false) }
    var newTaskTitle by remember { mutableStateOf("") }
    var editingTask by remember { mutableStateOf<Task?>(null) }
    var editedTaskTitle by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TaskMaster",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {

                    //boton para agregar una nueva tarea
                    Button(
                        onClick = { showDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .height(36.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Nueva Tarea",
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Nueva tarea",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    } // boton  +Nueva tarea-------------------------------------------------------
                } // actions-----------------------------------------------------------------------
            ) // topAppBar-------------------------------------------------------------------------
        } // top bar titulo y boton----------------------------------------------------------------
    ) { paddingValues ->
        // caja para mostrar las tareas
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // en caso de estar vacia, se mostrara un texto que ayude al usuario a crear una tarea
            if (tasks.isEmpty()) {
                Text(
                    text = "\nNo hay tareas\n\nPresiona el botón '+ Nueva tarea' para agregar una nueva",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            } else {
                // columna para mostrar las tareas en caso de que haya una o mas
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    items(
                        items = tasks,
                        key = { task -> task.id }
                    ) { task ->
                        TaskItem(
                            task = task,
                            onTaskChecked = { isChecked ->
                                tasks = tasks.map {
                                    if (it.id == task.id) it.copy(isCompleted = isChecked) else it
                                }
                            },
                            onDeleteTask = {
                                tasks = tasks.filter { it.id != task.id }
                            },
                            onEditTask = {
                                editingTask = task
                                editedTaskTitle = task.title
                            }
                        )

                        Divider(color = Color.LightGray, thickness = 1.dp)
                    }
                }
            } // else tasks.isEmpty()---------------------------------------------------------------

            // formulario para ponerle titulo a una tarea y crearla
            if (showDialog) {

                //ventana para el formulario
                Dialog(
                    onDismissRequest = { showDialog = false }
                ) {
                    //no se que es esto
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .width(280.dp)
                            .wrapContentHeight()
                    ) {
                        // columna para acomodar los elementos del formulario
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Nueva Tarea",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            OutlinedTextField(
                                value = newTaskTitle,
                                onValueChange = { newTaskTitle = it },
                                label = { Text("Titulo") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            // espacio en blanco
                            Spacer(modifier = Modifier.height(16.dp))

                            // fila para ordenar los botones del formulario
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                //boton cancelar la accion
                                TextButton(
                                    onClick = { showDialog = false }
                                ) {
                                    Text("Cancelar")
                                }

                                // separador
                                Spacer(modifier = Modifier.width(8.dp))

                                //boton para aceptar el formulario y agregar la tarea
                                Button(
                                    onClick = {

                                        // la tarea sera agregada mientras no este vacio el titulo
                                        //   en el formulario
                                        if (newTaskTitle.isNotBlank()) {
                                            tasks = tasks + Task(
                                                id = taskCounter,
                                                title = newTaskTitle
                                            )
                                            taskCounter++
                                            newTaskTitle = ""
                                            showDialog = false
                                        }
                                    },
                                    enabled = newTaskTitle.isNotBlank()
                                ) {
                                    Text("Agregar")
                                } // boton para aceptar el formulario -----------------------------
                            } // fila para ordenar los botones-------------------------------------
                        } // columna formulario----------------------------------------------------
                    } // no se que es esto (surface)--------
                } // ventanita para crear el dialog-------------------------------------------------
            } // mostrar el formulario para crear una nueva tarea-----------------------------------

            // para actualizar el titulo de la tarea
            if (editingTask != null) {
                Dialog(onDismissRequest = { editingTask = null }) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .width(280.dp)
                            .wrapContentHeight()
                    ) {

                        // tcolumna para ordenar los elementos visuales
                        Column(modifier = Modifier.padding(16.dp)) {

                            //titulo del cuadro
                            Text(
                                text = "Editar Titulo",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            //cuadro para exribir el nuevo titulo
                            OutlinedTextField(
                                value = editedTaskTitle,
                                onValueChange = { editedTaskTitle = it },
                                label = { Text("Título") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // fila para ordenar los botones
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                // coton para cancelar la accion
                                TextButton(onClick = { editingTask = null }) {
                                    Text("Cancelar")
                                }

                                // espacio para separar los botones
                                Spacer(modifier = Modifier.width(8.dp))

                                // boton para aceptar
                                Button(
                                    onClick = {
                                        if (editedTaskTitle.isNotBlank()) {
                                            tasks = tasks.map {
                                                if (it.id == editingTask!!.id)
                                                    it.copy(title = editedTaskTitle)
                                                else it
                                            }
                                            editingTask = null
                                        }
                                    },
                                    enabled = editedTaskTitle.isNotBlank()
                                ) {
                                    Text("Guardar")
                                }
                            } // fila para ordenar los botones--------------------------------------
                        }
                    }
                } //cuadro de dialogo para actualizar la tarea
            } // if editingTask

        } // Box para mostrar las tareas-----------------------------------------------------------
    } // scalfold----------------------------------------------------------------------------------
} // dun TaskManagerApp----------------------------------------------------------------------------


//funcion para mostrar cada tarea agregada
@Composable
fun TaskItem(
    task: Task,
    onTaskChecked: (Boolean) -> Unit,
    onDeleteTask: () -> Unit,
    onEditTask: () -> Unit
)
{
    
    // fila para ordenar los elementos de cada cuadro de tareas
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

            // esto hara que se muestre diferente el cuadro de tarea cada vez que se active
            //   el check box
            .background(
                // si se activa, el cuadro se hara visible en un gris transparente
                if (task.isCompleted) Color.LightGray.copy(alpha = 0.3f)

                // de lo contrario, si no esta activado el checkbox (tarea sin completar)
                //   no se mostrara el cuadro, su color sera transparente
                else Color.Transparent
            ),
        // ajustamos en el centro los elementos de la fila
        verticalAlignment = Alignment.CenterVertically
    ) {
        // checkbox para marcar la tarea como completada
        Checkbox(
            // si se marca, el checkbox usara el metodo del objeto tarea
            checked = task.isCompleted,
            onCheckedChange = onTaskChecked,
            modifier = Modifier.padding(end = 8.dp)
        ) // checkbox para marcar la tarea como completada

        // texto para mostrar el titulo de la tarea
        Text(
            text = task.title,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            fontSize = 18.sp,

            // esto hace que el color del cuadrado que muestra la tarea cambie de color al
            //   marchar el checkbox
            color = if (task.isCompleted) Color.Gray else MaterialTheme.colorScheme.onBackground,

            // cambia el grosor del texto a mas delgado cuando se marca el checkbox
            fontWeight = if (task.isCompleted) FontWeight.Normal else FontWeight.Medium,

            // se tacha la tarea cuando se marcka el checkbox
            textDecoration = if(task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
        ) // texto que muestra el titulo de la tarea

        //boton para actualizar el titulo de la tarea
        IconButton(
            onClick = onEditTask
        ) {
            Icon(
                imageVector = Icons.Default.Edit, // puedes usar Icons.Default.Edit si está disponible
                contentDescription = "Editar tarea",
                tint = Color.Cyan
            )
        }


        // boton para borrar la tarea
        IconButton(

            // metodo para borrar la tarea
            onClick = onDeleteTask
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Eliminar tarea",
                tint = Color.Red
            )
        } // boton para borrar la tarea-------------------------------------------------------------
    } // row para los elementos de las tareas ------------------------------------------------------
} // fun TaskItem-----------------------------------------------------------------------------------
