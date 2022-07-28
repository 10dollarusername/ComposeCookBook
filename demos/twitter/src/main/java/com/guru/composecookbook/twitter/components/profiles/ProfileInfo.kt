package com.guru.composecookbook.twitter.components.profiles

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.twitterColor

@Composable
fun ProfileInfo(
    profileName: String,
    profilePing: String,
    time: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.subtitle1
) {
    Row(
        modifier = modifier.semantics {
            contentDescription = "$profilePing post a tweet $time ago"
        },
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = profileName,
            style = textStyle,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = twitterColor,
            modifier = Modifier
                .size(textStyle.fontSize.value.dp)
                .align(Alignment.CenterVertically)
                .padding(start = 2.dp)
        )
        Text(
            text = "$profilePing · $time",
            modifier = Modifier.padding(start = 8.dp),
            style = textStyle
        )
    }
}