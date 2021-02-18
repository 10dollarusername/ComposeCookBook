import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.ui.utils.RotateIcon
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp

@Composable
fun AnimationScreen() {
    var animateIcon by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.semantics { testTag = "Animation Screen" },
        topBar = {
            TopAppBar(
                title = { Text(text = "Animations") },
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = { animateIcon = !animateIcon }) {
                        RotateIcon(
                            state = animateIcon,
                            asset = Icons.Filled.PlayArrow,
                            angle = 1440f,
                            duration = 3000
                        )
                    }
                }
            )
        },
        bodyContent = {
            AnimationScreenContent()
        }
    )
}

@Composable
fun AnimationScreenContent() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(4.dp))
        TitleText(title = "Animate as State")
        AnimationsForStates()
        SubtitleText(subtitle = "animate(Color)")
        //SimpleColorAnimation()
        Spacer(modifier = Modifier.padding(4.dp))
        SubtitleText(subtitle = "animate(Color)+animate(Dp)")
        //SingleScaleAndColorAnimation()
        Spacer(modifier = Modifier.padding(4.dp))
        SubtitleText(subtitle = "animate(Dp)")
        //SingleImageScaleAnimation()
        Spacer(modifier = Modifier.padding(4.dp))
        SubtitleText(subtitle = "animateContentSize()")
        //SingleAnimationContent()
        Spacer(modifier = Modifier.padding(8.dp))
        TitleText(title = "Visibility Animations: Experimental")
        Spacer(modifier = Modifier.padding(8.dp))
        //VisibilityAnimationFAB()
        Spacer(modifier = Modifier.padding(8.dp))
        //VisibilityAnimationFade()
        Spacer(modifier = Modifier.padding(8.dp))
        //VisibilityAnimationShrinkExpand()
        Spacer(modifier = Modifier.padding(8.dp))
        //SlideInOutSample()
        Spacer(modifier = Modifier.padding(8.dp))
        TitleText(title = "Multi State Animations")
        Spacer(modifier = Modifier.padding(8.dp))
        SubtitleText(subtitle = "Three different colorPropKey state with repeat")
        //ColorMultistateAnimation()
        Spacer(modifier = Modifier.padding(8.dp))
        SubtitleText(subtitle = "Different DpPropKey value states animation")
        //DpMultiStateAnimation()
        Spacer(modifier = Modifier.padding(8.dp))
        SubtitleText(subtitle = "Different FloatPropKey value states animation")
        //FloatMutliStateAnimation()
        Spacer(modifier = Modifier.padding(8.dp))
        TitleText(title = "Multi State Animations on Canvas")
        val ripple = remember { mutableStateOf(false) }
        if (ripple.value) {
          //  FloatMultiStateAnimationExplode(500)
        }
        Button(onClick = { ripple.value = !ripple.value }) {
            Text(text = "Top top explode")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        SubtitleText(subtitle = "Circle stroke canvas drawing + rotation animation")
        //FloatMultiStateAnimationCircleStrokeCanvas()
        Spacer(modifier = Modifier.padding(30.dp))
        SubtitleText(subtitle = "Multiple Circle canvas+ Scaling radius")
        //FloatMultiStateAnimationCircleCanvas()
        Spacer(modifier = Modifier.padding(50.dp))
        //draw layer animations
        //DrawLayerAnimations()
        Spacer(modifier = Modifier.padding(50.dp))
        //Animated Values animation
        //AnimatedValuesAnimations()
        Spacer(modifier = Modifier.padding(50.dp))
        //TickerAnimation()
        Spacer(modifier = Modifier.padding(100.dp))
      //  ColorPicker(onColorSelected = { /*TODO*/ })
        Spacer(modifier = Modifier.padding(100.dp))
    }
}

@Composable
fun AnimationsForStates() {
    SimpleColorStateAnimation()
    SimpleDpStateAnimations()
    SimpleFloatStateAnimation()
    SimpleAnimateCustomStateClass()
}

