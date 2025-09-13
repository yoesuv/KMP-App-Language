package com.yoesuv.kmplanguage.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kmpapplanguage.composeapp.generated.resources.Res
import kmpapplanguage.composeapp.generated.resources.change_language
import kmpapplanguage.composeapp.generated.resources.language
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingScreen(
    nav: NavHostController,
    padding: PaddingValues = PaddingValues(),
    onLanguageSelected: (String) -> Unit
) {
    var showLanguageDialog by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier.padding(padding)
            .padding(24.dp)
    ) {
        Text(
            stringResource(Res.string.language), style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(Modifier.height(16.dp))
        Row {
            Text("")
            Button(onClick = { showLanguageDialog = true }, content = {
                Text(
                    stringResource(Res.string.change_language), style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            })
        }
    }
    
    if (showLanguageDialog) {
        LanguageSelectionDialog(
            onDismiss = { showLanguageDialog = false },
            onLanguageSelected = { code ->
                onLanguageSelected(code)
                showLanguageDialog = false
            }
        )
    }
}

