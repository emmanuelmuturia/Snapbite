package snapbite.app.notifications.ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import snapbite.app.commons.SnapbiteState
import snapbite.app.notifications.domain.Notification
import snapbite.app.notifications.domain.NotificationRepository

class NotificationsScreenViewModel(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    private var _notificationsList = MutableStateFlow<List<Notification>>(value = emptyList())
    val notificationsList: StateFlow<List<Notification>> = _notificationsList.asStateFlow()

    private var _notificationsState =
        MutableStateFlow<SnapbiteState<Any>>(value = SnapbiteState.Loading)
    val notificationsState: StateFlow<SnapbiteState<Any>> = _notificationsState.asStateFlow()

    init {
        getAllNotifications()
    }

    private fun getAllNotifications() {
        viewModelScope.launch {

            _notificationsState.update { SnapbiteState.Loading }

            try {
                notificationRepository.getAllNotifications().collectLatest {
                    _notificationsList.value = it
                    _notificationsState.value = SnapbiteState.Success(data = _notificationsList.value)
                }
            } catch (e: Exception) {
                _notificationsState.update { SnapbiteState.Error(error = e) }
            }

        }
    }

    fun deleteNotification(notification: Notification) {
        viewModelScope.launch {
            notificationRepository.deleteNotification(notification = notification)
            getAllNotifications()
        }
    }

}
