package ru.gressor.movies_browser.repo.stub

import ru.gressor.movies_browser.entity.Actor
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.repo.MoviesRepo

class StubMoviesRepo : MoviesRepo {
    private val movies = listOf(
        Movie(
            "Avengers: End Game", "Action, Adventure, Drama", "pic_avengers_the_end",
            false,"13+", 125, 4.5f, 137,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(
                Actor("Robert Downey Jr.", "photo_robert_d", true),
                Actor("Chris Evans", "photo_chris_e", true),
                Actor("Mark Ruffalo", "photo_mark_r", false),
                Actor("Chris Hemsworth", "photo_chris_h", false)
            )
        ),

        Movie(
            "Tenet", "Action, Sci-Fi, Thriller", "pic_tenet",
            true,"16+", 98, 5f, 97,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(
                Actor("Chris Evans", "photo_chris_e", true),
                Actor("Mark Ruffalo", "photo_mark_r", false),
                Actor("Chris Hemsworth", "photo_chris_h", false)
            )
        ),

        Movie(
            "Black Widow", "Action, Adventure, Sci-Fi", "pic_back_widow",
            false,"13+", 38, 4f, 102,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(
                Actor("Mark Ruffalo", "photo_mark_r", false),
                Actor("Chris Hemsworth", "photo_chris_h", false)
            )
        ),

        Movie(
            "Wonder Woman 1984", "Action, Adventure, Fantasy", "pic_wonder_woman_1984",
            false,"13+", 74, 5f, 120,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(
                Actor("Chris Hemsworth", "photo_chris_h", false)
            )
        ),

        Movie(
            "Avengers 2020", "Action, Adventure, Drama", "pic_avengers_the_end",
            false,"13+", 125, 4.5f, 137,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(
                Actor("Robert Downey Jr.", "photo_robert_d", true),
                Actor("Chris Evans", "photo_chris_e", true),
                Actor("Mark Ruffalo", "photo_mark_r", false),
                Actor("Chris Hemsworth", "photo_chris_h", false)
            )
        ),

        Movie(
            "Tenet 2020", "Action, Sci-Fi, Thriller", "pic_tenet",
            true,"16+", 98, 5f, 97,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(
                Actor("Robert Downey Jr.", "photo_robert_d", true),
                Actor("Chris Evans", "photo_chris_e", true),
                Actor("Mark Ruffalo", "photo_mark_r", false),
                Actor("Chris Hemsworth", "photo_chris_h", false)
            )
        )
    )

    override fun getMovies(): List<Movie> = movies
}