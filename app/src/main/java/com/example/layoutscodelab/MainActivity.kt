package com.example.layoutscodelab

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutscodelab.ui.theme.LayoutsCodeLabTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsCodeLabTheme {
                LayoutsCodeLab()
            }
        }
    }
}

@Composable
fun LayoutsCodeLab(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodeLab")
                },
                actions = {
                    IconButton(onClick = {/* doSomething() */}){
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            BodyContent(
                Modifier
                    .padding(innerPadding)
                    .padding(8.dp))
        }
    }
}

@Composable
fun LazyList(){
    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()


    Column {
        Row{
            Button(onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to the top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    // listSize -1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }){
                Text("Scroll to the end")
            }
        }
        LazyColumn(state = scrollState) {
            items(100) {
                ImageListItem(index = it)
            }
        }
    }
}
@Composable
fun SimpleList(){
    // We save the scrolling position with this state that can always
    // be used to programmatically scroll the list
    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)){
        repeat(100){
            Text("Item #$it")
        }
    }
}

@Composable
fun ImageListItem(index: Int){
    Row(verticalAlignment = Alignment.CenterVertically){
        CoilImage(
            data = "https://developer.android.com/images/brand/Android_Robot.png",
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier){
    Column(modifier = modifier){
        LazyList()
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview(){
    LayoutsCodeLabTheme {
        LayoutsCodeLab()
    }
}

