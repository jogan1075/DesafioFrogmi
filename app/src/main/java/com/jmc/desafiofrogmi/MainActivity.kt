package com.jmc.desafiofrogmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmc.desafiofrogmi.data.remote.model.Data
import com.jmc.desafiofrogmi.presentation.MainVM
import com.jmc.desafiofrogmi.presentation.contract.MainContract
import com.jmc.desafiofrogmi.ui.component.LoadingScreen
import com.jmc.desafiofrogmi.ui.theme.DesafioFrogmiTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesafioFrogmiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = viewModel.viewState.value

                    LoadingScreen(isLoading = viewModel.state.isLoading) {
                        RenderScreen(state.list, viewModel::setEvent, state)
                    }

                }
            }
        }
    }
}

@Composable
fun RenderScreen(
    list: List<Data>,
    onEventSent: (MainContract.Event) -> Unit,
    stateMain: MainContract.State,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val state = rememberLazyListState()

        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(list) { store ->
                ListItem(store = store)
            }
        }

        LaunchedEffect(state) {
            snapshotFlow { state.firstVisibleItemIndex }
                .debounce(300)
                .map {
                    (it + 1)
                }
                .collect {
                    onEventSent(
                        MainContract.Event.GetListPokemon(
                            stateMain.positionNext,
                            stateMain.positionOld
                        )
                    )
                }
        }
    }
}

@Composable
fun ListItem(store: Data, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        with(store) {
            Text(
                text = "${attributes.name} | ${attributes.code}",
                modifier = modifier
                    .wrapContentWidth()
                    .align(Alignment.TopStart)
                    .padding(start = 5.dp),
                color = Color.Black,
                fontSize = 20.sp
            )
            Text(
                text = attributes.fullAddress,
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 5.dp),
                color = Color.Gray,
                fontSize = 15.sp
            )
        }
        Divider(
            thickness = 1.dp,
            color = Color.Blue,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesafioFrogmiTheme {
//        Greeting("Android")
    }
}

fun LazyListState.isScrolledToTheEnd(): Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return lastItem == null || lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset
}