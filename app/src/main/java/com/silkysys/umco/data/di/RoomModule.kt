package com.silkysys.umco.data.di

import android.app.Application
import androidx.room.Room
import com.silkysys.umco.data.local.UserDatabase
import com.silkysys.umco.data.local.WishlistDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, UserDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideWishlistDatabase(app: Application) =
        Room.databaseBuilder(app, WishlistDatabase::class.java, "wishlist_db")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun provideDao(db: UserDatabase) = db.getUserDao()

    @Provides
    fun provideWishlistDao(db: WishlistDatabase) = db.getWishlistDao()
}