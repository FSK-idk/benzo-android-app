package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BzPullToRefreshBox(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    content: @Composable (BoxScope.() -> Unit),
) {
    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = modifier,
        onRefresh = onRefresh,
        isRefreshing = isRefreshing,
        state = state,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                state = state,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        },
        content = content,
    )
}

@Preview
@Composable
private fun BzPullToRefreshBoxPreview() {
    BenzoMobileTheme {
        Surface {
            BzPullToRefreshBox(
                modifier = Modifier.fillMaxSize(),
                onRefresh = {},
                isRefreshing = true
            ) {}
        }
    }
}