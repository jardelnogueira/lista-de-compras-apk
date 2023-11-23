package br.com.listadecompras.model.embedded

import androidx.room.Embedded
import androidx.room.Relation
import br.com.listadecompras.model.Produto
import br.com.listadecompras.model.UnidadeProduto

data class ProdutoUnidadeProduto (
    @Embedded val unidadeProduto: UnidadeProduto,
    @Relation(
        parentColumn = "idUnidade",
        entityColumn = "idUnidade"
    )
    val produto: Produto
)