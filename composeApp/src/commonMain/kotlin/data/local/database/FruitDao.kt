package data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import models.FruitEntity

@Dao
interface FruitDao {

    @Query("SELECT * FROM fruit")
    fun getAll(): Flow<List<FruitEntity>>


    @Query("SELECT Count(*) FROM fruit")
    suspend fun getCount(): Int


    @Query("SELECT * FROM fruit WHERE count>0")
    fun getSelectedFruits(): Flow<List<FruitEntity>>

    @Query("SELECT * FROM fruit WHERE id == :id LIMIT 1")
    fun getSelectedFruit(id: Long): Flow<FruitEntity>

    @Upsert
    suspend fun upsert(fruit: FruitEntity)

    @Delete
    suspend fun delete(fruit: FruitEntity)

    @Query("DELETE FROM fruit")
    suspend fun deleteAll()

}