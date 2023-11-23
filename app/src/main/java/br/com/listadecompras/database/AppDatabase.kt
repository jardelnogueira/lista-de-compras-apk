package br.com.listadecompras.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.listadecompras.database.converter.Converters
import br.com.listadecompras.database.dao.CategoriaProdutoDao
import br.com.listadecompras.database.dao.ProdutoDao
import br.com.listadecompras.database.dao.UnidadeProdutoDao
import br.com.listadecompras.model.CategoriaProduto
import br.com.listadecompras.model.Produto
import br.com.listadecompras.model.UnidadeProduto

@Database(
    entities = [
        Produto::class,
        CategoriaProduto::class,
        UnidadeProduto::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ProdutoDao() : ProdutoDao
    abstract fun CategoriaProdutoDao(): CategoriaProdutoDao
    abstract fun UnidadeProdutoDao(): UnidadeProdutoDao

    companion object {

        @Volatile
        private lateinit var db: AppDatabase

        fun instancia(context: Context) : AppDatabase {
            if (::db.isInitialized) return db
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "appdb"
            ).allowMainThreadQueries()
                .build().also {
                    db = it
                }
        }
    }
}