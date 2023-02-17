package com.example.compose_study

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.example.compose_study.ui.theme.ComposestudyTheme
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import java.util.regex.Pattern

const val EMAIL = 0
const val PASSWORD = 1
const val EMAIL_PATTERN =
    "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Surface(modifier, color = Color.White) {
        Column(modifier = modifier) {
            BackButton {
                Toast.makeText(context, "back button click", Toast.LENGTH_SHORT).show()
            }
            SignInfo("이메일 주소", "이메일 주소를 입력하세요.", EMAIL)
            SignInfo("비밀번호", "비밀번호를 입력하세요.", PASSWORD)
            Spacer(Modifier.size(40.dp))
            BtnJoin {
                Toast.makeText(context, "join button click", Toast.LENGTH_SHORT).show()
            }


        }

        Box(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom)
        ){
            Text(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .background(Color(0xffFF4857)),
                textAlign = TextAlign.Center,
                text = "로그인",
                fontSize = 14.dp.textSp,
                color = Color(0xffffffff)
            )
        }


    }
}

@Composable
fun BackButton(
    onBackClick: () -> Unit
) {
    Image(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = "Back",
        modifier = Modifier
            .size(54.dp)
            .clickable(onClick = onBackClick)
            .padding(15.dp)
    )
}

@Composable
fun SignInfo(
    focusLabel: String,
    unFocusLabel: String,
    type: Int
) {

    var isFocused by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }


    Column {
        Box {
            TextField(
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xff111111),
                    focusedLabelColor = Color(0xffaaaaaa),
                    unfocusedLabelColor = Color(0xffdddddd),
                    backgroundColor = Color.White,
                    cursorColor = Color(0xff111111),
                    focusedIndicatorColor = Color(0xff111111),
                    unfocusedIndicatorColor = Color(0xffdddddd)
                ),
                textStyle = TextStyle(
                    fontSize = 15.dp.textSp
                ),
                singleLine = true,
                label = {
                    Text(
                        text = if (isFocused || text.isNotEmpty()) focusLabel
                        else unFocusLabel
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (type == EMAIL) KeyboardType.Email else KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused }
                    .align(Alignment.TopCenter),
                visualTransformation = if (type == EMAIL) VisualTransformation.None else PasswordVisualTransformation()
            )
            if (text.isNotEmpty()) {
                IconButton(
                    onClick = { text = "" },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 20.dp, top = 40.dp)
                        .size(21.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.btn_del),
                        contentDescription = "delete"
                    )
                }
            }
        }

        if (type == EMAIL && !emailCheck(text)) {
            Text(
                text = "잘못된 주소 형식입니다.",
                modifier = Modifier.padding(top = 8.dp, start = 20.dp),
                fontSize = 12.dp.textSp,
                color = Color(0xffff4857)
            )
        }
        if (type == PASSWORD) {
            Text(
                text = "비밀번호 찾기",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp, top = 10.dp),
                fontSize = 13.dp.textSp,
                color = Color(0xff111111),
                textAlign = TextAlign.End
            )
        }

    }
}

@Composable
fun BtnJoin(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .padding(horizontal = 20.dp),
        onClick = onClick,
        shape = RoundedCornerShape(3.dp),
        border = BorderStroke(1.dp, Color(0xffdddddd)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        )
    ) {
        Text(
            "이메일 회원가입",
            fontSize = 14.dp.textSp,
            color = Color(0xff777777)
        )
    }
}


val Dp.textSp: TextUnit
    @Composable get() = with(LocalDensity.current) {
        this@textSp.toSp()
    }

fun emailCheck(text: String): Boolean {
    return if (text.isNotEmpty()) {
        Pattern.compile(EMAIL_PATTERN).matcher(text).matches()
    } else true
}

//@Composable
//fun MyApp(modifier: Modifier = Modifier) {
//    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
//
//    Surface(modifier, color = MaterialTheme.colorScheme.background) {
//        if (shouldShowOnboarding) {
//            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
//        } else {
//            Greetings()
//        }
//    }
//}
//
//@Composable
//fun OnboardingScreen(
//    onContinueClicked: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Welcome to the Basics Codelab!")
//        Button(
//            modifier = Modifier.padding(vertical = 24.dp),
//            onClick = onContinueClicked
//        ) {
//            Text("Continue")
//        }
//    }
//}
//
//@Composable
//private fun Greetings(
//    modifier: Modifier = Modifier,
//    names: List<String> = List(1000) { "$it" }
//) {
//    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
//        items(items = names) { name ->
//            Greeting(name = name)
//        }
//    }
//}
//
//@Composable
//private fun Greeting(name: String) {
//    Card(
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.primary
//        ),
//        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        CardContent(name)
//    }
//}
//
//@Composable
//private fun CardContent(name: String) {
//    var expanded by rememberSaveable { mutableStateOf(false) }
//
//    Row(
//        modifier = Modifier
//            .padding(12.dp)
//            .animateContentSize(
//                animationSpec = spring(
//                    dampingRatio = Spring.DampingRatioMediumBouncy,
//                    stiffness = Spring.StiffnessLow
//                )
//            )
//    ) {
//        Column(
//            modifier = Modifier
//                .weight(1f)
//                .padding(12.dp)
//        ) {
//            Text(text = "Hello, ")
//            Text(
//                text = name, style = MaterialTheme.typography.headlineMedium.copy(
//                    fontWeight = FontWeight.ExtraBold
//                )
//            )
//            if (expanded) {
//                Text(
//                    text = ("Composem ipsum color sit lazy, " +
//                            "padding theme elit, sed do bouncy. ").repeat(4),
//                    modifier = Modifier.padding(top = 15.dp)
//                )
//            }
//        }
//        IconButton(
//            onClick = { expanded = !expanded }
//        ) {
//            Icon(
//                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
//                contentDescription = if (expanded) {
//                    stringResource(R.string.show_less)
//                } else {
//                    stringResource(R.string.show_more)
//                }
//            )
//        }
//    }
//}
//
//@Preview(
//    showBackground = true,
//    widthDp = 320,
//    uiMode = UI_MODE_NIGHT_YES,
//    name = "DefaultPreviewDark"
//)
//@Preview(showBackground = true, widthDp = 320)
//@Composable
//fun DefaultPreview() {
//    ComposestudyTheme {
//        Greetings()
//    }
//}
//
//@Preview(showBackground = true, widthDp = 320, heightDp = 320)
//@Composable
//fun OnboardingPreview() {
//    ComposestudyTheme {
//        OnboardingScreen(onContinueClicked = {})
//    }
//}
//
@Preview
@Composable
fun MyAppPreview() {
    ComposestudyTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
