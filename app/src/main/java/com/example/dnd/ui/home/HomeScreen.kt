package com.example.dnd.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.dnd.R

@Composable
fun HomeScreen(
    onClassesClick: () -> Unit,
    onFeaturesClick: () -> Unit,
    onMonstersClick: () -> Unit,
    onSpellsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val localOrientation = LocalConfiguration.current.orientation
        Image(
            painter = painterResource(id = R.drawable.dnd_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = when (localOrientation) {
                Configuration.ORIENTATION_LANDSCAPE -> ContentScale.FillWidth
                else -> ContentScale.FillHeight
            },
            alpha = 0.5f
        )
        Column() {
            NamedButton(
                text = stringResource(id = R.string.classes),
                onClick = onClassesClick
            )
            NamedButton(
                text = stringResource(id = R.string.features),
                onClick = onFeaturesClick
            )
            NamedButton(
                text = stringResource(id = R.string.monsters),
                onClick = onMonstersClick
            )
            NamedButton(
                text = stringResource(id = R.string.spells),
                onClick = onSpellsClick
            )
        }
    }
}

@Composable
fun NamedButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(fraction = 0.67f)
    ) {
        Text(text = text)
    }
}
