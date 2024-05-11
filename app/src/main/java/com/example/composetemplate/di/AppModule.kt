package com.example.composetemplate.di

import android.content.Context
import androidx.room.Room
import com.example.composetemplate.Constants.BASE_URL
import com.example.composetemplate.database.StandardDao
import com.example.composetemplate.database.StandardDatabase
import com.example.composetemplate.network.StandardApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideStandardDao(standardDatabase: StandardDatabase): StandardDao =
        standardDatabase.standardDao()


    @Provides
    @Singleton
    fun provideStandardDatabase(@ApplicationContext context: Context): StandardDatabase =
        Room.databaseBuilder(
            context,
            StandardDatabase::class.java,
            "standardInfo"
        )
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun providesStandardApi(): StandardApi {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(StandardApi::class.java)


    }

}