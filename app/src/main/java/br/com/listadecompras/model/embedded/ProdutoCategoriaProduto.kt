package br.com.listadecompras.model.embedded

import androidx.room.Embedded
import androidx.room.Relation
import br.com.listadecompras.model.CategoriaProduto
import br.com.listadecompras.model.Produto

data class ProdutoCategoriaProduto (
    @Embedded val categoriaProduto: CategoriaProduto,
    @Relation(
        parentColumn = "idCategoria",
        entityColumn = "idCategoria"
    )
    val produto: Produto
)