package br.com.listadecompras.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.listadecompras.model.CategoriaProduto

@Dao
interface CategoriaProdutoDao {

    @Query("SELECT * FROM CategoriaProduto")
    fun buscarTodasCategorias() : List<CategoriaProduto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvarCategoria(vararg categoria : CategoriaProduto)

    @Delete
    fun removerCategoria(categoria: CategoriaProduto)

    @Query("SELECT * FROM CategoriaProduto WHERE idCategoria = :idCategoria")
    fun buscarCategoriaPorId(idCategoria: Long) : CategoriaProduto?
}