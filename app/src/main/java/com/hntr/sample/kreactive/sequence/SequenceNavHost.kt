package com.hntr.sample.kreactive.sequence

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.hntr.sample.kreactive.di.ViewModelFactory
import com.hntr.sample.kreactive.navigation.*
import com.hntr.sample.kreactive.sequence.input.SequenceInputScreen
import com.hntr.sample.kreactive.sequence.result.SequenceResultScreen

@Composable
fun SequenceNavHost(navController: NavHostController = rememberNavController()) =
    NavHost(
        navController = navController,
        startDestination = SequenceDestinations.SEQUENCE_INPUT.path
    ) {
        composable(
            route = SequenceDestinations.SEQUENCE_INPUT.path
        ) {
            SequenceInputScreen(
                onValidationBtnClicked = { nav ->
                    navController.navigate(
                        buildString {
                            append(SequenceDestinations.SEQUENCE_RESULT.path)
                            append("/${nav.firstMultipleValue}")
                            append("/${nav.secondMultipleValue}")
                            append("/${nav.firstWordValue}")
                            append("/${nav.secondWordValue}")
                        }
                    )
                }
            )
        }
        composable(
            route = "${SequenceDestinations.SEQUENCE_RESULT.path}/{$FIRST_MULTIPLE}/{$SECOND_MULTIPLE}/{$FIRST_WORD}/{$SECOND_WORD}",
            arguments = listOf(
                navArgument(FIRST_MULTIPLE) { type = NavType.IntType },
                navArgument(SECOND_MULTIPLE) { type = NavType.IntType },
                navArgument(FIRST_WORD) { type = NavType.StringType },
                navArgument(SECOND_WORD) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            SequenceResultScreen(
                viewModel = ViewModelFactory.createSequenceResultViewModel()
                    .also { viewModel ->
                        val firstMultiple = backStackEntry.arguments?.getInt(FIRST_MULTIPLE) ?: return@also
                        val secondMultiple = backStackEntry.arguments?.getInt(SECOND_MULTIPLE) ?: return@also
                        val firstWord = backStackEntry.arguments?.getString(FIRST_WORD) ?: return@also
                        val secondWord = backStackEntry.arguments?.getString(SECOND_WORD) ?: return@also

                        viewModel.replaceSequence(firstMultiple, secondMultiple, firstWord, secondWord)
                    },
                onBackClicked = { navController.navigateUp() }
            )
        }
    }