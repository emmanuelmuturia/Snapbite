/*
actual class AppModule {

    actual val foodDataSource: FoodDataSource by lazy {
        SqlDelightFoodDataSource(
            foodDatabase = FoodDatabase(
                driver = DatabaseDriverFactory().create()
            ),
            imageStorage = ImageStorage()
        )
    }

}*/
