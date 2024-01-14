package snapbite.app.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import snapbite.app.commons.SnapbiteBackgroundImage
import snapbite.app.commons.SnapbiteHeader
import snapbite.app.theme.Caveat


class SearchScreen : Screen {

    @Composable
    override fun Content() {

        Box(modifier = Modifier.fillMaxSize()) {

            var searchItem by rememberSaveable { mutableStateOf(value = "") }

            SnapbiteBackgroundImage()

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SnapbiteHeader(headerTitle = "Search")
                Spacer(modifier = Modifier.height(height = 21.dp))
                OutlinedTextField(
                    value = searchItem,
                    onValueChange = { searchItem = it },
                    shape = RoundedCornerShape(size = 21.dp),
                    placeholder = {
                        Text(
                            text = "Enter Date e.g 21st October 2023...", textAlign = TextAlign.Start,
                            fontFamily = Caveat,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    textStyle = TextStyle(fontFamily = Caveat, fontSize = 21.sp, color = Color.Black),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )
            }

        }

    }

}