package br.com.listadecompras.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.listadecompras.databinding.ProdutoItemBinding
import br.com.listadecompras.extensions.formataValorMonetarioBR
import br.com.listadecompras.model.Produto
import com.google.android.material.card.MaterialCardView
import java.math.BigDecimal

class ListaDeComprasAdapter(
    private val context : Context,
    produtos: List<Produto> = emptyList(),
    produtosParaCheckAll: List<MaterialCardView> = emptyList<MaterialCardView>(),
    var enviarListaParaRemover: (produtos: MutableList<Produto>) -> Unit = {},
    var enviarListaChecked: (produtos: List<MaterialCardView>) -> Unit = {},
) :
    RecyclerView.Adapter<ListaDeComprasAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()
    val produtosParaCheckAll = produtosParaCheckAll.toMutableList()
    val produtosParaRemover = emptyList<Produto>().toMutableList()


    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var produto: Produto
        val produtoCardView = binding.produtoItemCardviewCheckable
        val cardViewFilha = binding.produtoItemCardviewFilha

        init {
            enviarListaParaRemover(produtosParaRemover)
            enviarListaChecked(produtosParaCheckAll)
        }

        private val nome = binding.nomeProduto
        private val quantidade = binding.quantidadeProduto
        private val preco = binding.valorProduto
        private val precoTexto = binding.precoText
        private val quantidadeText = binding.quantidadeText
        private val unidade = binding.unidadeProduto

        fun vincula(produto : Produto) : Map<String, TextView> {
            this.produto = produto
            Log.i("Preco", "Produto: " + produto.idProduto  + "; nome: "+ produto.nome + "; valor: " + produto.valor )
            nome.text = produto.nome
            quantidade.text = produto.quantidade.toString()
            preco.text = produto.valor.toString()
            unidade.text = produto.unidade

            return mapOf(
                "preco" to preco,
                "texto" to precoTexto,
                "unidade" to unidade,
                "qtdeText" to quantidadeText)
        }

        fun atualizaListaChecked(holder: ViewHolder) {
            for (card in 0..produtos.size) {
                if (holder.absoluteAdapterPosition == card) {
                    holder.produtoCardView.isChecked = false
                    produtosParaCheckAll.add(produtoCardView)
                }
            }
        }

        fun selecionarTodos() {
            for (card in 0..produtos.size) {
                produtoCardView.isChecked
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        val produtoMap = holder.vincula(produto)
        val preco = produtoMap["preco"]
        val precoTexto = produtoMap["texto"]
        val unidade = produtoMap["unidade"]
        val qtdeText = produtoMap["qtdeText"]
        val card = holder.produtoCardView
        val cardFilha = holder.cardViewFilha

        if (BigDecimal(preco?.text.toString()) <= BigDecimal.ZERO) {
            preco?.isVisible = false
            precoTexto?.isVisible = false
        } else {
            if (!unidade?.text.isNullOrBlank()) {
                unidade?.text = "/" + produto.unidade
                unidade?.isVisible = true
            }
            preco?.text = formataValorMonetarioBR(produto.valor)
        }

        card.setOnClickListener {
            if (card.isChecked) {
                card.isChecked = false
                produtosParaRemover.remove(produto)
            } else {
                card.isChecked = true
                qtdeText?.layout
                produtosParaRemover.add(produto)
            }
        }
        holder.atualizaListaChecked(holder)
    }

    override fun getItemCount() = produtos.size

    fun atualizarListaProdutos(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }
}
