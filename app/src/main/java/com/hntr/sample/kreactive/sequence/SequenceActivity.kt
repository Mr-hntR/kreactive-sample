package com.hntr.sample.kreactive.sequence

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hntr.sample.kreactive.ui.theme.KreactiveTheme

class SequenceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KreactiveTheme {
                SequenceNavHost()
            }
        }
    }
}