package models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruit")
data class FruitEntity(
    @PrimaryKey(true) val id:Long = 0L,
    val name:String = "",
    val price: Double = 0.00,
    val currency: String = "",
    val priced:String = "",
    val image:String = "",
    val description:String = "",
    val count:Int = 0)