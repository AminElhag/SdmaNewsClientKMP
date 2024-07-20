package features.main.presentions.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.plusmobileapps.konnectivity.Konnectivity
import common.components.ErrorScreen
import common.components.NothingToShowScreen
import features.main.presentions.components.NewsItem
import features.main.presentions.components.ShimmerListItem
import features.main.presentions.viewmodel.MainViewModel
import features.newsDetails.presentaions.screens.NewsDetailsScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class MainScreen : Screen {
    @OptIn(
        ExperimentalMaterial3Api::class, KoinExperimentalAPI::class,
        ExperimentalMaterialApi::class
    )
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<MainViewModel>()
        val state = viewModel.state
        val navigator = LocalNavigator.current
        val konnectivity = remember { Konnectivity() }
        val isConnected by konnectivity.isConnectedState.collectAsState()
        val pullRefreshState = rememberPullRefreshState(
            refreshing = state.isListLoading,
            onRefresh = viewModel::retry
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("سدما")
                    },
                )
            },
        ) { values ->
            if (isConnected) {
                if (state.isFirstLoad) {
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
                            if (isConnected) {
                                viewModel.retry()
                            }
                        })
                } else if (state.items.isEmpty()) {
                    NothingToShowScreen(modifier = Modifier.padding(values))
                } else {
                    Box(
                        modifier = Modifier.pullRefresh(pullRefreshState)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize().padding(values)
                        ) {
                            items(state.items.size) {
                                val item = state.items[it]
                                if (it >= state.items.size - 1 && !state.endReached && !state.isListLoading) {
                                    viewModel.loadNextItems()
                                }
                                NewsItem(
                                    item,
                                ) {
                                    navigator?.push(NewsDetailsScreen(item.id))
                                }
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
            } else {
                ErrorScreen(
                    modifier = Modifier.padding(values),
                    errorMessage = "لا يوجد اتصال بالانترنت الرجاء تفعيل الانترنت و المحاولة مجددا"
                ) {
                    if (isConnected) {
                        viewModel.retry()
                    }
                }
            }
        }
    }
}