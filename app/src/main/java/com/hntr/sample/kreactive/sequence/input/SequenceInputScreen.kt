package com.hntr.sample.kreactive.sequence.input

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.hntr.sample.kreactive.R
import com.hntr.sample.kreactive.navigation.NavParamFactory
import com.hntr.sample.kreactive.navigation.SequenceResultNavParams
import com.hntr.sample.kreactive.ui.component.*
import com.hntr.sample.kreactive.ui.theme.*
import kotlinx.coroutines.*
import java.util.*

private const val MULTIPLE_MIN_LIMIT = 1
private const val MULTIPLE_MAX_LIMIT = 10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SequenceInputScreen(
    modifier: Modifier = Modifier,
    onValidationBtnClicked: (SequenceResultNavParams) -> Unit
) = Scaffold(
    modifier = Modifier.statusBarsPadding(),
    topBar = {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)),
            title = {
                ToolbarWithTitle(stringResource(R.string.toolbar_title_projects))
            },
        )
    }
) {
    Column(
        modifier = modifier
            .padding(it)
            .padding(vertical = NormalPadding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isBtnEnabledState = remember { mutableStateOf(false) }
        val firstMultiplePicker = remember { mutableStateOf(MULTIPLE_MIN_LIMIT) }
        val secondMultiplePicker = remember { mutableStateOf(MULTIPLE_MIN_LIMIT) }
        val firstWord = remember { mutableStateOf(TextFieldValue()) }
        val secondWord = remember { mutableStateOf(TextFieldValue()) }

        NumberPickers(firstMultiplePicker, secondMultiplePicker)
        WordInputs(isBtnEnabledState, firstWord, secondWord)

        Spacer(modifier = Modifier.height(NormalPadding))

        ValidationButton(
            isBtnEnabledState = isBtnEnabledState,
            onValidationBtnClicked = onValidationBtnClicked,
            params = NavParamFactory.createSequenceResultNavParams(
                firstMultiplePicker.value,
                secondMultiplePicker.value,
                firstWord.value.text,
                secondWord.value.text
            )
        )
    }
}

@Composable
private fun NumberPickers(
    firstMultiplePicker: MutableState<Int>,
    secondMultiplePicker: MutableState<Int>
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly,
) {
    NumberPicker(
        value = firstMultiplePicker.value,
        range = MULTIPLE_MIN_LIMIT..MULTIPLE_MAX_LIMIT,
        onValueChange = { value ->
            firstMultiplePicker.value = value
        }
    )

    NumberPicker(
        value = secondMultiplePicker.value,
        range = MULTIPLE_MIN_LIMIT..MULTIPLE_MAX_LIMIT,
        onValueChange = { value ->
            secondMultiplePicker.value = value
        }
    )
}

@Composable
private fun WordInputs(
    isBtnEnabledState: MutableState<Boolean>,
    firstWord: MutableState<TextFieldValue>,
    secondWord: MutableState<TextFieldValue>
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly,
) {
    WordInput(
        modifier = Modifier
            .padding(NormalPadding)
            .weight(1F),
        text = firstWord.value.text,
        label = stringResource(R.string.first_word),
        onTextChanged = {
            firstWord.value = TextFieldValue(text = it)
            isBtnEnabledState.value = !isAnyTextFieldsEmpty(firstWord, secondWord)
        }
    )

    WordInput(
        modifier = Modifier
            .padding(NormalPadding)
            .weight(1F),
        text = secondWord.value.text,
        label = stringResource(R.string.second_word),
        onTextChanged = {
            secondWord.value = TextFieldValue(text = it)
            isBtnEnabledState.value = !isAnyTextFieldsEmpty(firstWord, secondWord)
        }
    )
}

@Composable
fun ValidationButton(
    isBtnEnabledState: MutableState<Boolean>,
    onValidationBtnClicked: (SequenceResultNavParams) -> Unit,
    params: SequenceResultNavParams
) {
    Button(
        onClick = { onValidationBtnClicked(params) },
        enabled = isBtnEnabledState.value
    ) {
        Text(
            text = stringResource(R.string.validate),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

private fun isAnyTextFieldsEmpty(
    firstWord: MutableState<TextFieldValue>,
    secondWord: MutableState<TextFieldValue>
) = firstWord.value.text == "" || secondWord.value.text == ""