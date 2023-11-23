package br.com.listadecompras.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.listadecompras.model.UnidadeProduto

@Dao
interface UnidadeProdutoDao {
    @Query("SELECT * FROM UnidadeProduto")
    fun buscarTodasUnidades() : List<UnidadeProduto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvarUnidade(vararg unidadeProduto: UnidadeProduto)

    @Delete
    fun removerUnidade(unidadeProduto: UnidadeProduto)

    @Query("SELECT * FROM UnidadeProduto WHERE idUnidade = :idUnidade")
    fun buscarUnidadePorId(idUnidade: Long) : List<UnidadeProduto>
}