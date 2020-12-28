package com.github.ferum_bot.start_app.ui.fragments.main_list

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ferum_bot.start_app.R
import com.github.ferum_bot.start_app.core.models.HorizontalListItem
import com.github.ferum_bot.start_app.core.models.HorizontalRecyclerItem
import com.github.ferum_bot.start_app.core.states.ActivitiesLoadingStates
import kotlinx.coroutines.*
import java.util.*
import kotlin.Comparator

/**
 * Created by Matvey Popov.
 * Date: 27.12.2020
 * Time: 21:02
 * Project: StartApp
 */
class MainListViewModel(): ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val loadedActivities: MutableLiveData<List<ResolveInfo>> = MutableLiveData(listOf())
    private val sortedFirstLetters: MutableLiveData<List<String>> = MutableLiveData(listOf())

    private val _listOfHorizontalRecyclerItem: MutableLiveData<List<HorizontalRecyclerItem>> = MutableLiveData()
    val listOfHorizontalRecyclerItem: LiveData<List<HorizontalRecyclerItem>>
    get() = _listOfHorizontalRecyclerItem

    private val _status: MutableLiveData<ActivitiesLoadingStates> = MutableLiveData(
        ActivitiesLoadingStates.NOT_ACTIVE)
    val status: LiveData<ActivitiesLoadingStates>
    get() = _status

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
    get() = _errorMessage

    fun getAllActivities(packageManager: PackageManager) {
        _status.postValue(ActivitiesLoadingStates.LOADING)
        var activities = loadActivities(packageManager)

        activities = activities.sortedWith(Comparator { a, b ->
            String.CASE_INSENSITIVE_ORDER.compare(
                    a.loadLabel(packageManager).toString().toUpperCase(Locale.ROOT),
                    b.loadLabel(packageManager).toString().toUpperCase(Locale.ROOT)
            )
        }) as MutableList<ResolveInfo>
        loadedActivities.value = activities

        getSortedFirstLetters(packageManager)
        getResultList(packageManager)
        _status.postValue(ActivitiesLoadingStates.DONE)

    }

    fun getPackageName(id: Int): String {
        return loadedActivities.value!![id].activityInfo.applicationInfo.packageName
    }

    fun getName(id: Int): String {
        return loadedActivities.value!![id].activityInfo.name
    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
    }

    private fun getResultList(packageManager: PackageManager) {
        val result = mutableListOf<HorizontalRecyclerItem>()
        var cnt = 0
        val activitiesLabel = getAllActivitiesLabel(packageManager)
        val currentListOfItems = mutableListOf<HorizontalListItem>()
        for ((index, el) in activitiesLabel.withIndex()) {
            val firstChar = el[0]
            if (firstChar.toString().toUpperCase(Locale.ROOT) != sortedFirstLetters.value!![cnt]) {
                val currentResult = HorizontalRecyclerItem(makeFirstLetter(sortedFirstLetters.value!![cnt]), currentListOfItems.toList())
                result.add(currentResult)
                currentListOfItems.clear()
                cnt++
            }
            currentListOfItems.add(HorizontalListItem(index, el, loadedActivities.value!![index].loadIcon(packageManager)))
        }
        if (currentListOfItems.isNotEmpty()) {
            val currentResult = HorizontalRecyclerItem(makeFirstLetter(sortedFirstLetters.value!![cnt]), currentListOfItems.toList())
            result.add(currentResult)
        }
        _listOfHorizontalRecyclerItem.value = result
    }

    private fun makeFirstLetter(letter: String): String {
        return "Letter: $letter"
    }

    private fun getSortedFirstLetters(packageManager: PackageManager) {
        val activitiesLabel = getAllActivitiesLabel(packageManager)
        if (activitiesLabel.isEmpty()) {
            throw IllegalArgumentException("Absent of activities")
        }
        val result = mutableListOf<String>(activitiesLabel[0][0].toString().toUpperCase(Locale.ROOT))
        val size = activitiesLabel.size
        for (i in 1 until size) {
            if (result.last() == activitiesLabel[i][0].toString().toUpperCase(Locale.ROOT)) {
                continue
            }
            result.add(activitiesLabel[i][0].toString().toUpperCase(Locale.ROOT))
        }
        sortedFirstLetters.value = (result)
    }

    private fun getAllActivitiesLabel(packageManager: PackageManager): List<String> {
        val result = mutableListOf<String>()
        if (loadedActivities.value == null) {
            return result
        }
        for (el in loadedActivities.value!!) {
            result.add(el.loadLabel(packageManager).toString())
        }
        return result
    }

    private fun loadActivities(packageManager: PackageManager): MutableList<ResolveInfo> {
        val startUpIntent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        return packageManager.queryIntentActivities(startUpIntent, 0)
    }

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }
}