@Composable
fun SimpleColorStateAnimation() {
    SubtitleText(subtitle = "Animate color")
    val enabled = remember { mutableStateOf(true) }
    val animatedColor = animateColorAsState(targetValue =
    if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)

    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = animatedColor.value
    )

    Button(
    onClick = { enabled.value = !enabled.value },
    colors = buttonColors,
    modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Color Animation")
    }
}
//
@Composable
fun SimpleDpStateAnimations() {
    SubtitleText(subtitle = "Animate DP value")
    var enabled by remember { mutableStateOf(true) }
    val animatedColorState = animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
    val animatedHeightState = animateDpAsState(targetValue = if (enabled) 40.dp else 60.dp)
    val animatedWidthState = animateDpAsState(if (enabled) 150.dp else 300.dp)
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = animatedColorState.value
    )
    Button(
        onClick = { enabled = !enabled },
        colors = buttonColors,
        modifier = Modifier
            .padding(16.dp)
            .height(animatedHeightState.value)
            .preferredWidth(animatedWidthState.value),
    ) {
        Text("Scale & Color")
    }
}

@Composable
fun SimpleFloatStateAnimation() {
    SubtitleText(subtitle = "Animate Float value")

    var enabled by remember { mutableStateOf(true) }
    val animatedFloatState = animateFloatAsState(targetValue = if (enabled) 1f else 0.5f)
    Button(
        onClick = { enabled = !enabled },
        modifier = Modifier
            .padding(16.dp)
            .alpha(animatedFloatState.value)
    ) {
        Text("Opacity change")
    }
}

data class CustomAnimationState(val width: Dp, val rotation: Float)
@Composable
fun SimpleAnimateCustomStateClass() {
    SubtitleText(subtitle = "Animate Custom State state with 2D vector")
    var enabled by remember { mutableStateOf(true) }
    val initUiState = CustomAnimationState(200.dp, 0f)
    val targetUiState = CustomAnimationState(300.dp, 15f)

    val uiState = if (enabled) initUiState else targetUiState

    val animatedUiState by animateValueAsState(
        targetValue = uiState,
        typeConverter = TwoWayConverter(
            convertToVector = { AnimationVector2D(it.width.value, it.rotation) },
            convertFromVector = { CustomAnimationState(it.v1.dp, it.v2)}
        ),
        animationSpec = tween(600)
    )

    Button(
        onClick = { enabled = !enabled },
        modifier = Modifier
            .padding(16.dp)
            .width(animatedUiState.width)
            .rotate(animatedUiState.rotation)
    ) {
        Text("Custom State Animation")
    }
}

