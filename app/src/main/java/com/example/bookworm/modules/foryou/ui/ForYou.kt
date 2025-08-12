package com.example.bookworm.modules.foryou.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.modules.bookGrid.ui.BookGrid


@Composable
fun ForYou(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stringResource(R.string.for_you),
            style = MaterialTheme.typography.titleLarge
        )
        BookGrid(navController = navController)
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ForYouPreview() {
    ForYou()
}
