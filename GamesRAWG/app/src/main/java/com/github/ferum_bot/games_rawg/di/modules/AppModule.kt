package com.github.ferum_bot.games_rawg.di.modules

import com.github.ferum_bot.games_rawg.core.util.implementations.AndroidResourceProvider
import com.github.ferum_bot.games_rawg.core.util.interfaces.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:29
 * Project: Games-RAWG
 */
@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindResourceProvider(provider: AndroidResourceProvider): ResourceProvider

}