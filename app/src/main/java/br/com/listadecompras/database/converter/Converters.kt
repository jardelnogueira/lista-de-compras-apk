package br.com.listadecompras.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun doubleToBig(valor: Double?) : BigDecimal {
        return valor?.let { BigDecimal(valor.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigToDouble(valor: BigDecimal?) : Double? {
        return valor?.let { valor.toDouble() }
    }
}