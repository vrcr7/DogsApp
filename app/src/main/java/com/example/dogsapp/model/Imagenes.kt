package com.example.dogsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_RAZA")
data class Imagenes (
    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "raza")
    val raza: String,
    @ColumnInfo(name = "url")
    val url: String,


    )