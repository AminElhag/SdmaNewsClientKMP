package features.main.presentions.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import features.main.domain.NewsModel
import features.main.presentions.components.NewsItem
import kotlinx.datetime.Clock

class MainScreen : Screen {

    val newsList = listOf<NewsModel>(
        NewsModel(1, "https://cdnuploads.aa.com.tr/uploads/Contents/2024/07/13/thumbs_b_c_09cc2c4661249276d94f25a7035bff77.jpg?v=162147", "لا يزال السودان يواجه أسوأ أزمة جوع ونزوح مع استمرار الحرب الأهلية لمدة 15 شهرًا", "2024-07-14T11:50:19.213921Z"),
        NewsModel(2, "https://www.aljazeera.net/wp-content/uploads/2023/07/RC2FM0A2JXCX-1689375424.jpg?resize=770%2C513&quality=80", "حرب السودان: منح الأجانب مهلة 15 يومًا للذهاب إلى الخرطوم", "2024-07-14T11:20:19.213921Z"),
        NewsModel(3, "https://assets.thenewhumanitarian.org/s3fs-public/styles/responsive_large_2x/public/2024-04/sudan-egypt-investigation-header.jpg.webp?itok=z_bJg1Ug", "يقوم الجيش المصري باعتقال واحتجاز وترحيل آلاف اللاجئين إلى السودان الذي مزقته الحرب", "2024-07-14T06:14:19.213921Z"),
        NewsModel(1, "https://cdnuploads.aa.com.tr/uploads/Contents/2024/07/13/thumbs_b_c_09cc2c4661249276d94f25a7035bff77.jpg?v=162147", "لا يزال السودان يواجه أسوأ أزمة جوع ونزوح مع استمرار الحرب الأهلية لمدة 15 شهرًا", "2024-07-13T14:14:19.213921Z"),
        NewsModel(2, "https://www.aljazeera.net/wp-content/uploads/2023/07/RC2FM0A2JXCX-1689375424.jpg?resize=770%2C513&quality=80", "حرب السودان: منح الأجانب مهلة 15 يومًا للذهاب إلى الخرطوم", "2024-07-13T14:14:19.213921Z"),
        NewsModel(3, "https://assets.thenewhumanitarian.org/s3fs-public/styles/responsive_large_2x/public/2024-04/sudan-egypt-investigation-header.jpg.webp?itok=z_bJg1Ug", "يقوم الجيش المصري باعتقال واحتجاز وترحيل آلاف اللاجئين إلى السودان الذي مزقته الحرب", "2024-07-13T14:14:19.213921Z"),
        NewsModel(1, "https://cdnuploads.aa.com.tr/uploads/Contents/2024/07/13/thumbs_b_c_09cc2c4661249276d94f25a7035bff77.jpg?v=162147", "لا يزال السودان يواجه أسوأ أزمة جوع ونزوح مع استمرار الحرب الأهلية لمدة 15 شهرًا", "2024-07-13T14:14:19.213921Z"),
        NewsModel(2, "https://www.aljazeera.net/wp-content/uploads/2023/07/RC2FM0A2JXCX-1689375424.jpg?resize=770%2C513&quality=80", "حرب السودان: منح الأجانب مهلة 15 يومًا للذهاب إلى الخرطوم", "2024-07-13T14:14:19.213921Z"),
        NewsModel(3, "https://assets.thenewhumanitarian.org/s3fs-public/styles/responsive_large_2x/public/2024-04/sudan-egypt-investigation-header.jpg.webp?itok=z_bJg1Ug", "يقوم الجيش المصري باعتقال واحتجاز وترحيل آلاف اللاجئين إلى السودان الذي مزقته الحرب", "2024-07-13T14:14:19.213921Z"),
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("سدما")
                    },
                    actions = {
                        IconButton(
                            onClick = {}
                        ){
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "الاشعارات"
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {}
                        ){
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "الانتقال الی حسابي"
                            )
                        }
                    }
                )
            },
        ) { values->
            LazyColumn (
                modifier = Modifier.fillMaxSize().padding(values)
            ){
                items(newsList.size){
                    val item = newsList[it]
                    NewsItem(item)
                }
            }
        }
    }
}