//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun VisibilityAnimationFAB() {
//    var expanded by remember { mutableStateOf(true) }
//    FloatingActionButton(
//        onClick = { expanded = !expanded },
//        modifier = Modifier
//    ) {
//        Row(Modifier.padding(start = 16.dp, end = 16.dp)) {
//            Icon(
//                vectorResource(id = R.drawable.ic_twitter),
//                Modifier.align(Alignment.CenterVertically)
//            )
//            AnimatedVisibility(
//                expanded,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            ) {
//                Text(modifier = Modifier.padding(start = 8.dp), text = "Tweet")
//            }
//        }
//    }
//
//}
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun VisibilityAnimationFade() {
//    var visibility by remember { mutableStateOf(true) }
//    Row(
//        Modifier.padding(start = 12.dp, end = 12.dp).width(200.dp).height(60.dp)
//            .background(green500).clickable(onClick = { visibility = !visibility })
//    ) {
//        AnimatedVisibility(
//            visibility,
//            modifier = Modifier.align(Alignment.CenterVertically),
//            enter = fadeIn(1f),
//            exit = fadeOut(0f)
//        ) {
//            Text(modifier = Modifier.padding(start = 12.dp), text = "Tap to Fade In Out")
//        }
//    }
//}
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun VisibilityAnimationShrinkExpand() {
//    var visibility by remember { mutableStateOf(true) }
//    Row(
//        Modifier.padding(start = 12.dp, end = 12.dp).width(200.dp).height(60.dp)
//            .background(green500).clickable(onClick = { visibility = !visibility })
//    ) {
//        AnimatedVisibility(
//            visibility,
//            modifier = Modifier.align(Alignment.CenterVertically),
//            enter = expandIn(Alignment.Center, { fullSize: IntSize -> fullSize * 4 }),
//            exit = shrinkOut(Alignment.Center)
//        ) {
//            Text(modifier = Modifier.padding(start = 12.dp), text = "Tap to Shrink expand")
//        }
//    }
//}
//
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun SlideInOutSample() {
//    var visibility by remember { mutableStateOf(true) }
//    Row(
//        Modifier.padding(start = 12.dp, end = 12.dp).width(200.dp).height(60.dp)
//            .background(green500).clickable(onClick = { visibility = !visibility })
//    ) {
//        AnimatedVisibility(
//            visibility,
//            enter = slideIn(
//                { IntOffset(0, 100) },
//                tween(500, easing = LinearOutSlowInEasing)
//            ),
//            exit = slideOut(
//                { IntOffset(0, 50) },
//                tween(500, easing = FastOutSlowInEasing)
//
//            )
//        ) {
//            // Content that needs to appear/disappear goes here:
//            Text("Tap for Sliding animation")
//        }
//    }
//}
//
//@Composable
//fun ColorMultistateAnimation() {
//    var colorState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    var colorFinalState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//
//    val colorAnim = transition(
//        definition = AnimationDefinitions.colorAnimDefinition,
//        initState = colorState,
//        toState = colorFinalState,
//        onStateChangeFinished = {
//            when (it) {
//                AnimationDefinitions.AnimationState.START -> {
//                    colorState = AnimationDefinitions.AnimationState.START
//                    colorFinalState = AnimationDefinitions.AnimationState.MID
//                }
//                AnimationDefinitions.AnimationState.MID -> {
//                    colorState = AnimationDefinitions.AnimationState.MID
//                    colorFinalState = AnimationDefinitions.AnimationState.END
//                }
//                AnimationDefinitions.AnimationState.END -> {
//                    colorState = AnimationDefinitions.AnimationState.END
//                    colorFinalState = AnimationDefinitions.AnimationState.START
//                }
//            }
//        }
//    )
//    val buttonColors = ButtonDefaults.buttonColors(
//        backgroundColor = colorAnim[AnimationDefinitions.colorPropKey]
//    )
//    Button(
//        modifier = Modifier.fillMaxWidth().padding(16.dp),
//        colors = buttonColors,
//        onClick = {}) {
//        Text("Color prop Animations", modifier = Modifier.padding(8.dp))
//    }
//}
//
//@Preview
//@Composable
//fun DpMultiStateAnimation() {
//    val dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val dpAnim = transition(
//        definition = AnimationDefinitions.dpAnimDefinition,
//        initState = dpStartState,
//        toState = dpEndState
//    )
//
//    Row(horizontalArrangement = Arrangement.SpaceAround) {
//        Card(modifier = Modifier.preferredSize(120.dp).padding(12.dp)) {
//            Image(
//                painter = painterResource(id = R.drawable.lana),
//                contentDescription = null,
//                modifier = Modifier.height(dpAnim[AnimationDefinitions.dpPropKey])
//            )
//        }
//        Card(modifier = Modifier.preferredSize(120.dp).padding(12.dp)) {
//            Image(
//                painter = painterResource(id = R.drawable.billie),
//                contentDescription = null,
//                modifier = Modifier.height(100.dp - dpAnim[AnimationDefinitions.dpPropKey])
//            )
//        }
//    }
//}
//
//
//@Preview
//@Composable
//fun FloatMutliStateAnimation() {
//    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val floatStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val floatAnim = transition(
//        definition = AnimationDefinitions.floatAnimDefinition(0f, 100f, true),
//        initState = floatStateStart,
//        toState = floatStateFinal
//    )
//
//    Card(backgroundColor = Color.White, modifier = Modifier.preferredSize(150.dp)) {
//        Image(
//            painter = painterResource(id = R.drawable.lana),
//            contentDescription = null,
//            alpha = floatAnim[AnimationDefinitions.floatPropKey] / 100,
//        )
//    }
//
//}
//
//@Composable
//fun FloatMultiStateAnimationCircleStrokeCanvas() {
//    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val floatAnim = transition(
//        definition = AnimationDefinitions.floatAnimDefinition(0f, 360f, true),
//        initState = floatStateStart,
//        toState = floadStateFinal
//    )
//    val stroke = Stroke(8f)
//    Canvas(modifier = Modifier.padding(16.dp).preferredSize(100.dp)) {
//        val diameter = size.minDimension
//        val radius = diameter / 2f
//        val insideRadius = radius - stroke.width
//        val topLeftOffset = Offset(
//            10f,
//            10f
//        )
//        val size = Size(insideRadius * 2, insideRadius * 2)
//        var rotationAngle = floatAnim[AnimationDefinitions.floatPropKey] - 90f
//        drawArc(
//            color = green500,
//            startAngle = rotationAngle,
//            sweepAngle = 150f,
//            topLeft = topLeftOffset,
//            size = size,
//            useCenter = false,
//            style = stroke,
//        )
//        rotationAngle += 40
//    }
//}
//
//@Preview
//@Composable
//fun FloatMultiStateAnimationCircleCanvas(color: Color = green500, radiusEnd: Float = 200f) {
//    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val floatAnim = transition(
//        definition = AnimationDefinitions.floatAnimDefinition(0f, radiusEnd, true),
//        initState = floatStateStart,
//        toState = floadStateFinal
//    )
//
//    Canvas(modifier = Modifier.padding(16.dp)) {
//        val centerOffset = Offset(
//            10f,
//            10f
//        )
//        val radius = floatAnim[AnimationDefinitions.floatPropKey]
//        drawCircle(
//            color = color.copy(alpha = 0.8f),
//            radius = radius,
//            center = centerOffset,
//        )
//        drawCircle(
//            color = color.copy(alpha = 0.4f),
//            radius = radius / 2,
//            center = centerOffset,
//        )
//        drawCircle(
//            color = color.copy(alpha = 0.2f),
//            radius = radius / 4,
//            center = centerOffset,
//        )
//    }
//}
//
//@Composable
//fun FloatMultiStateAnimationExplode(duration: Int = 500) {
//    val floatStateStart by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    val floadStateFinal by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//    val floatAnim = transition(
//        definition = AnimationDefinitions.floatAnimDefinition(
//            0f, 2000f, false, duration
//        ),
//        initState = floatStateStart,
//        toState = floadStateFinal
//    )
//
//    Canvas(modifier = Modifier) {
//        val centerOffset = Offset(
//            10f,
//            10f
//        )
//        var radius = floatAnim[AnimationDefinitions.floatPropKey]
//        drawCircle(
//            color = green200.copy(alpha = 0.8f),
//            radius = radius,
//            center = centerOffset,
//        )
//        radius += 500
//    }
//}
//
//@Composable
//private fun DrawLayerAnimations() {
//    TitleText(title = "DrawLayer changes + Single value animations")
//    var draw by remember { mutableStateOf(false) }
//    val modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//        scaleX = animateAsState(if (draw) 2f else 1f).value,
//        scaleY = animateAsState(if (draw) 2f else 1f).value,
//        shadowElevation = animateAsState(if (draw) 50f else 5f).value,
//        clip = draw,
//        rotationZ = animateAsState(if (draw) 360f else 0f).value
//    ).clickable(onClick = { draw = !draw })
//
//    Image(
//        painter = painterResource(id = R.drawable.bp),
//        contentDescription = null,
//        modifier = modifier
//    )
//
//    Spacer(modifier = Modifier.padding(30.dp))
//    var draw2 by remember { mutableStateOf(false) }
//
//    Box {
//        Image(
//            painter = painterResource(id = R.drawable.adele21),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw2) 30f else 5f).value,
//                translationX = animateAsState(if (draw2) 320f else 0f).value,
//                translationY = 0f,
//            ).clickable(onClick = { draw2 = !draw2 })
//        )
//        Image(
//            painter = painterResource(id = R.drawable.dualipa),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw2) 30f else 10f).value,
//                translationX = animateAsState(if (draw2) -320f else 0f).value,
//                translationY = animateAsState(if (draw2) 0f else 30f).value
//            ).clickable(onClick = { draw2 = !draw2 })
//        )
//        Image(
//            painter = painterResource(id = R.drawable.edsheeran),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw2) 30f else 5f).value,
//                translationY = animateAsState(if (draw2) 0f else 50f).value
//            ).clickable(onClick = { draw2 = !draw2 })
//        )
//    }
//    Spacer(modifier = Modifier.padding(30.dp))
//    var draw3 by remember { mutableStateOf(false) }
//
//    Box {
//        Image(
//            painter = painterResource(id = R.drawable.wolves),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw3) 30f else 5f).value,
//                translationX = animateAsState(if (draw3) 320f else 0f).value,
//                rotationY = animateAsState(if (draw3) 45f else 0f).value,
//                translationY = 0f
//            ).clickable(onClick = { draw3 = !draw3 })
//        )
//        Image(
//            painter = painterResource(id = R.drawable.sam),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw3) 30f else 10f).value,
//                translationX = animateAsState(if (draw3) -320f else 0f).value,
//                rotationY = animateAsState(if (draw3) 45f else 0f).value,
//                translationY = animateAsState(if (draw3) 0f else 30f).value
//            ).clickable(onClick = { draw3 = !draw3 })
//        )
//        Image(
//            painter = painterResource(id = R.drawable.billie),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw3) 30f else 5f).value,
//                translationY = animateAsState(if (draw3) 0f else 50f).value,
//                rotationY = animateAsState(if (draw3) 45f else 0f).value
//            ).clickable(onClick = { draw3 = !draw3 })
//        )
//    }
//    Spacer(modifier = Modifier.padding(30.dp))
//    var draw4 by remember { mutableStateOf(false) }
//
//    Box {
//        Image(
//            painter = painterResource(id = R.drawable.imagindragon),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw4) 30f else 5f).value,
//                translationX = animateAsState(if (draw4) 320f else 0f).value,
//                rotationZ = animateAsState(if (draw4) 45f else 0f).value,
//                translationY = 0f
//            ).clickable(onClick = { draw4 = !draw4 })
//        )
//        Image(
//            painter = painterResource(id = R.drawable.khalid),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw4) 30f else 10f).value,
//                translationX = animateAsState(if (draw4) -320f else 0f).value,
//                rotationZ = animateAsState(if (draw4) 45f else 0f).value,
//                translationY = animateAsState(if (draw4) 0f else 30f).value
//            ).clickable(onClick = { draw4 = !draw4 })
//        )
//        Image(
//            painter = painterResource(id = R.drawable.camelia),
//            contentDescription = null,
//            modifier = Modifier.preferredSize(150.dp).graphicsLayer(
//                shadowElevation = animateAsState(if (draw4) 30f else 5f).value,
//                translationY = animateAsState(if (draw4) 0f else 50f).value,
//                rotationZ = animateAsState(if (draw4) 45f else 0f).value,
//            ).clickable(onClick = { draw4 = !draw4 })
//        )
//    }
//}
//
//@Composable
//fun AnimatedValuesAnimations() {
//    val moveX = -1000f
//    val moveXMax = 1000f
//    TitleText(title = "Animated Value to Animations + drag")
//    val xFloat = animatedFloat(initVal = 0f)
//
//    val dragObserver = object : DragObserver {
//        override fun onStart(downPosition: Offset) {
//            xFloat.setBounds(moveX, moveXMax)
//            super.onStart(downPosition)
//        }
//
//        override fun onStop(velocity: Offset) {
//            xFloat.snapTo(moveX)
//            super.onStop(velocity)
//        }
//
//        override fun onCancel() {
//            xFloat.snapTo(moveX)
//            super.onCancel()
//        }
//
//        override fun onDrag(dragDistance: Offset): Offset {
//            xFloat.animateTo(xFloat.targetValue + dragDistance.x)
//            return super.onDrag(dragDistance)
//        }
//    }
//
//    CardElement(
//        modifier = Modifier.background(green500).preferredSize(200.dp)
//            .rawDragGestureFilter(dragObserver)
//            .offset(
//                x = Dp(xFloat.value),
//            )
//    )
//}
//
//@Composable
//fun CardElement(modifier: Modifier = Modifier) {
//    Card(
//        backgroundColor = MaterialTheme.colors.primary,
//        modifier = modifier
//    ) {}
//}
//
//
//@Composable
//fun TickerAnimation() {
//    var dpStartState by remember { mutableStateOf(AnimationDefinitions.AnimationState.START) }
//    var dpEndState by remember { mutableStateOf(AnimationDefinitions.AnimationState.END) }
//
//    val dpAnim = transition(
//        definition = AnimationDefinitions.tickerDefinition,
//        initState = dpStartState,
//        toState = dpEndState,
//        onStateChangeFinished = { it ->
//            when (it) {
//                AnimationDefinitions.AnimationState.START -> {
//                    dpStartState = AnimationDefinitions.AnimationState.START
//                    dpEndState = AnimationDefinitions.AnimationState.END
//                }
//                AnimationDefinitions.AnimationState.MID -> {
//                    // Nothing
//                }
//                AnimationDefinitions.AnimationState.END -> {
//                    dpStartState = AnimationDefinitions.AnimationState.END
//                    dpEndState = AnimationDefinitions.AnimationState.START
//                }
//            }
//        }
//    )
//    Box(modifier = Modifier.height(50.dp).background(green700).padding(16.dp)) {
//        Text(
//            text = "15",
//            color = Color.White,
//            modifier = Modifier.offset(y = 100.dp - dpAnim[AnimationDefinitions.tickerPropKey])
//        )
//        Text(
//            text = "14",
//            color = Color.White,
//            modifier = Modifier.offset(y = 50.dp - dpAnim[AnimationDefinitions.tickerPropKey])
//        )
//        Text(
//            text = "13",
//            color = Color.White,
//            modifier = Modifier.offset(y = -dpAnim[AnimationDefinitions.tickerPropKey])
//        )
//    }
//}
//
//
//
//
