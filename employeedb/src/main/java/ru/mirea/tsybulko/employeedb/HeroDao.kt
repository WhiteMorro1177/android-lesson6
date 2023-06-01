package ru.mirea.tsybulko.employeedb

import androidx.room.*


@Dao
interface HeroDao {
    @Query("SELECT * FROM superhero")
    fun getAll(): List<SuperHero?>?

    @Query("SELECT * FROM superhero WHERE id = :id")
    fun getById(id: Long): SuperHero?

    @Insert
    fun insert(hero: SuperHero?)

    @Update
    fun update(hero: SuperHero?)

    @Delete
    fun delete(hero: SuperHero?)
}