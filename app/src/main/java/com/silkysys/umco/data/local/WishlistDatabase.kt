package com.silkysys.umco.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.silkysys.umco.data.model.products.byCategory.DataItem

@Database(
    entities = [DataItem::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WishlistDatabase : RoomDatabase() {
    abstract fun getWishlistDao(): WishlistDao
}