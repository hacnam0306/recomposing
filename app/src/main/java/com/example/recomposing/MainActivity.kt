package com.example.firstcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recomposing.ui.theme.RecomposingTheme
import com.example.recomposing.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecomposingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize().padding(8.dp), color = MaterialTheme.colorScheme.background) {
                    ChatList(listChat)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

data class Message(val message:String, val senderName:String)
@Composable
fun MessageItem(message: Message){
    Row(modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "avatar",
            modifier = Modifier.width(40.dp).clip(CircleShape).border(1.dp, Color.Red, CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))

        var isExpanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.clickable {
            isExpanded = !isExpanded
        }){
            CommonText(message.message)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message.senderName,

                color= MaterialTheme.colorScheme.secondary
                , style = MaterialTheme.typography.bodySmall
                ,
                maxLines = if(isExpanded) 4 else 1
            )
        }
    }
}

@Composable
fun CommonText(text:String){
    Text(text = text, fontSize = 24.sp, lineHeight = 32.sp,
        style = MaterialTheme.typography.titleLarge
    )

}

@Composable
fun ChatList(data:List<Message>){
    LazyColumn {
        items(data){ item: Message ->
            MessageItem(item)
        }

    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    RecomposingTheme {
        ChatList(listChat)
    }
}


val listChat:List<Message> = listOf( Message("Hello world","con kiu"),
    Message("Hello world","con kiu"),
    Message("Hello world 1","con kiu 1 con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5"),
    Message("Hello world 3","con kiu 3 con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5"),
    Message("Hello world 4","con kiu 4 con kiu 5con kiu 5con kiu 5con kiu 5"),

    Message("Hello world 5","con kiu 5 con kiu 5con kiu 5con kiu 5"),
    Message("Hello world 6","con kiu 6 con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5con kiu 5"),

    )
