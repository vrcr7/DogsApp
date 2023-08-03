package com.example.dogsapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogsapp.model.remote.clases.DogsApiClass
import retrofit2.http.DELETE

@Dao
interface DogsDao {
    @Query("SELECT * FROM TABLE_DOGS ORDER BY id ASC")
    fun getAllDatos(): LiveData<List<Dogs>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllDogs(listDogs: List<Dogs>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dog:Dogs)

    @Query("DELETE FROM TABLE_DOGS")
    suspend fun deleteAll()
}