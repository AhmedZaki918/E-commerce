package com.silkysys.umco.data.local

import androidx.room.TypeConverter
import com.silkysys.umco.data.model.products.byCategory.BaseImage

class Converters {
    // Two converter methods for BaseImage class
    @TypeConverter
    fun fromSource(baseImage: BaseImage): String {
        return baseImage.original_image_url
    }

    @TypeConverter
    fun toSource(name: String): BaseImage {
        return BaseImage(name, name)
    }
}