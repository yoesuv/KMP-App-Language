package com.yoesuv.kmplanguage.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yoesuv.kmplanguage.core.route.AppRoute
import kmpapplanguage.composeapp.generated.resources.Res
import kmpapplanguage.composeapp.generated.resources.home_information
import kmpapplanguage.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(nav: NavHostController, padding: PaddingValues = PaddingValues()) {
    Column(
        modifier = Modifier.padding(padding)
    ) {
        Text(
            stringResource(Res.string.home_information), style = TextStyle(
                fontSize = 14.sp
            )
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            nav.navigate(AppRoute.Settings)
        }, content = {
            Text(
                stringResource(Res.string.settings), style = TextStyle(
                    fontSize = 14.sp
                )
            )
        })
    }
}