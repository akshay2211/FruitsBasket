package data.repo

import data.local.database.FruitDao
import fruitsbasket.composeapp.generated.resources.Res
import fruitsbasket.composeapp.generated.resources.apple
import fruitsbasket.composeapp.generated.resources.grape
import fruitsbasket.composeapp.generated.resources.hawthorn
import fruitsbasket.composeapp.generated.resources.lemon
import fruitsbasket.composeapp.generated.resources.orange
import fruitsbasket.composeapp.generated.resources.strawberry
import models.FruitEntity
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt

class FruitRepository(private val fruitDao: FruitDao) {

    //todo: needs to replaced with data from remote api call
    private val fakeData = listOf(
        FruitEntity(
            name = "Apple",
            price = 1.50,
            currency = "USD",
            priced = "Per Pound",
            description = "Crisp, sweet, and juicy, apples are a popular and versatile fruit, perfect for snacks, salads, and baking. Available in various varieties such as Gala, Fuji, and Granny Smith.",
            image = ""
        ),
        FruitEntity(
            name = "Grapes",
            price = 2.00,
            currency = "USD",
            priced = "Per Pound",
            description = "Sweet and juicy, grapes come in green, red, and black varieties. Ideal for snacking, adding to fruit salads, or making wine. They are a healthy and refreshing option.",
            image = ""
        ),
        FruitEntity(
            name = "Orange",
            price = 1.20,
            currency = "USD",
            priced = "Per Pound",
            description = "Oranges are citrus fruits known for their vibrant color and juicy, tangy-sweet flavor. Excellent for fresh eating, juicing, or adding zest to recipes.",
            image = ""
        ),
        FruitEntity(
            name = "Strawberry",
            price = 3.00,
            currency = "USD",
            priced = "Per Pound",
            description = "Bright red, sweet, and slightly tart, strawberries are perfect for snacking, desserts, and smoothies. They are rich in vitamins and antioxidants.",
            image = ""
        ),
        FruitEntity(
            name = "Lemon",
            price = 0.80,
            currency = "USD",
            priced = "Per Piece",
            description = "Lemons are zesty and tangy citrus fruits used for their juice, zest, and essential oils. Ideal for beverages, cooking, baking, and as a garnish.",
            image = ""
        ),
        FruitEntity(
            name = "Hawthorn",
            price = 4.00,
            currency = "USD",
            priced = "Per Pound",
            description = "Tart and slightly sweet, hawthorn berries are often used in jams, jellies, and herbal teas. They are known for their health benefits, particularly for heart health.",
            image = ""
        ),
    ).shuffled()

    suspend fun insertFakeData() {
        fakeData.forEach { fruitEntity ->
            println(fruitEntity)
            upsertFruit(fruitEntity)
        }
    }

    suspend fun getFruitsCount() = fruitDao.getCount()

    suspend fun upsertFruit(fruitEntity: FruitEntity) {
        fruitDao.upsert(fruitEntity)
    }

    suspend fun delete(fruitEntity: FruitEntity) {
        fruitDao.delete(fruitEntity)
    }

    fun getAllFruits() = fruitDao.getAll()

    fun getSelectedFruits() = fruitDao.getSelectedFruits()
    fun getSelectedFruit(id: Long) = fruitDao.getSelectedFruit(id)
    suspend fun deleteAll() {
        fruitDao.deleteAll()
    }

}

fun Double.roundTo(numFractionDigits: Int): Double {
    val factor = 10.0.pow(numFractionDigits.toDouble())
    return (this * factor).roundToInt() / factor
}

fun String.getIcon() = when (this) {
    "Apple" -> Res.drawable.apple
    "Grapes" -> Res.drawable.grape
    "Orange" -> Res.drawable.orange
    "Strawberry" -> Res.drawable.strawberry
    "Lemon" -> Res.drawable.lemon
    "Hawthorn" -> Res.drawable.hawthorn
    else -> Res.drawable.apple
}

fun String.getCurrencySymbol() = when (this) {
    "USD" -> "$"
    else -> "₹"
}

fun FruitEntity.getUpdatePrice() =
    "${this.currency.getCurrencySymbol()} ${if (this.count > 0) abs(this.count * this.price) else this.price.roundTo(2)}"

fun FruitEntity.getFormattedPrice() = "${this.currency.getCurrencySymbol()} ${abs(this.price)}"
fun List<FruitEntity>.getUpdatedPrice() =
    "${this.firstOrNull()?.currency?.getCurrencySymbol() ?: ""} ${abs(this.sumOf { it.price * it.count })}"