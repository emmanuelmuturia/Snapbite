package emmanuelmuturia.day

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import emmanuelmuturia.theme.Caveat
import emmanuelmuturia.uilayer.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.snapbite),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds
        )

        DayScreenHeader(navController = navController)

    }

    DayScreenFooter(navController = navController, context = LocalContext.current)

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayScreenHeader(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 42.dp, start = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                modifier = Modifier.clickable { navController.popBackStack() },
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back Arrow",
                tint = Color.Black
            )

        }

        Spacer(modifier = Modifier.height(height = 21.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = formatCurrentDate(),
                fontFamily = Caveat,
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun DayScreenFooter(navController: NavHostController, context: Context) {

    val dayScreenViewModel: DayScreenViewModel = hiltViewModel()
    val cameraPermissionQueue = dayScreenViewModel.visiblePermissionDialogQueue

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            dayScreenViewModel.onPermissionResult(
                permission = Manifest.permission.CAMERA,
                isGranted = isGranted
            )
        }
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 21.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
                .size(size = 49.dp)
                .clickable {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    navController.navigate(route = "photoScreen")
                },
            painter = painterResource(id = R.drawable.camera),
            contentDescription = "Camera Button"
        )

    }
    cameraPermissionQueue.reversed().forEach { permission ->
        PermissionDialog(
            permissionTextProvider = when (permission) {
                Manifest.permission.CAMERA -> CameraPermissionTextProvider()
                else -> return@forEach
            },
            isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                LocalContext.current as Activity, permission
            ),
            onDismiss = dayScreenViewModel::dismissDialog,
            onOkClick = { dayScreenViewModel.dismissDialog() },
            onGoToAppSettingsClick = { (context as Activity).openAppSettings() }

        )
    }
}


fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatCurrentDate(): String {
    val currentDate = LocalDate.now()
    val dayOfMonth = currentDate.dayOfMonth
    val month =
        currentDate.month.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH)
    val year = currentDate.year

    val daySuffix = when (dayOfMonth) {
        1, 21, 31 -> "st"
        2, 22 -> "nd"
        3, 23 -> "rd"
        else -> "th"
    }

    return "$dayOfMonth$daySuffix $month $year"
}