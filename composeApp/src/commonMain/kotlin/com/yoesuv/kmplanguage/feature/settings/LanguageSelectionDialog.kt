package com.yoesuv.kmplanguage.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kmpapplanguage.composeapp.generated.resources.Res
import kmpapplanguage.composeapp.generated.resources.cancel
import kmpapplanguage.composeapp.generated.resources.english
import kmpapplanguage.composeapp.generated.resources.indonesian
import kmpapplanguage.composeapp.generated.resources.select_language
import org.jetbrains.compose.resources.stringResource

@Composable
fun LanguageSelectionDialog(
    onDismiss: () -> Unit,
    onLanguageSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                stringResource(Res.string.select_language), style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        text = {
            Column {
                TextButton(
                    onClick = { onLanguageSelected("English") }
                ) {
                    Text(
                        stringResource(Res.string.english), style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
                TextButton(
                    onClick = { onLanguageSelected("Indonesian") }
                ) {
                    Text(
                        stringResource(Res.string.indonesian), style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(Res.string.cancel))
            }
        }
    )
}
