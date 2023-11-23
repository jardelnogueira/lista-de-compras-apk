package br.com.listadecompras.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.listadecompras.database.AppDatabase
import br.com.listadecompras.databinding.ActivityFormNovoProdutoSomenteNomeValorBinding
import br.com.listadecompras.model.Produto
import java.math.BigDecimal

class FormularioNovoProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormNovoProdutoSomenteNomeValorBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        AppDatabase.instancia(this).ProdutoDao()
    }

    private var produtoId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        configuraBotaoAdicionar()
        configuraBotaoRemover()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun configuraBotaoSalvar() {
        val salvarProduto = binding.botaoSalvarNovoProduto
        salvarProduto.setOnClickListener {
            val produtoNovo = criarProduto()
            Log.i("Produto", "produto: " + produtoNovo)
            if (verificaPreenchimentoObrigatorios(produtoNovo)) {
                produtoDao.salvarProduto(produtoNovo)
                finish()
            }
        }
    }

    private fun criarProduto(): Produto {
        //val categoria = binding.categoriaProdutoAutocomplete.text.toString()
        val nome = binding.nomeProdutoEditText.text.toString()
        var preco = binding.valorProdutoEditText.text.toString()
        if (preco.isNullOrBlank()) {
            preco = BigDecimal.ZERO.toString()
        }
        val unidade = binding.unidadeProdutoEditText.text.toString()
        //val pesoEmbalagem = binding.pesoEmbalagemProdutoEditText.text.toString()
        val quantidade = binding.quantidadeProdutoEditText.text.toString()


        return Produto(
            nome = nome,
            quantidade = quantidade.toInt(),
            valor = BigDecimal(preco),
            unidade = unidade,
            status = "Compras"
        )
    }

    private fun verificaPreenchimentoObrigatorios(produto: Produto): Boolean {
        if (produto.nome.isNullOrBlank()) {
            binding.nomeProdutoEditText.requestFocus()
            return false
        }
        else if (produto.quantidade.toString().isNullOrBlank()) {
            binding.quantidadeProdutoEditText.requestFocus()
            return false
        }
        else {
            return true
        }
    }

    private fun configuraBotaoAdicionar() {
        val adcBtn = binding.fabAdcQuantidade
        val qtdEditText = binding.quantidadeProdutoEditText
        var quantidade: Int
        adcBtn.setOnClickListener {
            quantidade = qtdEditText.text.toString().toInt()
            quantidade++
            qtdEditText.setText(quantidade.toString())
        }
    }

    private fun configuraBotaoRemover() {
        val adcRmv = binding.fabRmvQuantidade
        val qtdEditText = binding.quantidadeProdutoEditText
        var quantidade: Int
        adcRmv.setOnClickListener {
            quantidade = qtdEditText.text.toString().toInt()
            quantidade--
            if (quantidade >= 1) qtdEditText.setText(quantidade.toString()) else quantidade = 1
        }
    }
}