package features.newsDetails.presentaions.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import common.ResultState
import common.components.ErrorScreen
import common.util.DateUtil
import features.newsDetails.domain.NewsModel
import features.newsDetails.presentaions.components.ShimmerNewsDetails
import features.newsDetails.presentaions.viewmodel.NewsDetailsViewModel
import networking.util.NetworkError
import networking.util.Result
import networking.util.onError
import networking.util.onSuccess
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.theme.textColor

class NewsDetailsScreen(
    private val newsId: Long
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = koinViewModel<NewsDetailsViewModel>()
        viewModel.setId(id = newsId)
        val state = viewModel.getNewsDetails.collectAsState()
        viewModel.loadingNewsDetaild()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text("تفاصيل الخبر")
                }, navigationIcon = {
                    IconButton(onClick = {
                        navigator?.pop()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "الرجوع للصفحة السابقة"
                        )
                    }
                })
            }
        ) { value ->
            when (state.value) {
                is ResultState.Loading -> {
                    ShimmerNewsDetails(modifier = Modifier.padding(value))
                }

                is ResultState.Error -> {
                    ErrorScreen(
                        errorMessage = NetworkError.UNKNOWN.message
                    ) {
                        viewModel.loadingNewsDetaild()
                    }
                }

                is ResultState.Success -> {
                    (state.value as ResultState.Success<Result<NewsModel, NetworkError>>).response.onError {
                        ErrorScreen(
                            errorMessage = it.message
                        ) {
                            viewModel.loadingNewsDetaild()
                        }
                    }.onSuccess {
                        Column(
                            modifier = Modifier.fillMaxSize().padding(value)
                                .background(color = MaterialTheme.colorScheme.background)
                                .verticalScroll(rememberScrollState()),
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier.fillMaxWidth(),
                                model = it.imageUrl,
                                contentDescription = null,
                                loading = {
                                    CircularProgressIndicator()
                                },
                                error = {
                                    AsyncImage(
                                        model = Icon(
                                            imageVector = Icons.Default.Warning,
                                            contentDescription = "Warning"
                                        ), contentDescription = "Warning"
                                    )
                                }
                            )
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.End,
                            ) {
                                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                                    Text(
                                        it.title,
                                        modifier = Modifier.fillMaxWidth()
                                            .align(alignment = Alignment.Start)
                                            .padding(top = 6.dp),
                                        textAlign = TextAlign.Justify,
                                        color = textColor
                                    )
                                    Text(
                                        DateUtil.getDateInBeautyWay(it.updateTime),
                                        textAlign = TextAlign.Justify,
                                        modifier = Modifier.padding(top = 6.dp),
                                        color = textColor
                                    )
                                    Text(
                                        it.content,
                                        textAlign = TextAlign.Justify,
                                        modifier = Modifier.padding(top = 6.dp),
                                        color = textColor
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }

}