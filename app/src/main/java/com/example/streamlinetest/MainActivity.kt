package com.example.streamlinetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.example.streamlinetest.data.local.UniversityEntity
import com.example.streamlinetest.ui.theme.StreamlineTestTheme
import com.example.streamlinetest.ui.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamlineTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UniversityList(mainViewModel)
                }
            }
        }
    }
}


@Composable
fun UniversityList(mainViewModel: MainViewModel) {
    val universities = mainViewModel.universities.observeAsState().value

    LaunchedEffect(key1 = null, block = {
        mainViewModel.getAllUniversities()
    })

    LazyColumn {
        items(universities!!) { uni ->
            UniversityCard(universityEntity = uni)
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
                text = universityEntity.name,
                style = MaterialTheme.typography.h3
            )
            Text(
                text = universityEntity.country,
                style = MaterialTheme.typography.h4
            )
        }
    }
}
