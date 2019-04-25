package com.example.myapplication

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_save.setOnClickListener(){
            guardar()
        }
    }

    fun guardar(){
        val nombre = et_name.text.toString()
        val content = et_content.text.toString()

        try {
            val cardSD: File = Environment.getExternalStorageDirectory()
            var file =  File(cardSD.path,nombre)
            val isNewFileCreated :Boolean = file.createNewFile()
            if(isNewFileCreated){
                var newFile = OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE))
                newFile.use{
                    it.write(content)
                    it.flush()
                    it.close()
                }
                Toast.makeText(this,"$nombre fue creado y guardado con exito",Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this,"$nombre ya existe",Toast.LENGTH_LONG).show()
            }
        }catch (e: IOException){
            Toast.makeText(this,"No se pudo guardar",Toast.LENGTH_LONG).show()
        }
    }
}
