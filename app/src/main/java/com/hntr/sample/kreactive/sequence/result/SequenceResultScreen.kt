package com.hntr.sample.kreactive.sequence.result

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hntr.sample.kreactive.R
import com.hntr.sample.kreactive.ui.component.*
import com.hntr.sample.kreactive.ui.theme.SmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SequenceResultScreen(
    viewModel: SequenceResultViewModel,
    onBackClicked: () -> Unit
) = Scaffold(
    modifier = Modifier.statusBarsPadding(),
    topBar = {
        TopAppBar(
            modifier = Modifier
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)),
            title = {
                ToolbarWithTitle(stringResource(R.string.toolbar_title_project_detail))
            },
            navigationIcon = {
                IconButton(
                    onClick = { onBackClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        )
    }
) {
    val state = viewModel.getSequence().observeAsState()
    val sequence = state.value ?: return@Scaffold
    SequenceResult(
        modifier = Modifier.padding(it),
        sequence = sequence
    )
}

@Composable
fun SequenceResult(
    modifier: Modifier,
    sequence: List<String>
) = Column(
    modifier = modifier.fillMaxWidth()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(SmallPadding),
        verticalArrangement = Arrangement.spacedBy(SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(sequence) {
            Text(text = it)
        }
    }
}