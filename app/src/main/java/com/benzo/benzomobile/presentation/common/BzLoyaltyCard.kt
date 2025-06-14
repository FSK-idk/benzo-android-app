package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzLoyaltyCard(
    modifier: Modifier = Modifier,
    loyaltyCard: LoyaltyCard,
    login: String,
) {
    Surface(
        modifier = modifier.shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ),
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
                    text = "Бонусы: ${"%.2f".format(loyaltyCard.balance / 100.0f)}",
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

@Preview
@Composable
private fun BzLoyaltyCardPreview() {
    BenzoMobileTheme {
        Surface {
            BzLoyaltyCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                loyaltyCard = LoyaltyCard(
                    number = "1234567890",
                    balance = 1234
                ),
                login = "login"
            )
        }
    }
}