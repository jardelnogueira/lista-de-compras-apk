package br.com.listadecompras.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.listadecompras.R
import br.com.listadecompras.database.AppDatabase
import br.com.listadecompras.databinding.ActivityListaDeComprasBinding
import br.com.listadecompras.model.Produto
import br.com.listadecompras.ui.recyclerview.adapter.ListaDeComprasAdapter
import com.google.android.material.card.MaterialCardView

class ListaDeComprasActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaDeComprasBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        AppDatabase.instancia(this).ProdutoDao()
    }

    private val adapter = ListaDeComprasAdapter(context = this)

    private var listaParaRemover = emptyList<Produto>().toMutableList()

    private var selecionados = emptyList<MaterialCardView>()

    private var contador = 0



    private fun salvarVariosProdutos() {
        for (i in 1..5) {
            produtoDao.salvarProduto(
                Produto(
                    nome = "Teste" + i.toString(),
                    quantidade = i,
                    unidade = "",
                    status = "Compras"
                )
            )
        }
    }

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Lista de Compras"
        setContentView(binding.root)
        //salvarNovoProduto()
        //salvarVariosProdutos()

    }

    override fun onResume() {
        super.onResume()
        configRecyclerView()
        atualizaListaProdutos()
        contador = 0
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_novo_produto_lista_compras, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_botao_remover_produto -> {
                recebeListaParaRemover(listaParaRemover)
                if (contador%2 != 0) recebeListaParaRemover(produtoDao.buscarTodosProdutos())
                contador = 0
            }
            R.id.menu_botao_novo_produto -> {
                formNovoProduto()
            }
            R.id.menu_botao_selecionar_todos -> {
                contador++
                if (contador%2 != 0) {
                    for (card in selecionados) {
                        card.isChecked = true
                        listaParaRemover
                    }
                } else {
                    for (card in selecionados) {
                        card.isChecked = false
                        listaParaRemover.clear()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun configRecyclerView() {
        val recyclerview = binding.recyclerviewActivityListaDeCompras
        val itemsCount = recyclerview.childCount
        recyclerview.adapter = adapter

        adapter.enviarListaParaRemover = {
            listaParaRemover = it
        }

        adapter.enviarListaChecked = {
            selecionados = it
        }


    }

    private fun atualizaListaProdutos() {
        return adapter.atualizarListaProdutos(produtoDao.buscarTodosProdutos())
    }

    private fun formNovoProduto() {
        val intent = Intent(this, FormularioNovoProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun recebeListaParaRemover(listaParaRemover: List<Produto>) {
        produtoDao.removerVariosProdutos(listaParaRemover)
        atualizaListaProdutos()
    }

    private fun checkSelecionados(selecionados: List<MaterialCardView>) {
        for (card in selecionados) {
            card.isChecked = true
        }
    }
}