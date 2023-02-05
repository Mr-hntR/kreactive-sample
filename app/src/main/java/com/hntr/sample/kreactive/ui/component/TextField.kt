package com.hntr.sample.kreactive.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordInput(
    modifier: Modifier,
    text: String,
    label: String,
    onTextChanged: (String) -> Unit
) {
    val alphanumericalRegex = "^[a-zA-Z\\d]*$".toRegex()
    TextField(
        modifier = modifier,
        value = text,
        label = {
            Text(label)
        },
        onValueChange = {
            if (it.contains(alphanumericalRegex)) {
                onTextChanged(it)
            }
        }
    )
}