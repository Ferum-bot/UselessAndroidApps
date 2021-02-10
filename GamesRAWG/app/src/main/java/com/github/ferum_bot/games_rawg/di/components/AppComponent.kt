package com.github.ferum_bot.games_rawg.di.components

import android.content.Context
import com.github.ferum_bot.games_rawg.core.util.interfaces.ResourceProvider
import com.github.ferum_bot.games_rawg.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 22:29
 * Project: Games-RAWG
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getResources(): ResourceProvider

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }

}