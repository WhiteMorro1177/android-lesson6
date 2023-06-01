package ru.mirea.tsybulko.employeedb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class SuperHero {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var name: String? = null
    var ability: String? = null
}