package org.lsposed.mhsmpatch.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nkbe.util.MHSMPackageManager
import nkbe.util.MHSMPackageManager.AppInfo

class SelectAppsViewModel : ViewModel() {

    companion object {
        private const val TAG = "SelectAppViewModel"
    }

    init {
        Log.d(TAG, "SelectAppsViewModel ${toString().substringAfterLast('@')} construct")
    }

    var isRefreshing by mutableStateOf(false)
        private set

    var filteredList by mutableStateOf(listOf<AppInfo>())
        private set

    val multiSelected = mutableStateListOf<AppInfo>()

    fun filterAppList(refresh: Boolean, filter: (AppInfo) -> Boolean) {
        viewModelScope.launch {
            if (MHSMPackageManager.appList.isEmpty() || refresh) {
                isRefreshing = true
                MHSMPackageManager.fetchAppList()
                isRefreshing = false
            }
            filteredList = MHSMPackageManager.appList.filter(filter)
            Log.d(TAG, "Filtered ${filteredList.size} apps")
        }
    }
}
