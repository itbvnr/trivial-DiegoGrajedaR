package cat.itb.m78.exercices


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cat.itb.m78.exercices.theme.AppTheme
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    ManualNav()

}

private sealed interface Screen {
    data object MenuScreen : Screen
    data object GameScreen : Screen
    data object ScoreScreen : Screen
    data object SettingsScreen : Screen
}

private class ManualNavAppViewModel : ViewModel() {
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.MenuScreen)
    var difficulty by mutableStateOf("Easy")
    var rounds by mutableStateOf(5)
    var timePerRound by mutableStateOf(30)
    var score by mutableStateOf(0)

    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }

    fun updateSettings(newDifficulty: String, newRounds: Int, newTimePerRound: Int) {
        difficulty = newDifficulty
        rounds = newRounds
        timePerRound = newTimePerRound
    }

}

    @Composable
    fun Screen1(navigateToScreen2: () -> Unit, navigateToScreen4: () -> Unit, )
    {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background (Color.Yellow.copy(alpha = 0.6f)).fillMaxHeight().fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,)
        {
            Image(
                painter = painterResource(
                    Res.drawable.trivial),"",
                modifier = Modifier
                    .size(350.dp))

            Button(onClick = navigateToScreen2,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta.copy(alpha = 1f)),)
            {
                Text("New Game")
            }
            Spacer(modifier = Modifier. height(16.dp))

            Button(onClick = navigateToScreen4,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta.copy(alpha = 1f)),)
            {
                Text("Settings")
            }
        }
    }

    @Composable
    fun Screen2(navigateToScreen3: (Int) -> Unit, rounds: Int)
    {
        val allQuestions = listOf(
            "-Qui va pintar la Mona Lisa? " to listOf("Da Vinci", "Van Gogh", "Picasso", "Michelangelo"),
            "-Quin país és conegut com 'la terra del sol naixent'?" to listOf("Japó", "Corea del Sud", "Xina", "Tailàndia" ),
            "-Quina és la capital de França?" to listOf("París", "Roma", "Berlín", "Londres"),
            "-Quin element químic té el símbol 'O'?" to listOf("Oxigen", "Or", "Ozó", "Ouri"),
            "-Qui va escriure 'Don Quixot'? " to listOf("Miguel de Cervantes", "García Márquez", "Pablo Neruda", "García Lorca"),
            "-Quin d'aquests animals és un mamífer?" to listOf("Cangur", "Ocells", "Pingüí", "Tauró"),
            "-Quina guerra va tenir lloc entre 1914 i 1918?" to listOf("Primera Guerra Mundial", "Guerra e Vietnam", "Segona Guerra Mundial", "Guerra Civil Espanyola"),
            "-Quina és la llengua oficial de Brasil?" to listOf("Portuguès", "Espanyol", "Francès", "Anglès"),
            "-Quin tipus de fruit és una taronja?" to listOf("Citrus", "Drupa", "Berria", "Poma"),
            "-Quina ciutat és coneguda com 'la ciutat de la llum'?" to listOf("París", "Nova York", "Tòquio", "Londres"),
            "-Quina és la moneda oficial del Regne Unit?" to listOf("Lliura", "Dòlar", "Euro", "Peseta"),
            "-Quin d'aquests invents va ser creat per Thomas Edison?" to listOf("La bombeta elèctrica", "El telèfon", "L màquina de cosir", "L'ascensor"),
            "-Quina nació va ser l'origen del reggae?" to listOf("Jamaica", "Cuba", "Brasil", "Haití"),
            "-Qui va ser el primer home a caminar sobre la Lluna?" to listOf("Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Michael Collins"),
            "-Quin d'aquests presidents va ser el primer president dels Estats Units?" to listOf("George Washington", "Franklin D. Roosevelt", "Theodore Roosevelt", "Abraham Lincoln"),
            "-En quin oceà es troba la Gran Barrera de Coral?" to listOf("Oceà Pacífic", "Oceà Àrtic", "Oceà Índic", "Oceà Atlàntic"),
            "-Quina és la unitat bàsica de la matèria? " to listOf("L'àtom", "El quark", "La partícula", "La molècula"),
            "-Quina és la capital de Mèxic? " to listOf("Ciutat de Mèxic", "Cancún", "Monterrey", "Guadalajara"),
            "-Qui va ser el creador de la teoria de la relativitat?" to listOf("Albert Einstein", "Galileo Galilei", "Nikola Tesla", "Isaac Newton"),
            "-En quina muntanya va ser descobert l'or en la història de la mineria de Califòrnia?" to listOf("Muntanya Sutter", "Muntanya Kilimanjaro", "Muntanya de l'Aconcagua", "Muntanya de l'Aconcagua"),
            ).shuffled()


        val questions = allQuestions.take(rounds)
        var currentQuestionIndex by remember { mutableStateOf(0) }
        var score by remember { mutableStateOf(0) }



        if (currentQuestionIndex >= questions.size) {
            navigateToScreen3(score)
            return
        }


        val (question, answers) = questions[currentQuestionIndex]


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp).background (Color.Yellow.copy(alpha = 0.6f))
                .fillMaxHeight().fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Text(text = question, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            answers.shuffled().forEach { answer ->
                Button(onClick =
                    {
                        if (answer == answers[0]) score++
                        currentQuestionIndex++
                    })
                {
                    Text(answer)
                }
            }
        }
    }


    @Composable
    fun Screen3(navigateToScreen1: () -> Unit, score: Int) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background (Color.Yellow.copy(alpha = 0.6f)).fillMaxHeight().fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,)
        {
            Text("Your Score: $score", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = navigateToScreen1,){
                Text("Return to Menu")
            }

        }
    }


    @Composable
    fun Screen4(navigateToScreen1: () -> Unit, updateSettings: (String, Int, Int) -> Unit)
    {
        var difficulty by remember { mutableStateOf("Easy")}
        val difficulties = listOf("Easy", "Medium", "Hard")

        var selectedRounds by remember { mutableStateOf("5")}
        val roundOptions = listOf("5", "10", "15")

        var timeRounds by remember { mutableStateOf(30f)}

        Column(
            modifier = Modifier
                .background(Color.Yellow.copy(alpha = 0.6f)).fillMaxWidth().fillMaxHeight()
                .padding(16.dp),){

        Column(
            modifier = Modifier
                .background(Color.White).fillMaxWidth().fillMaxHeight()
                .padding(9.dp),)
        {
            Text("Settings", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(30.dp))

            Text("- Difficulty", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(20.dp))

            var expanded by remember { mutableStateOf(false) }
            Button(
                onClick = { expanded = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta.copy(alpha = 0.8f))
            )
            {
                Text(difficulty,)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false })
            {
                difficulties.forEach { level ->
                    DropdownMenuItem(
                        text = { Text(level) }, onClick =
                            {
                                difficulty = level
                                expanded = false
                            })
                }
            }

            Text("- Rounds", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.width(30.dp))

            roundOptions.forEach { option ->
                Row(Modifier.selectable(selected = selectedRounds == option, onClick = { selectedRounds = option }))
                {
                    RadioButton(selected = selectedRounds == option, onClick = null)
                    Text(option, modifier = Modifier.padding(start = 10.dp))
                }
            }


            Spacer(modifier = Modifier.height(50.dp))

            Text("- Time per round", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(30.dp))

            Slider(
                value = timeRounds,
                onValueChange = { timeRounds = it },
                valueRange = 5f..60f,
                steps = 11
            )
            Text("${timeRounds.toInt()} seconds")
            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick =
                    {
                        updateSettings(difficulty, selectedRounds.toInt(), timeRounds.toInt())
                        navigateToScreen1()
                    },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta.copy(alpha = 0.8f))
            )
            {
                Text("Save & Return to Menu")
            }
        }}
    }


    @Composable
    fun ManualNav() {
        val viewModel = viewModel { ManualNavAppViewModel() }
        val currentScreen = viewModel.currentScreen.value
        when (currentScreen) {
            Screen.MenuScreen -> Screen1(
                navigateToScreen2 = { viewModel.navigateTo(Screen.GameScreen) },
                navigateToScreen4 = { viewModel.navigateTo(Screen.SettingsScreen)}
            )

            Screen.GameScreen -> Screen2(
                navigateToScreen3 = { viewModel.score = it; viewModel.navigateTo(Screen.ScoreScreen) },
                rounds = viewModel.rounds
            )

            Screen.ScoreScreen -> Screen3(
                navigateToScreen1 = { viewModel.navigateTo(Screen.MenuScreen) },
                score = viewModel.score
            )

            Screen.SettingsScreen -> Screen4(
                navigateToScreen1 = { viewModel.navigateTo(Screen.MenuScreen) },
                updateSettings = { difficulty, rounds, time -> viewModel.updateSettings(difficulty, rounds, time) }
            )
        }
    }
