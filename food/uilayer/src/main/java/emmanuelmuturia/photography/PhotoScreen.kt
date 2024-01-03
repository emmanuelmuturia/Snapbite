package emmanuelmuturia.photography

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import emmanuelmuturia.day.DayScreenViewModel
import emmanuelmuturia.uilayer.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoScreen(navController: NavHostController, dayScreenViewModel: DayScreenViewModel) {

    val context = LocalContext.current

    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }

    val scaffoldState = rememberBottomSheetScaffoldState()

    val bitmaps by dayScreenViewModel.bitmaps.collectAsState()

    BottomSheetScaffold(sheetPeekHeight = 0.dp, scaffoldState = scaffoldState, sheetContent = {
        PhotoBottomSheetContent(bitmaps = bitmaps)
    }) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding)
        ) {

            PhotoPreview(controller = controller, modifier = Modifier.fillMaxSize())

            PhotoScreenHeader(
                navController = navController
            )

        }

    }

    PhotoScreenFooter(
        scaffoldState = scaffoldState,
        context = context,
        controller = controller,
        dayScreenViewModel = dayScreenViewModel
    )

}


@Composable
fun PhotoPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {

    val lifeCycleOwner = LocalLifecycleOwner.current

    AndroidView(factory = {
        PreviewView(
            it
        ).apply {
            this.controller = controller
            controller.bindToLifecycle(lifeCycleOwner)
        }
    }, modifier = modifier)

}


@Composable
fun PhotoScreenHeader(
    navController: NavHostController
) {

    val dayScreenViewModel: DayScreenViewModel = hiltViewModel()

    val bitmaps by dayScreenViewModel.bitmaps.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp, start = 14.dp, end = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        Icon(
            imageVector = Icons.Rounded.Check,
            contentDescription = "OK",
            tint = Color.Black,
            modifier = Modifier
                .clickable {
                    navController.navigate(route = "createFoodScreen")
                    Log.d("Food Images List", "These are the food images: $bitmaps")
                }
                .size(size = 49.dp)
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoScreenFooter(
    scaffoldState: BottomSheetScaffoldState,
    context: Context,
    controller: LifecycleCameraController,
    dayScreenViewModel: DayScreenViewModel
) {

    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 21.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            modifier = Modifier
                .size(size = 49.dp)
                .clickable {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                },
            painter = painterResource(id = R.drawable.gallery),
            contentDescription = "Gallery Button"
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        Image(
            modifier = Modifier
                .size(size = 49.dp)
                .clickable {
                    takePhoto(
                        controller = controller,
                        onPhotoTaken = dayScreenViewModel::onTakePhoto,
                        context = context
                    )
                },
            painter = painterResource(id = R.drawable.shutter),
            contentDescription = "Shutter Button"
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        Image(
            modifier = Modifier
                .size(size = 49.dp)
                .clickable {
                    controller.cameraSelector =
                        if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                            CameraSelector.DEFAULT_FRONT_CAMERA
                        } else CameraSelector.DEFAULT_BACK_CAMERA
                },
            painter = painterResource(id = R.drawable.flip),
            contentDescription = "Flip Button"
        )
    }
}

@Composable
fun PhotoBottomSheetContent(
    bitmaps: List<Bitmap>,
    modifier: Modifier = Modifier
) {

    if (bitmaps.isEmpty()) {
        Box(modifier = Modifier.padding(all = 14.dp), contentAlignment = Alignment.Center) {
            Text(text = "You have not photos yet...")
        }
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(space = 14.dp),
            verticalItemSpacing = 14.dp,
            contentPadding = PaddingValues(all = 14.dp),
            modifier = modifier
        ) {

            items(items = bitmaps) { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Captured Images",
                    modifier = Modifier.clip(shape = RoundedCornerShape(size = 10.dp))
                )
            }

        }
    }

}

private fun takePhoto(
    controller: LifecycleCameraController,
    onPhotoTaken: (Bitmap) -> Unit,
    context: Context
) {

    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                    postScale(-1f, 1f)
                }

                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )

                onPhotoTaken(rotatedBitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Photo", "Photo cannot be taken because of: ${exception.message}")
            }
        }
    )

}