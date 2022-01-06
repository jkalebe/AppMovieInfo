package br.com.example.appmovieinfo

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.example.appmovieinfo.repository.AppDatabase
import br.com.example.appmovieinfo.repository.MovieDB
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = AppDatabase.getDatabase(appContext)
        val dao = db.getMovieDao()
        runBlocking {
            val movieUnderTest = MovieDB(
                "myId",
                "Spider Man",
                "2002",
                "2002-02-02",
                "160 min",
                "action, science",
                "Carinha01",
                "Carinha02",
                "actor1, actor2, actor3",
                "Isso é um teste",
                "English",
                "States United",
                "Sei lá",
                "http:minhaimagem.com.br",
                "movie",
                "250000$"
            )

            var rowId = dao.save(movieUnderTest)
            assertTrue(rowId > -1)

            val movie = dao.allFavorites().first()
            movie.forEach {
                assertEquals(it.Title, "Spider Man")
            }

            val isFavorite = dao.isFavorite("myId")
            assertTrue(isFavorite == 1)

            val result = dao.delete(movieUnderTest)
            assertTrue(result == 1)
        }
    }
}