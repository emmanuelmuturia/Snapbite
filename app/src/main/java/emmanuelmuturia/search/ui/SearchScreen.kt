package emmanuelmuturia.search.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.androidx.compose.koinViewModel
import snapbite.app.R


class SearchScreen : Screen {

    @Composable
    override fun Content() {

        val searchScreenViewModel: SearchScreenViewModel = koinViewModel()

        var searchItem by rememberSaveable { searchScreenViewModel.searchItem }

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.snapbite),
                contentDescription = "Background Image",
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchScreenHeader()
                Spacer(modifier = Modifier.height(height = 21.dp))
                OutlinedTextField(
                    value = searchItem,
                    onValueChange = { searchItem = it },
                    shape = RoundedCornerShape(size = 21.dp),
                    placeholder = {
                        Text(
                            text = "Enter Date e.g 21st October 2023...", textAlign = TextAlign.Start,
                            fontFamily = emmanuelmuturia.commons.theme.Caveat,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    textStyle = TextStyle(fontFamily = emmanuelmuturia.commons.theme.Caveat, fontSize = 21.sp, color = Color.Black),
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


@Composable
fun SearchScreenHeader() {

    val navigator = LocalNavigator.currentOrThrow

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp, start = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.35f)) {

            Icon(
                modifier = Modifier.clickable { navigator.pop() },
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back Arrow",
                tint = Color.Black
            )

        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Search",
                fontFamily = emmanuelmuturia.commons.theme.Caveat,
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }

}