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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import emmanuelmuturia.commons.uilayer.R
import emmanuelmuturia.components.SnapbiteHeader
import emmanuelmuturia.entities.FoodEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayScreen(navigateBack: () -> Unit, navController: NavHostController) {

    val dayScreenViewModel: DayScreenViewModel = hiltViewModel()

    val foodList by dayScreenViewModel.foodList.collectAsState()

    /*val dayId = navController.currentBackStackEntry?.arguments?.getInt("dayId")

    val day = dayScreenViewModel.getDayById(dayId = dayId)

    var dayDate by rememberSaveable { dayScreenViewModel.dayDate }

    var dayEmoji by rememberSaveable { dayScreenViewModel.dayEmoji }

    var listOfFoods by rememberSaveable { dayScreenViewModel.listOfFoods }

    day.let {

        if (it != null) {

            dayDate = it.dayDate

            dayEmoji = it.dayEmoji

            listOfFoods = it.foodList

        }

    }*/

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.snapbite),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds
        )

        Column {

            SnapbiteHeader(navigateBack = navigateBack, headerTitle = dayScreenViewModel.formatCurrentDate())

            LazyVerticalGrid(columns = GridCells.Fixed(count = 3)) {
                items(items = foodList) { foodEntity ->
                    FoodCard(foodEntity = foodEntity, navController = navController)
                }
            }

        }

    }

    DayScreenFooter(
        context = LocalContext.current,
        navController = navController
    )

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
            if (isGranted) {
                navController.navigate(route = "photoScreen")
            }
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FoodCard(foodEntity: FoodEntity, navController: NavHostController) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .height(height = 70.dp)
                .width(width = 70.dp)
                .clickable { foodEntity.foodId.let {
                    navController.navigate(route = "editFoodScreen")
                } }, shape = RoundedCornerShape(size = 21.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                GlideImage(
                    model = "https://images.unsplash.com/photo-1493770348161-369560ae357d?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    contentDescription = "Food Item",
                    contentScale = ContentScale.Crop
                )

            }

        }

        Spacer(modifier = Modifier.height(height = 10.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = foodEntity.foodName,
                style = MaterialTheme.typography.labelLarge
            )

        }

    }

}


fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}