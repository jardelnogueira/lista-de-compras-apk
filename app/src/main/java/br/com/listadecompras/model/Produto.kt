package br.com.listadecompras.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Produto (
    @PrimaryKey(autoGenerate = true)
    val idProduto: Long = 0L,
    val nome: String,
    val valor: BigDecimal? = null,
    val quantidade: Int,
    val pesoEmbalagem: BigDecimal? = null,
    val idCategoria: Long? = null,
    //
    val unidade: String,
    val status: String
        ) : Parcelable
