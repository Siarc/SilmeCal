package com.taman.silmebagcalculator.ui.screens.dashboard

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.ui.components.BackgroundDroplet
import com.taman.silmebagcalculator.ui.theme.SilmeBagCalculatorTheme
import com.taman.silmebagcalculator.utils.NonWovenScreen

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview(){
    val navController = rememberNavController()
    SilmeBagCalculatorTheme {
        DashboardScreen(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = viewModel(),
    navController: NavHostController
){
    val backPressedOnce by viewModel.backPressedOnce.collectAsState()
    val context = LocalContext.current
    val activity = context as? Activity

    BackHandler {
        viewModel.onBackPressed()

        if (backPressedOnce) {
            activity?.finish()
        } else {
            // Show warning toast
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Dashboard") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        },
        content = { padding ->
            Surface(
                color = Color.White,
                modifier = Modifier.padding(padding)
            ) {
                BackgroundDroplet()
                Column(
                    modifier = modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    WelcomeMessage()
                    Spacer(modifier = Modifier.height(8.dp))
                    FavoriteCollectionCard(
                        fc2Drawable = R.drawable.non_woven_bag,
                        fc2Text = R.string.non_woven_bag,
                        onClick = {
                            navController.navigate(NonWovenScreen)
                        },
                        modifier = Modifier.padding(8.dp),
                    )
                    FavoriteCollectionCard(
                        fc2Drawable = R.drawable.jute_bag,
                        fc2Text = R.string.jute_bag,
                        onClick = {},
                        modifier = Modifier.padding(8.dp)
                    )
                    FavoriteCollectionCard(
                        fc2Drawable = R.drawable.cotton_bag,
                        fc2Text = R.string.cotton_bag,
                        onClick = {},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    )

}

@Composable
fun WelcomeMessage(modifier: Modifier = Modifier) {

    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome on board!",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Let's help you meet up your task",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes fc2Drawable: Int, @StringRes fc2Text: Int, onClick:() -> Unit, modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(fc2Drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(fc2Text),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center

            )
            Image(
                painter = painterResource(fc2Drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
        }
    }
}