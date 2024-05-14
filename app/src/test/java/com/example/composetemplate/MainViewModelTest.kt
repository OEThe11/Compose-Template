package com.example.composetemplate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composetemplate.data.Resource
import com.example.composetemplate.database.StandardEntity
import com.example.composetemplate.models.GetStandardJSONResponseItem
import com.example.composetemplate.repository.StandardRepository
import com.example.composetemplate.screens.main.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<StandardRepository>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getStandardList emits correct states`() = runTest {
        //Arrange
        val fakeData = listOf(
            StandardEntity(7, "Y2K Chris Jericho", 100, true),
            StandardEntity(7, "John Cena", 101, false),
            StandardEntity(7, "RVD Rob Van Dam", 102, true),
            StandardEntity(7, "King Booker T", 103, true),
            StandardEntity(7, "Randy Orton", 104, false)
        )

        val resourceSuccess = Resource.Success(fakeData)
        coEvery { repository.standardInfo() } returns resourceSuccess

        //Act
        viewModel.getStandardList()
        val result = viewModel.standardStatus.first()

        //Assert
        assertEquals(resourceSuccess, result)
    }

}