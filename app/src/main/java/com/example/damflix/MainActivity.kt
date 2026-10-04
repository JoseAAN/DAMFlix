package com.example.damflix

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.damflix.ui.theme.DAMFlixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DAMFlixTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) {
                    innerPadding -> PerfilUsuario(modifier = Modifier.padding(innerPadding));
                }
            }
        }
    }
}

@Composable
fun PerfilUsuario(modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_avatar),
            contentDescription = stringResource(R.string.avatar_desc),
            modifier = Modifier.size(90.dp).clip(CircleShape).border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = stringResource(id = R.string.user_name_label), style = MaterialTheme.typography.titleLarge)
            Text(text = stringResource(id = R.string.user_role), style = MaterialTheme.typography.bodyMedium)
            Text(text = stringResource(id = R.string.user_stats_viewed), style = MaterialTheme.typography.bodyMedium)
            Text(text = stringResource(id = R.string.user_stats_reviewed), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
