package br.com.listadecompras.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun formataValorMonetarioBR(valor: BigDecimal?): String {
    val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatador.format(valor)
}