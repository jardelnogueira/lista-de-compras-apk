package br.com.listadecompras.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CategoriaProduto (
    @PrimaryKey(autoGenerate = true)
    val idCategoria: Long,
    val descricaoTipo: String,
    val corTipo: String
        ) : Parcelable