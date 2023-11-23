package br.com.listadecompras.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UnidadeProduto(
    @PrimaryKey(autoGenerate = true)
    val idUnidade: Long,
    val descricaoUnidade: String,
    val siglaUnidade: String
) : Parcelable
