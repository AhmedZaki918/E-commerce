package com.silkysys.umco.data.local

object Constants {
    // Base url
    const val BASE_URL = "http://jumla-stage.umcogroup.com"

    // Lost connection case
    const val CHECK_CONNECTION = "Please check your internet connection"

    // Shared Preferences
    const val MyPREFERENCES = "MyPrefs"
    const val USER_TOKEN = "USER_TOKEN"

    // User table for room
    const val TABLE = "User"
    const val WISHLIST_TABLE = "Wishlist"
    const val PRODUCT_ONLY = "products_only"

    // Intent keys
    const val OPEN_FRAGMENT = "fragment"
    const val CART = "cart"
    const val EXPLORE = "explore"
    const val PRODUCT_AND_DESC = "products_and_description"
    const val PRODUCT_DETAILS = "Product_Details"
    const val EDIT_ADDRESS = "EDIT_ADDRESS"
    const val SAVE_ADDRESS_RESPONSE = "SAVE_RESPONSE"
    const val PLACE_ORDER = "PLACE_ORDER"
    const val GET_ORDER = "GET_ORDER"
    const val CATEGORY_ID = "CATEGORY_ID"

    // Login screen
    const val NEW_ACCOUNT = "https://pos.silkysystems.com/reg/"
    const val REQUIRED_FIELD = "Required field"
    const val INVALID_EMAIL = "Invalid email address"

    // Checkout screen
    const val MISSING_FIELD = "Enter missing fields"
    const val ADDRESS_1 = "ADDRESS_1"
    const val CITY = "CITY"
    const val COUNTRY = "CO"
    const val COUNTRY_NAME = "Country_Name"
    const val PHONE = "PHONE"
    const val POST_CODE = "POST_CODE"
    const val STATE = "STATE"

    // Cart screen
    const val MINIMUM_QTY = 1
    const val ADD_ONE = 1
    const val REMOVE_ONE = -1

    // Order screen
    const val PAYPAL = "paypal_smart_button"
    const val PAYPAL_STANDARD = "paypal_standard"

    const val ADD = "ADD"
    const val REMOVE = "REMOVE"
    const val ITEM_ADDED = "Item was added"
    const val ITEM_REMOVED = "Item removed"

    // Dummy data for test only
    const val SATURDAY = "Saturday"
    const val SUNDAY = "Sunday"
    const val MONDAY = "Monday"
    const val TUESDAY = "Tuesday"
    const val WEDNESDAY = "wednesday"
    const val THURSDAY = "Thursday"
    const val FRIDAY = "Friday"
    const val TEN_AM = "10:00 AM - 12:00 PM"
    const val TWELVE_PM = "12:00 PM - 02:00 PM"
    const val TWO_PM = "02:00 PM - 04:00 PM"
    const val FOUR_PM = "04:00 PM - 06:00 PM"
}