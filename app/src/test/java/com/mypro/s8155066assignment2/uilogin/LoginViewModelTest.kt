package com.mypro.s8155066assignment2.uilogin

import com.mypro.s8155066assignment2.data.LoginResponse
import com.mypro.s8155066assignment2.data.Repository
import io.mockk.coEvery
import io.mockk.coVerify
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
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: Repository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login with blank fields shows error and does not call repository`() = runTest(testDispatcher) {
        viewModel.login("", "")
        advanceUntilIdle()

        assertEquals("Please enter username and password", viewModel.errorState.value)
        assertNull(viewModel.keypass.value)
        coVerify(exactly = 0) { repository.login(any(), any()) }
    }

    @Test
    fun `login success updates keypass and clears error`() = runTest(testDispatcher) {
        coEvery { repository.login("12345678", "Ann") } returns LoginResponse("movies")

        viewModel.login("12345678", "Ann")
        advanceUntilIdle()

        assertEquals("movies", viewModel.keypass.value)
        assertNull(viewModel.errorState.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    @Test
    fun `login failure shows error and keypass stays null`() = runTest(testDispatcher) {
        coEvery { repository.login(any(), any()) } throws Exception("Network error")

        viewModel.login("12345678", "wrong")
        advanceUntilIdle()

        assertEquals(
            "Login failed. Please check your details and connection.",
            viewModel.errorState.value
        )
        assertNull(viewModel.keypass.value)
        assertEquals(false, viewModel.isLoading.value)
    }
}