package com.yoesuv.kmplanguage.feature.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmpapplanguage.composeapp.generated.resources.Res
import kmpapplanguage.composeapp.generated.resources.cancel
import kmpapplanguage.composeapp.generated.resources.english
import kmpapplanguage.composeapp.generated.resources.indonesian
import kmpapplanguage.composeapp.generated.resources.select_language
import org.jetbrains.compose.resources.stringResource
import com.yoesuv.kmplanguage.Language
import com.yoesuv.kmplanguage.core.theme.AppColors

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
                    onClick = { onLanguageSelected(Language.English.isoFormat) }
                ) {
                    Text(
                        stringResource(Res.string.english), style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
                TextButton(
                    onClick = { onLanguageSelected(Language.Indonesia.isoFormat) }
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
            OutlinedButton(
                onClick = onDismiss,
                border = BorderStroke(width = 1.dp, color = AppColors.Red500),
            ) {
                Text(
                    stringResource(Res.string.cancel), style = TextStyle(
                        color = AppColors.Red500
                    )
                )
            }
        }
    )
}

