package emmanuelmuturia.home

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import emmanuelmuturia.components.SnapbiteBackgroundImage
import emmanuelmuturia.home.uilayer.BuildConfig
import emmanuelmuturia.theme.snapbiteOrange

@Composable
fun WelcomeScreen(navigateToHomeScreen: () -> Unit) {

    val exitDialogState = rememberSaveable { mutableStateOf(value = false) }

    val context = LocalContext.current

    BackHandler(enabled = true) { exitDialogState.value = !exitDialogState.value }

    if (exitDialogState.value) {
        ExitConfirmationDialog(
            onConfirmExit = {
                (context as? ComponentActivity)?.finish()
            },
            onDismiss = {
                exitDialogState.value = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        WelcomeScreenBottomSheet(navigateToHomeScreen = navigateToHomeScreen)

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WelcomeScreenBottomSheet(navigateToHomeScreen: () -> Unit) {

    var isChecked by rememberSaveable {
        mutableStateOf(value = false)
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 420.dp,
        sheetDragHandle = {
            BottomSheetDefaults.DragHandle(
                color = Color.White,
                height = 7.dp
            )
        },
        sheetTonalElevation = 21.dp,
        containerColor = Color.Transparent,
        sheetContainerColor = snapbiteOrange,
        sheetShape = RoundedCornerShape(size = 49.dp),
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(height = 10.dp))

                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 35.sp
                )

                Spacer(modifier = Modifier.height(height = 10.dp))

                Text(
                    modifier = Modifier.padding(all = 10.dp),
                    text = "Welcome to Snapbite! Feel free to look around in order to get access to your preferred resource(s)…",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(height = 10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 3.dp, end = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        modifier = Modifier.testTag(tag = "welcomeScreenCheckBox"),
                        checked = isChecked, onCheckedChange = {
                            isChecked = it
                        }, colors = CheckboxDefaults.colors(
                            checkedColor = Color.Black,
                            checkmarkColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.width(width = 3.dp))

                    HyperlinkText(
                        fullText = "By clicking the checkbox, you agree to our Terms & Conditions and Privacy Policy…",
                        hyperLinks = mutableMapOf(
                            "Terms & Conditions" to BuildConfig.termsAndConditions,
                            "Privacy Policy" to BuildConfig.privacyPolicy
                        )
                    )

                }

                Spacer(modifier = Modifier.height(height = 14.dp))

                Button(
                    modifier = Modifier.width(width = 240.dp),
                    onClick = navigateToHomeScreen,
                    shape = RoundedCornerShape(size = 15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        disabledContainerColor = Color.Transparent,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White
                    ),
                    enabled = isChecked,
                    border = BorderStroke(width = 1.dp, color = Color.White)
                ) {
                    Text(
                        text = "Let's Go!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isChecked) Color.Black else Color.White
                    )
                }
            }
        }) {

    }

}


@Composable
private fun HyperlinkText(
    fullText: String,
    hyperLinks: Map<String, String>
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)

        for ((key, value) in hyperLinks) {

            val startIndex = fullText.indexOf(key)
            val endIndex = startIndex + key.length
            addStyle(
                style = SpanStyle(
                    fontSize = TextUnit.Unspecified,
                    fontWeight = FontWeight.Normal,
                    textDecoration = TextDecoration.Underline
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = value,
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = TextUnit.Unspecified
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = Modifier,
        text = annotatedString,
        style = MaterialTheme.typography.labelLarge,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}