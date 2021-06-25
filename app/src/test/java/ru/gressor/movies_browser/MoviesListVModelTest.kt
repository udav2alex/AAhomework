package ru.gressor.movies_browser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.repo.MoviesRepo
import ru.gressor.movies_browser.viewmodel.MoviesListVModel

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class MoviesListVModelTest {

    private lateinit var vModel: MoviesListVModel

    @Mock
    private lateinit var repo: MoviesRepo

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        vModel = MoviesListVModel(repo)
    }

    @Test
    fun coroutines_ReturnValueIsNotNull() {
        testCoroutineRule.runBlockingTest {
            val liveData = vModel.moviesList
            val observer = Observer<List<Movie>> {}

            `when`(repo.getMoviesNowPlaying()).thenReturn(listOf())

            try {
                liveData.observeForever(observer)
                vModel.loadMoviesList()

                assertNotNull(liveData.value)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }
}