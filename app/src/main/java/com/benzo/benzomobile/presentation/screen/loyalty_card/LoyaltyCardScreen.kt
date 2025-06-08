package com.benzo.benzomobile.presentation.screen.loyalty_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoyaltyCardScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isRefreshing: Boolean,
    loyaltyCard: LoyaltyCard,
    login: String,
    snackbarHostState: SnackbarHostState,
    onRefresh: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { SimpleTopAppBar(title = "Loyalty card") },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    LoyaltyCardView(
                        loyaltyCard = loyaltyCard,
                        login = login
                    )
                }
            }
        }
    }
}

@Composable
fun LoyaltyCardView(
    loyaltyCard: LoyaltyCard,
    login: String,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.tertiaryContainer,
                            MaterialTheme.colorScheme.secondaryContainer,
                            MaterialTheme.colorScheme.primaryContainer,
                        )
                    )
                )
                .padding(24.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "BENZO",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = loyaltyCard.number,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )

            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = "Bonuses: ${"%.2f".format(loyaltyCard.balance / 100.0f)}",
                    fontSize = 16.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "@${login}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun LoyaltyCardScreenPreview() {
    BenzoMobileTheme {
        LoyaltyCardScreen(
            isLoading = false,
            isRefreshing = false,
            loyaltyCard = LoyaltyCard(
                number = "123456789",
                balance = 25000,
            ),
            login = "ivanivanov",
            snackbarHostState = SnackbarHostState(),
            onRefresh = {},
        )
    }
}