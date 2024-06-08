package data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repo.FruitRepository
import kotlinx.coroutines.launch
import models.FruitEntity

class FruitViewModel(private val fruitRepository: FruitRepository) : ViewModel() {
    // initialised with first row of the database
    var selectedFruit = fruitRepository.getSelectedFruit(0)
    val fruitList = fruitRepository.getAllFruits()
    val selectedFruits = fruitRepository.getSelectedFruits()


    private fun upsert(fruitEntity: FruitEntity) {
        viewModelScope.launch {
            fruitRepository.upsertFruit(fruitEntity)
        }
    }

    fun insertFakeData() {
        viewModelScope.launch {
            val count = fruitRepository.getFruitsCount()
            if (count == 0) fruitRepository.insertFakeData()
        }
    }

    fun incrementCount(fruitEntity: FruitEntity) {
        upsert(fruitEntity.copy(count = fruitEntity.count + 1))
    }

    fun decrementCount(fruitEntity: FruitEntity) {
        if (fruitEntity.count > 0) upsert(fruitEntity.copy(count = fruitEntity.count - 1))
    }

    fun select(fruitEntity: FruitEntity) {
        selectedFruit = fruitRepository.getSelectedFruit(fruitEntity.id)
    }

    fun deleteAll() {
        viewModelScope.launch {
            fruitRepository.deleteAll()
        }

    }
}