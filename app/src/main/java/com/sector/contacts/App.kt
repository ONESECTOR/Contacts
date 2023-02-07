package com.sector.contacts

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.exceptions.PlutoExceptions
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.rooms.db.PlutoRoomsDBWatcher
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin
import com.sector.contacts.di.provider.DatabaseProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import kotlin.system.exitProcess

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Pluto.Installer(this)
            .addPlugin(PlutoRoomsDatabasePlugin("rooms-db"))
            .addPlugin(PlutoExceptionsPlugin("exceptions"))
            .install()
        Pluto.showNotch(true)

        watchRoomsDatabase()
        setExceptionListener()
    }

    private fun watchRoomsDatabase() {
        PlutoRoomsDBWatcher.watch(DatabaseProvider.DATABASE_NAME, DatabaseProvider::class.java)
    }

    private fun setExceptionListener() {
        PlutoExceptions.setExceptionHandler { thread, throwable ->
            Timber.tag("exception_demo")
                .e(throwable, "uncaught exception handled on thread: ${thread.name}")
            exitProcess(0)
        }

        PlutoExceptions.setANRHandler { thread, exception ->
            Timber.tag("anr_demo").e(exception, "unhandled ANR handled on thread: ${thread.name}")
        }
    }
}