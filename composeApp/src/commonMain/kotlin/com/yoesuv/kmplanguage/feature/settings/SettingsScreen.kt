package com.yoesuv.kmplanguage.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmpapplanguage.composeapp.generated.resources.Res
import kmpapplanguage.composeapp.generated.resources.change_language
import kmpapplanguage.composeapp.generated.resources.language
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingScreen() {
    Column {
        Text(stringResource(Res.string.language))
        Spacer(Modifier.height(16.dp))
        Row {
            Text("")
            Button(onClick = {}, content = {
                Text(stringResource(Res.string.change_language))
            })
        }
    }
}