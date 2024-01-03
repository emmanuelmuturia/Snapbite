package emmanuelmuturia.faq

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import emmanuelmuturia.components.SnapbiteBackgroundImage
import emmanuelmuturia.components.SnapbiteHeader
import emmanuelmuturia.model.FAQ
import emmanuelmuturia.state.ErrorScreen
import emmanuelmuturia.state.LoadingScreen
import emmanuelmuturia.state.SnapbiteState

@Composable
fun FAQScreen(onClick: () -> Unit) {

    val faqScreenViewModel: FAQScreenViewModel = hiltViewModel()

    val faqList by faqScreenViewModel.faqList.collectAsStateWithLifecycle()

    val faqState by faqScreenViewModel.faqState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SnapbiteHeader(
                navigateBack = onClick,
                headerTitle = "FAQ"
            )

            when (faqState) {
                is SnapbiteState.Success -> FAQContent(faqList = faqList)
                is SnapbiteState.Error -> ErrorScreen()
                else -> LoadingScreen()
            }

            Spacer(modifier = Modifier.weight(weight = 1f))

        }

    }

}

@Composable
private fun FAQContent(faqList: List<FAQ>) {

    val faqScreenViewModel: FAQScreenViewModel = hiltViewModel()

    val isLoading by faqScreenViewModel.isLoading.collectAsStateWithLifecycle()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = faqScreenViewModel::refreshFAQs,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        }) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 7.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(items = faqList, key = {faq ->
                faq.faqId
            }) { faq ->
                FAQCard(faq = faq)
            }

        }

    }

}


@Composable
private fun FAQCard(faq: FAQ) {

    var isExpanded by rememberSaveable { mutableStateOf(value = false) }

    val surfaceColor by animateColorAsState(
        targetValue = Color.Transparent, label = "Surface Colour"
    )

    Surface(
        modifier = Modifier
            .clickable { isExpanded = !isExpanded }
            .padding(start = 7.dp, end = 7.dp)
            .animateContentSize()
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(size = 21.dp),
        color = surfaceColor,
        border = BorderStroke(width = 3.dp, color = Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (!isExpanded) FAQProblem(faq = faq) {
                isExpanded = !isExpanded
            } else FAQProblemAndSolution(faq = faq) { isExpanded = !isExpanded }

        }

    }

    Spacer(modifier = Modifier.height(height = 21.dp))

}


@Composable
private fun FAQProblem(faq: FAQ, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 7.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = faq.faqProblem,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1
        )

        Spacer(modifier = Modifier.width(width = 10.dp))

        Icon(
            modifier = Modifier
                .clickable { onClick() }
                .size(size = 42.dp),
            imageVector = Icons.Rounded.KeyboardArrowDown,
            contentDescription = "Dropdown Button",
            tint = Color.Black
        )

    }

}


@Composable
private fun FAQProblemAndSolution(faq: FAQ, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 3.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = faq.faqProblem,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            maxLines = Int.MAX_VALUE
        )

        Spacer(modifier = Modifier.width(width = 10.dp))

        Icon(
            modifier = Modifier
                .clickable { onClick() }
                .size(size = 42.dp),
            imageVector = Icons.Rounded.KeyboardArrowUp,
            contentDescription = "Dropdown Button",
            tint = Color.White
        )

    }

    Text(
        modifier = Modifier.padding(all = 14.dp),
        text = faq.faqSolution,
        style = MaterialTheme.typography.labelLarge,
        fontSize = 18.sp
    )

}