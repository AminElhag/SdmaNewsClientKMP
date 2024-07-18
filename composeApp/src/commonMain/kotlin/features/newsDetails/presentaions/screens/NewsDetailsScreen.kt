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

    val item = NewsModel(
        id = newsId,
        imageUrl = "https://ichef.bbci.co.uk/ace/ws/800/cpsprodpb/4f39/live/da2299b0-4375-11ef-b74c-bb483a802c97.jpg.webp",
        title = "لسودان: هل يبدو المشهد متناقضا بين مؤتمرات السلام وحدة المعارك على الأرض؟",
        updateTime = "2024-07-14T11:50:19.213921Z",
        content = """
            لم تنقطع المؤتمرات الدولية أو الإقليمية، التي هدفت إلى إيجاد حل للأزمة السودانية ،على مدى السنوات الماضية، لكن الشهر الجاري شهد ومايزال يشهد زخما، وتتاليا لهذه المؤتمرات، التي تسعى جميعها لوقف الحرب السودانية المدمرة، التي صادف الإثنين 15 نيسان/أبريل الماضي، الذكرى الأولى لاندلاعها بين الجيش السوداني وقوات الدعم السريع.

            غير أن الملفت هو أنه ورغم توالي المؤتمرات، على مدى الشهر الجاري، فإن الواقع على الأرض لايوحي بحديث السلام وإيقاف الحرب، الذي يجري تداوله في تلك المؤتمرات، في وقت يبدو فيه المدنيون السودانيون، الأكثر تضررا من صراع طرفين عسكريين من أجل السلطة، وفق ما يقوله الكثير من المواطنين السودانيين.

            لا جدية

            وتطرح تلك المؤتمرات المتتالية، تساؤلات حول ما الذي يمكن، أن تقدمه مفاوضات هي في جلها، غير مباشرة بين الجيش السوداني، وقوات الدعم السريع، وما إذا كان الطرفان وفق مايثيره محللون، يمضون للمفاوضات بنوايا حقيقية، من أجل وقف الحرب، أم أنهم يدخلون في لعبة علاقات عامة مع العالم، للظهور بأنهم راغبون في السلام، بينما يبدو واقع ما يفعلونه على الأرض مختلفا تماما.

            آخر تلك المؤتمرات والمفاوضات، هي تلك التي انطلقت في جنيف بسويسرا، في 12 تموز/يوليو الجاري، وسميت بـ "محادثات جنيف غير المباشرة" لسلام السودان، وتتم برعاية أممية، عبر مبعوث الأمم المتحدة إلى السودان، رمطان لعمامرة.
            
            ويوم الإثنين 15 تموز/ يوليو الجاري، اُختتم مؤتمر آخر، هو مؤتمر القوى السياسية السودانية، الذي انعقد في العاصمة الإثيوبية أديس أبابا، برعاية الاتحاد الإفريقي، وقبله انعقد في العاصمة المصرية القاهرة، مؤتمر القوى السياسية والمدنية السودانية، على مدي يومين، هما السادس والسابع من تموز/يوليو الجاري، وناقش إمكانية وقف الحرب في السودان، في وقت أفادت فيه أنباء بأن بعض القوى السياسية، رفضت التوقيع على بيانه الختامي.

            وبينما تبدو الأهداف الرئيسية، لكل تلك المؤتمرات،متلخصة في وقف الحرب وإيصال المساعدات الإنسانية للنازحين، بعد تزايد أعدادهم بصورة كبيرة، نتيجة لاتساع رقعة الحرب، فإن الواقع على الأرض، يُنبئ بعكس ذلك، إذ يواصل الطرفان العسكريان المتقاتلان عملياتهما العسكرية دون هوادة.
            
            وفي إبراز للتناقض بين ما يجري، من حديث مؤتمرات إحلال السلام في السودان، والواقع على الأرض، كان لافتا ذلك التصريح الذي نُقل عن عضو مجلس السيادة الانتقالي السوداني، الفريق أول ركن ياسر عبد الرحمن العطا، الذي قال إن التفاوض مع "قوات الدعم السريع"، هو مجرد تأجيل للمعركة، موضحا أن "التفاوض يعني الدخول في مشاكل أمنية وسياسية". وأضاف "لن يكون هناك هدنة، ولو استمر القتال 100 سنة".

            الآمال معلقة على جنيف

            على الجانب الآخر يعلق كثير من المراقبين في السودان، آمالا أكبر على تلك المحادثات الأخيرة ،التي انطلقت في جنيف برعاية أممية، ويردون ذلك إلى أن الوسيط في هذه الحالة وهو الأمم المتحدة، ربما تحظى بقبول لدى طرفي الصراع، بما يمكنها من احداث اختراق، وهم يرون أنها ورغم تركيزها ،على إيصال المساعدات الإنسانية أولا، إلا أنها ربما تتفادى الخلاف الشائك بشأن القضايا العسكرية، قبل أن تحقق اختراقا في المجال الإنساني قد ينتقل بدوره إلى قضية وقف الحرب، والتي يتفق الجميع على أنها معقدة للغاية، ولايمكن الاتفاق على إيقافها بين يوم وليلة.

            وأدت الحرب السودانية، التي مر عليها الآن أكثر من عام، إلى قتل وجرح عشرات الألاف من السودانيين، بينهم نحو 15 ألف شخص، في مدينة الجنينة عاصمة ولاية غرب دارفور وحدها، وفق خبراء بالأمم المتحدة، ورغم أن الحصيلة الحقيقية لضحايا تلك الحرب، ماتزال غير مؤكدة حتى الآن، إلا أن عدد قتلاها قد يصل إلى "150 ألفا" وفقا للمبعوث الأميركي الخاص للسودان.

            وعلى صعيد المأساة الإنسانية للنازحين واللاجئين، فإن التقديرات تشير إلى أن الحرب في السودان، أدت إلى أكثر من عشرة ملايين نازح داخل البلاد، في حين دفعت حوالي مليونين ونصف مليون شخص، إلى الفرار إلى الدول المجاورة.

            هل يبدو التناقض واضحا بين توالي المؤتمرات الخاصة بسلام السودان واستمرار المعارك على الأرض؟
            لماذا تفشل المؤتمرات التي ترعاها دول جوار السودان في التوصل إلى حل للأزمة؟
            هل تتفقون مع من يرون أن مفاوضات جنيف برعاية أممية هي التي ستحقق الاختراق؟
            هل يحتاج الأمر إلى ضغوط أقوى من قوى دولية على الطرفين المتحاربين لوقف الحرب؟
            لماذا يبدو الطرفان المتحاربان في تجاهل تام لمأساة المدنيين النازحين والجوعى؟
            هل تتفقون مع من يرون أن استمرار الحرب في السودان مرهون فقط بالطموحات السياسية للبرهان وحميدتي؟.

        """.trimIndent()
    )

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
                                model = item.imageUrl,
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
                                        item.title,
                                        modifier = Modifier.fillMaxWidth().align(alignment = Alignment.Start)
                                            .padding(top = 6.dp),
                                        textAlign = TextAlign.Justify,
                                        color = textColor
                                    )
                                    Text(
                                        DateUtil.getDateInBeautyWay(item.updateTime),
                                        textAlign = TextAlign.Justify,
                                        modifier = Modifier.padding(top = 6.dp),
                                        color = textColor
                                    )
                                    Text(
                                        item.content,
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