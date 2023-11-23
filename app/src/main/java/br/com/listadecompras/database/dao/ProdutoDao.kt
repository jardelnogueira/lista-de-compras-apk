package br.com.listadecompras.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import br.com.listadecompras.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscarTodosProdutos() : List<Produto>

    @Query("SELECT * FROM Produto WHERE status LIKE 'Compras'")
    fun buscarProdutosCompras() : List<Produto>

    @Query("SELECT * FROM Produto WHERE status LIKE 'Despensa'")
    fun buscarProdutosDespensa() : List<Produto>

    @Query("SELECT * FROM Produto WHERE status LIKE 'Excluido'")
    fun buscarProdutosExcluidos() : List<Produto>

    //@Query("DELETE FROM Produto WHERE idProduto IN (:produtos)")
    //fun removerVariosProdutos(produtos: List<Long>)

    @Query("SELECT * FROM Produto WHERE idProduto = :idProduto")
    fun buscarProdutoPorId(idProduto: Long) : Produto?

    @Query("SELECT * FROM Produto WHERE idCategoria = :idCategoria")
    fun buscarProdutoPorCategoria(idCategoria: Long) : List<Produto>

    @Query("SELECT idProduto FROM Produto")
    fun buscarTodosIds(): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvarProduto(vararg produto: Produto)

    @Delete
    fun removerProduto(produto: Produto)

    @Delete
    fun removerVariosProdutos(produtos: List<Produto>)
}