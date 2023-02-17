package com.example.streamlinetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.streamlinetest.data.local.UniversityEntity
import com.example.streamlinetest.ui.theme.StreamlineTestTheme
import com.example.streamlinetest.ui.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamlineTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UniversityList()
                }
            }
        }
    }
}


@Composable
fun UniversityList(mainViewModel: MainViewModel = viewModel()) {
    val universities = mainViewModel.universities.observeAsState().value

    LaunchedEffect(key1 = null, block = {
        mainViewModel.getAllUniversities()
    })

    if (universities != null && universities.isNotEmpty()) {
        LazyColumn {
            items(universities) { uni ->
                UniversityCard(universityEntity = uni)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun UniversityCard(
    universityEntity: UniversityEntity
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = universityEntity.name,
                style = MaterialTheme.typography.h4
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = universityEntity.country,
                style = MaterialTheme.typography.h6
            )
        }
    }
}
