package com.example.dnd.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dnd.R
import com.example.dnd.data.GenericDndClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturesScreen(
    viewModel: FeaturesViewModel,
    onBackClick: () -> Unit,
) {
    val dndFeatures = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                title = { Text(stringResource(R.string.features)) })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            items(
                count = dndFeatures.value.dndFeatures.size,
                key = { index -> dndFeatures.value.dndFeatures[index].index }
            ) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp,
                            vertical = 8.dp
                        )
                        .background(MaterialTheme.colorScheme.background)
                        .border(2.dp, Color.Black),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        ),
                        text = dndFeatures.value.dndFeatures[item].name,
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    if (isNameInvalid(dndFeatures.value.dndFeatures[item])) {
                        Text(
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                bottom = 8.dp,
                            ),
                            text = dndFeatures.value.dndFeatures[item].index,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }
    }
}

private fun isNameInvalid(dndFeature: GenericDndClass): Boolean {
    val lowerFeatureName = dndFeature.name.lowercase()
    val noSlashesOrDashes = Regex("[/-]")
    val lowerFeatureNameNoSlash = lowerFeatureName.replace(noSlashesOrDashes, " ")
    val invalidChars = Regex("[^A-Za-z0-9 ]")
    val lowerName = lowerFeatureNameNoSlash.replace(invalidChars, "")
    val lowerIndexName = dndFeature.index.replace('-',' ')
    return lowerName != lowerIndexName
}
