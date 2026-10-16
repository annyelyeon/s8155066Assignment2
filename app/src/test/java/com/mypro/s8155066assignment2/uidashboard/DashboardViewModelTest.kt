package com.mypro.s8155066assignment2.uidashboard

import com.mypro.s8155066assignment2.data.DashboardResponse
import com.mypro.s8155066assignment2.data.Entity
import com.mypro.s8155066assignment2.data.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var repository: Repository
    private val testDispatcher = StandardTestDispatcher()

    private val movie = mapOf(
        "title" to "The Godfather",
        "director" to "Francis Ford Coppola",
        "genre" to "Crime Drama",
        "releaseYear" to 1972,
        "description" to "An epic crime film"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadMovies success updates movies list`() = runTest(testDispatcher) {
        coEvery { repository.getDashboard("movies") } returns DashboardResponse(
            entities = listOf(movie, movie + ("title" to "Pulp Fiction")),
            entityTotal = 2
        )

        viewModel.loadMovies("movies")
        advanceUntilIdle()

        assertEquals(2, viewModel.movies.value.size)
        assertEquals("The Godfather", viewModel.movies.value[0]["title"])
        assertEquals("Pulp Fiction", viewModel.movies.value[1]["title"])
        assertNull(viewModel.errorState.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    @Test
    fun `loadMovies works with a different entity type`() = runTest(testDispatcher) {
        val photo: Entity = mapOf(
            "technique" to "Long Exposure",
            "equipment" to "Tripod",
            "description" to "Shutter left open"
        )
        coEvery { repository.getDashboard("photography") } returns DashboardResponse(
            entities = listOf(photo),
            entityTotal = 1
        )

        viewModel.loadMovies("photography")
        advanceUntilIdle()

        assertEquals(1, viewModel.movies.value.size)
        assertEquals("Long Exposure", viewModel.movies.value[0]["technique"])
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun `loadMovies failure shows error and list stays empty`() = runTest(testDispatcher) {
        coEvery { repository.getDashboard(any()) } throws Exception("Network error")

        viewModel.loadMovies("movies")
        advanceUntilIdle()

        assertEquals(
            "Failed to load movies. Please check your connection.",
            viewModel.errorState.value
        )
        assertEquals(0, viewModel.movies.value.size)
        assertEquals(false, viewModel.isLoading.value)
    }
}