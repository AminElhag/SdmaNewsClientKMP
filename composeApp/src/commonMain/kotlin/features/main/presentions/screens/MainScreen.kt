package features.main.presentions.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import common.components.ErrorScreen
import common.components.NothingToShowScreen
import common.components.ShimmerListItem
import features.main.presentions.components.NewsItem
import features.main.presentions.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class MainScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<MainViewModel>()
        val state = viewModel.state


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text("سدما")
                }, actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "الاشعارات"
                        )
                    }
                }, navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "الانتقال الی حسابي"
                        )
                    }
                })
            },
        ) { values ->
            if (state.isLoading) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(values)
                ) {
                    items(6) {
                        ShimmerListItem(
                            modifier = Modifier.fillMaxSize().padding(16.dp)
                        )
                    }
                }
            } else if (state.error != null) {
                ErrorScreen(modifier = Modifier.padding(values),
                    errorMessage = state.error,
                    onRetry = {
                        viewModel.getLastNews()
                    })
            } else if (state.items.isEmpty()) {
                NothingToShowScreen(modifier = Modifier.padding(values))
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(values)
                ) {
                    items(state.items.size) {
                        val item = state.items[it]
                        if (it >= state.items.size - 1 && !state.endReached && !state.isListLoading) {
                            viewModel.loadNextItems()
                        }
                        NewsItem(item)
                    }
                    item {
                        if (state.isListLoading) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}