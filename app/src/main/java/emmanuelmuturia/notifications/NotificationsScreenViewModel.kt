package emmanuelmuturia.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.notifications.entity.NotificationEntity
import emmanuelmuturia.notifications.repository.NotificationRepository
import emmanuelmuturia.commons.SnapbiteState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotificationsScreenViewModel @Inject constructor(
    private val notificationRepository: emmanuelmuturia.notifications.repository.NotificationRepository,
    application: Application
) : AndroidViewModel(application = application) {

    private var _notificationsList = MutableStateFlow<List<emmanuelmuturia.notifications.entity.NotificationEntity>>(value = emptyList())
    val notificationsList: StateFlow<List<emmanuelmuturia.notifications.entity.NotificationEntity>> = _notificationsList.asStateFlow()

    private var _notificationsState =
        MutableStateFlow<emmanuelmuturia.commons.SnapbiteState<Any>>(value = emmanuelmuturia.commons.SnapbiteState.Loading)
    val notificationsState: StateFlow<emmanuelmuturia.commons.SnapbiteState<Any>> = _notificationsState.asStateFlow()

    private var _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getAllNotifications()
    }

    fun getAllNotifications() {
        viewModelScope.launch {

            _notificationsState.update { emmanuelmuturia.commons.SnapbiteState.Loading }

            try {
                _notificationsList.value = notificationRepository.getAllNotifications()
                _notificationsState.update { emmanuelmuturia.commons.SnapbiteState.Success(data = _notificationsList.value) }
            } catch (e: Exception) {
                _notificationsState.update { emmanuelmuturia.commons.SnapbiteState.Error(error = e) }
            }

        }
    }


    fun refreshNotifications() {
        viewModelScope.launch {
            _isLoading.value = true
            notificationRepository.getAllNotifications()
            delay(timeMillis = 1400L)
            _isLoading.value = false
        }
    }

    fun deleteNotification(notificationEntity: emmanuelmuturia.notifications.entity.NotificationEntity) {
        viewModelScope.launch {
            try {
                notificationRepository.deleteNotification(notificationEntity = notificationEntity)
                getAllNotifications()
            } catch (e: Exception) {
                Timber.tag(tag = "Notification Deletion Exception")
                    .e(
                        message = "Could not delete the notification due to: %s",
                        e.printStackTrace()
                    )
            }
        }
    }

}