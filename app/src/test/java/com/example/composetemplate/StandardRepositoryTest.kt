package com.example.composetemplate

import com.example.composetemplate.data.Resource
import com.example.composetemplate.database.StandardDao
import com.example.composetemplate.database.StandardEntity
import com.example.composetemplate.mapping.StandardMapper
import com.example.composetemplate.models.GetStandardJSONResponseItem
import com.example.composetemplate.network.StandardApi
import com.example.composetemplate.repository.StandardRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class StandardRepositoryTest {

    private lateinit var standardRepository: StandardRepository
    private lateinit var standardApi : StandardApi
    private lateinit var standardDao : StandardDao


    @Before
    fun setUp() {
        standardApi = mockk()
        standardDao = mockk(relaxed = true)
        standardRepository = StandardRepository(standardApi, standardDao)
    }

    @Test
     fun `standardInfo returns success when API and DB operations are successful`() = runTest{
        val fakeStandardList = listOf(
            GetStandardJSONResponseItem(7, "Y2K Chris Jericho", 100, true),
            GetStandardJSONResponseItem(7, "John Cena", 101, false),
            GetStandardJSONResponseItem(7, "RVD Rob Van Dam", 102, true),
            GetStandardJSONResponseItem(7, "King Booker T", 103, true),
            GetStandardJSONResponseItem(7, "Randy Orton", 104, false)
        )
        val expectedStandardEntities = fakeStandardList.map { StandardMapper.buildFrom(it) }
        val response = Response.success(fakeStandardList)
       coEvery { standardApi.getAllStandardList() } returns response
        coEvery { standardDao.insertAll(*anyVararg()) } just Runs

        //Act
        val result = standardRepository.standardInfo()

        //Assert
        assertTrue(result is Resource.Success)
        assertEquals(expectedStandardEntities, (result as Resource.Success).data)
        coVerify{ standardDao.insertAll(*anyVararg()) }
    }

    @Test
    fun `standardInfo returns error when API call is unsuccessful`() = runTest {
        //Arrange
        val errorCode = 404
        val errorMessage = "Not Found"
        val responseError = Response.error<List<GetStandardJSONResponseItem>>(
            errorCode,
            okhttp3.ResponseBody.create(null, errorMessage))
        coEvery { standardApi.getAllStandardList() } returns responseError

        //Act
        val result = standardRepository.standardInfo()

        //Assert
        assertTrue(result is Resource.Error)
        assertEquals("API Error: $errorMessage", (result as Resource.Error).message)

        }

    @Test
    fun `standardInfo handles exceptions gracefully`() = runTest {
        //Arrange
        coEvery { standardApi.getAllStandardList() } throws Exception("Network Error")

        //Act
        val result = standardRepository.standardInfo()

        //Assert
        assertTrue(result is Resource.Error)
        assertEquals("Exception: Something is up", (result as Resource.Error).message)
    }

}